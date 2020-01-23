package com.introtoandroid.passwordmatcher;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PasswordMatcherTest {
    private static final String EMPTY_STRING = "";
    private static final String GOOD_PASSWORD = "ABC123";
    private static final String BAD_PASSWORD = "SBC123";

    @Rule
    public ActivityScenarioRule<PasswordMatcherActivity> activityScenarioRule
            = new ActivityScenarioRule<>(PasswordMatcherActivity.class);


    @Test
    public void testPreConditions() {
        onView(withId(R.id.title)).check(matches(withText(R.string.match_passwords_title)));

        onView(withId(R.id.password)).check(matches(withHint(R.string.password)));
        onView(withId(R.id.matchingPassword)).check(matches(withHint(R.string.matching_password)));

        onView(withId(R.id.matchButton)).check(matches(withText(R.string.match_password_button)));

        // TODO:
        // int mpInput = matchingPassword.getInputType();
        // assertEquals(129, mpInput);
        // int visibility = passwordResult.getVisibility();
        // assertEquals(View.GONE, visibility);

    }

    @Test
    public void testMatchingPasswords() {
        ViewInteraction pwView = onView(withId(R.id.password));
        pwView.perform(click());
        pwView.perform(typeText(GOOD_PASSWORD));
        ViewInteraction pwmatchView = onView(withId(R.id.matchingPassword));
        pwmatchView.perform(click());
        pwmatchView.perform(typeText(GOOD_PASSWORD));

        onView(withId(R.id.matchButton)).perform(click());

        pwmatchView.check(matches(withText(GOOD_PASSWORD)));
        onView(withId(R.id.passwordResult)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordResult)).check(matches(withText(R.string.passwords_match_notice)));
        onView(withId(R.id.passwordResult)).check(matches(hasTextColor(R.color.red)));
    }

    @Test
    public void testEmptyPasswords() {
        onView(withId(R.id.password)).perform(click());
        onView(withId(R.id.password)).perform(typeText(EMPTY_STRING));
        onView(withId(R.id.matchingPassword)).perform(click());
        onView(withId(R.id.matchingPassword)).perform(typeText(EMPTY_STRING));

        onView(withId(R.id.matchButton)).perform(click());

        onView(withId(R.id.password)).check(matches(withText(EMPTY_STRING)));
        onView(withId(R.id.matchingPassword)).check(matches(withText(EMPTY_STRING)));
    }

//
//        String p = password.getText().toString();
//        assertEquals(p, EMPTY_STRING);
//
//        String mp = matchingPassword.getText().toString();
//        assertEquals(mp, EMPTY_STRING);
//
//        assertEquals(p, mp);
//
//        int visibility = passwordResult.getVisibility();
//        assertEquals(View.VISIBLE, visibility);
//
//        String notice = passwordResult.getText().
//        ();
//        assertEquals(passwordMatcherActivity.getResources().getString(R.string.passwords_do_not_match_notice), notice);
//
//        int noticeColor = passwordResult.getCurrentTextColor();
//        assertEquals(passwordMatcherActivity.getResources().getColor(R.color.red), noticeColor);
//    }
//
//    public void testNotEqualPasswords() {
//        TouchUtils.clickView(this, password);
//        sendKeys(GOOD_PASSWORD);
//
//        TouchUtils.clickView(this, matchingPassword);
//        sendKeys(BAD_PASSWORD);
//
//        TouchUtils.clickView(this, button);
//
//        String good = password.getText().toString();
//        assertEquals("abc123", good);
//
//        String bad = matchingPassword.getText().toString();
//        assertEquals("sbc123", bad);
//
//        assertTrue("Passwords should not match", (!good.equals(bad)));
//
//        int visibility = passwordResult.getVisibility();
//        assertEquals(View.VISIBLE, visibility);
//
//        String notice = passwordResult.getText().toString();
//        assertEquals(passwordMatcherActivity.getResources().getString(R.string.passwords_do_not_match_notice), notice);
//
//        int noticeColor = passwordResult.getCurrentTextColor();
//        assertEquals(passwordMatcherActivity.getResources().getColor(R.color.red), noticeColor);
//    }

}
