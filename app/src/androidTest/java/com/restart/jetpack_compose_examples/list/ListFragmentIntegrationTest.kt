package com.restart.jetpack_compose_examples.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.restart.jetpack_compose_examples.ProductModel
import com.restart.jetpack_compose_examples.R
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class ListFragmentIntegrationTest : KoinTest {

    @get:Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val uiRule = createEmptyComposeRule()

    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    val mockWebServer = MockWebServer()


    @Before
    fun init() {
        mockWebServer.start(8080)
        /*stopKoin()

        startKoin {
            modules(module {
                single<OkHttpClient> {
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                }

                single<ListApiInterface> {
                    Retrofit.Builder()
                        .baseUrl(mockWebServer.url("/"))  // Use MockWebServer URL
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                        .client(get())
                        .build()
                        .create(ListApiInterface::class.java)
                }

                single<IListRepository> { ListRepository(get()) }
                viewModelOf(::ListViewModel)
            })
        }*/

        loadKoinModules(module {
            single<ListApiInterface> {
                Retrofit.Builder()
                    .baseUrl(mockWebServer.url("/"))  // Use MockWebServer URL
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .client(get())
                    .build()
                    .create(ListApiInterface::class.java)
            }
        })
    }

    @After
    fun finish() {
        mockWebServer.shutdown()
    }

    @Test
    fun integrationTest() = runTest {
        launchFragmentInContainer<ListFragment> {
            ListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    navController.setGraph(R.navigation.nav_main)
                    navController.setCurrentDestination(R.id.listFragment)
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }

        val mockProducts = listOf<ProductModel>(
            ProductModel(1, "Product 1"),
            ProductModel(1, "Product 2"),
            ProductModel(1, "Product 3"),
        )

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(Gson().toJson(mockProducts))
            .setHeader("Content-Type", "application/json")

        mockWebServer.enqueue(mockResponse)

        uiRule.apply {
            // Verify initial state - button exists and list is empty
            onNodeWithTag("load_data_button").assertExists()
            onNodeWithTag("list").assertExists()

            // Initially no products should be displayed
            onNodeWithText("Product 1").assertDoesNotExist()

            // Click the load data button
            onNodeWithTag("load_data_button").performClick()

            /** Must Wait Until Ideal **/
            waitUntil(10_000) { onNodeWithTag("list").fetchSemanticsNode().children.isNotEmpty() }

            onNodeWithTag("list").onChildren().assertCountEquals(3)
            onNodeWithText("Product 1").assertExists()
            onNodeWithText("Product 2").assertExists()
            onNodeWithText("Product 3").assertExists()
        }

        // Verify API was called
        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/products", recordedRequest.path)
    }
}