package com.introtoandroid.passwordmatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PasswordMatcherTest {
    private static final String EMPTY_STRING = "";
    private static final String GOOD_PASSWORD = "A B C 1 2 3 ENTER";
    private static final String BAD_PASSWORD = "S B C 1 2 3 ENTER";

    @Rule
    public ActivityScenarioRule<PasswordMatcherActivity> activityRule = new ActivityScenarioRule<>(PasswordMatcherActivity.class);
    private ActivityScenario<PasswordMatcherActivity> activityScenario;

    @Before
    public void setUp() throws Exception {
        activityScenario = activityRule.getScenario();
    }

    @Test
    public void testPreConditions() {
            onView(withId(R.id.title)).check(matches(withText(R.string.match_passwords_title)));
            onView(withId(R.id.password)).check(matches(withText(EMPTY_STRING)));
            onView(withId(R.id.password)).perform(ViewActions.typeText("password"));
            onView(withId(R.id.password)).check(matches(withHint(R.string.password)));
            onView(withId(R.id.password)).check(matches(withInputType(129)));

            onView(withId(R.id.matchingPassword)).check(matches(withText(EMPTY_STRING)));
            onView(withId(R.id.matchingPassword)).check(matches(withHint(R.string.matching_password)));
            onView(withId(R.id.matchingPassword)).check(matches(withInputType(129)));

            onView(withId(R.id.matchButton)).check(matches(withText(R.string.match_password_button)));
            onView(withId(R.id.passwordResult)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.forViewVisibility(View.GONE))));
    }

//    public void testMatchingPasswords() {
//        TouchUtils.clickView(this, password);
//        sendKeys(GOOD_PASSWORD);
//
//        TouchUtils.clickView(this, matchingPassword);
//        sendKeys(GOOD_PASSWORD);
//
//        TouchUtils.clickView(this, button);
//
//        String p = password.getText().toString();
//        assertEquals("abc123", p);
//
//        String mp = matchingPassword.getText().toString();
//        assertEquals("abc123", mp);
//
//        assertEquals(p, mp);
//
//        int visibility = passwordResult.getVisibility();
//        assertEquals(View.VISIBLE, visibility);
//
//        String notice = passwordResult.getText().toString();
//        assertEquals(passwordMatcherActivity.getResources().getString(R.string.passwords_match_notice), notice);
//
//        int noticeColor = passwordResult.getCurrentTextColor();
//        assertEquals(passwordMatcherActivity.getResources().getColor(R.color.green), noticeColor);
//    }
//
//    public void testEmptyPasswords() {
//        TouchUtils.clickView(this, password);
//        sendKeys(EMPTY_STRING);
//
//        TouchUtils.clickView(this, matchingPassword);
//        sendKeys(EMPTY_STRING);
//
//        TouchUtils.clickView(this, button);
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
//        String notice = passwordResult.getText().toString();
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
