package com.dat153.passwordmatchercompose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PasswordMatcherTest {

    companion object {
        private const val EMPTY_STRING = ""
        private const val GOOD_PASSWORD = "abc123"
        private const val BAD_PASSWORD = "sbc123"
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // --- Pre-conditions ---

    @Test
    fun testPreConditions() {
        // Title is shown
        composeTestRule.onNodeWithTag("title")
            .assertIsDisplayed()
            .assertTextEquals("Match Passwords")

        // Password field is empty
        composeTestRule.onNodeWithTag("password")
            .assertIsDisplayed()
            .assert(hasText(EMPTY_STRING))

        // Confirm password field is empty
        composeTestRule.onNodeWithTag("matchingPassword")
            .assertIsDisplayed()
            .assert(hasText(EMPTY_STRING))

        // Result text is not shown yet
        composeTestRule.onNodeWithTag("passwordResult")
            .assertDoesNotExist()
    }

    // --- Matching passwords ---

    @Test
    fun testMatchingPasswords() {
        composeTestRule.onNodeWithTag("password")
            .performTextInput(GOOD_PASSWORD)
        composeTestRule.onNodeWithTag("matchingPassword")
            .performTextInput(GOOD_PASSWORD)
        composeTestRule.onNodeWithTag("matchButton")
            .performClick()

        // Both fields still hold the correct input value underneath the masking dots
        composeTestRule.onNodeWithTag("password")
            .assert(hasText(GOOD_PASSWORD, substring = false))
        composeTestRule.onNodeWithTag("matchingPassword")
            .assert(hasText(GOOD_PASSWORD, substring = false))

        // Result is visible and shows the match message
        composeTestRule.onNodeWithTag("passwordResult")
            .assertIsDisplayed()
            .assertTextEquals("Passwords match!")

        // Result uses green color (checked via content description set on the Text)
        composeTestRule.onNodeWithContentDescription("match_password_notice")
            .assertIsDisplayed()
    }

    // --- Empty passwords ---

    @Test
    fun testEmptyPasswords() {
        // Leave both fields empty and click the button
        composeTestRule.onNodeWithTag("matchButton")
            .performClick()

        composeTestRule.onNodeWithTag("password")
            .assertTextEquals(EMPTY_STRING)
        composeTestRule.onNodeWithTag("matchingPassword")
            .assertTextEquals(EMPTY_STRING)

        composeTestRule.onNodeWithTag("passwordResult")
            .assertIsDisplayed()
            .assertTextEquals("Passwords do not match.")

        composeTestRule.onNodeWithContentDescription("no_match_password_notice")
            .assertIsDisplayed()
    }

    // --- Non-matching passwords ---

    @Test
    fun testNotEqualPasswords() {
        composeTestRule.onNodeWithTag("password")
            .performTextInput(GOOD_PASSWORD)
        composeTestRule.onNodeWithTag("matchingPassword")
            .performTextInput(BAD_PASSWORD)
        composeTestRule.onNodeWithTag("matchButton")
            .performClick()

        composeTestRule.onNodeWithTag("passwordResult")
            .assertIsDisplayed()
            .assertTextEquals("Passwords do not match.")

        composeTestRule.onNodeWithContentDescription("no_match_password_notice")
            .assertIsDisplayed()
    }
}

class Test3 {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLog() {
        // [START android_compose_semantics_print_log]
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("matchingPassword")
        // [END android_compose_semantics_print_log]
        // [START android_compose_semantics_match_text]
        composeTestRule.onRoot(useUnmergedTree = false).printToLog("matchingPassword")
        // [END android_compose_semantics_match_text]
    }
}