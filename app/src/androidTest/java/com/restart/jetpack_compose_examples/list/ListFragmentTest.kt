package com.restart.jetpack_compose_examples.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.jetpack_compose_examples.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListFragmentTest {

    @get:Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Before
    fun init() {
        /* startKoin {
             androidContext(ApplicationProvider.getApplicationContext())
             modules(viewModelModule)
         }*/
    }

    private fun openListFragment() {
        launchFragmentInContainer<ListFragment> {
            ListFragment()
        }
    }

    @Test
    fun testListDisplayed() {
        val scenario = launchFragmentInContainer<ListFragment>(
            themeResId = androidx.appcompat.R.style.Theme_AppCompat
        ) {
            ListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    navController.setGraph(R.navigation.nav_main)
                    navController.setCurrentDestination(R.id.listFragment)
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("list").assertIsDisplayed()
        composeTestRule.onNodeWithTag("list").assertExists()
    }
}