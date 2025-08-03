package com.restart.jetpack_compose_examples.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.jetpack_compose_examples.ProductModel
import com.restart.jetpack_compose_examples.R
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ListFragmentTest : KoinTest {

    @get:Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    private val mockRepo = mockk<IListRepository>()

    lateinit var viewModel: ListViewModel

    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun init() {
        stopKoin()
        val testModule = module {
            single<IListRepository> { mockRepo }
            viewModel { ListViewModel(get()).also { viewModel = it } }
        }
        startKoin { modules(testModule) }

    }

    @Test
    fun testListScreen_loadDataButton_populatesList() {
        coEvery { mockRepo.getList() } returns List(10) { index ->
            ProductModel(
                id = index,
                name = "Product $index"
            )
        }

        launchFragmentInContainer<ListFragment> {
            ListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    navController.setGraph(R.navigation.nav_main)
                    navController.setCurrentDestination(R.id.listFragment)
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }

        composeTestRule.onNodeWithTag("list").onChildren().assertCountEquals(0)
        composeTestRule.onNodeWithTag("load_data_button").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("list").onChildren().assertCountEquals(10)

        Assert.assertTrue(viewModel.viewState.value.products.size == 10)
    }
}
