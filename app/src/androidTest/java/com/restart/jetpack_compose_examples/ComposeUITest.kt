package com.restart.jetpack_compose_examples

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onParent
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeUITest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun test() {
        composeTestRule.setContent {
            ProfileScreen()
        }

        composeTestRule.onNodeWithTag("").onParent().onChildren()[0]
    }

}


fun SemanticsNodeInteractionsProvider.onNodeWithKey(key: Any) = SemanticsMatcher("") {

}