package com.dat153.passwordmatchercompose

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class CounterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_startsAtZero() {
        composeTestRule.setContent { Counter(Modifier) }

        composeTestRule
            .onNodeWithTag("counter_text")
            .assertTextEquals("0")
    }

    @Test
    fun counter_incrementsOnButtonClick() {
        composeTestRule.setContent { Counter(Modifier) }

        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("counter_text")
            .assertTextEquals("1")
    }

    @Test
    fun counter_incrementsMultipleTimes() {
        composeTestRule.setContent { Counter(Modifier) }

        repeat(3) {
            composeTestRule
                .onNodeWithTag("add_button")
                .performClick()
        }

        composeTestRule
            .onNodeWithTag("counter_text")
            .assertTextEquals("3")
    }
}