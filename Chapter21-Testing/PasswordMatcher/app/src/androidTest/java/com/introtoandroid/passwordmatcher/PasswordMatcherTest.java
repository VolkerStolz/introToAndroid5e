package com.introtoandroid.passwordmatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private static final String GOOD_PASSWORD = "abc123\n";
    private static final String BAD_PASSWORD = "sbc123\n";

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
            onView(withId(R.id.password)).check(matches(withHint(R.string.password)));
            onView(withId(R.id.password)).check(matches(withInputType(129)));

            onView(withId(R.id.matchingPassword)).check(matches(withText(EMPTY_STRING)));
            onView(withId(R.id.matchingPassword)).check(matches(withHint(R.string.matching_password)));
            onView(withId(R.id.matchingPassword)).check(matches(withInputType(129)));

            onView(withId(R.id.matchButton)).check(matches(withText(R.string.match_password_button)));
            onView(withId(R.id.passwordResult)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.forViewVisibility(View.GONE))));
    }

    @Test
    public void testMatchingPasswords() {
        onView(withId(R.id.password)).perform(ViewActions.typeText(GOOD_PASSWORD));
        onView(withId(R.id.matchingPassword)).perform(ViewActions.typeText(GOOD_PASSWORD));
        onView(withId(R.id.matchButton)).perform(ViewActions.click());
        onView(withId(R.id.password)).check(matches(withText("abc123")));
        onView(withId(R.id.matchingPassword)).check(matches(withText("abc123")));

        activityScenario.onActivity(a -> {
            TextView pwView = a.findViewById(R.id.password);
            TextView matchView = a.findViewById(R.id.matchingPassword);
            assertEquals(pwView.getText().toString(),matchView.getText().toString());
        });

        onView(withId(R.id.passwordResult)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.forViewVisibility(View.VISIBLE))));
        onView(withId(R.id.passwordResult)).check(matches(withText(R.string.passwords_match_notice)));
        onView(withId(R.id.passwordResult)).check(matches(hasTextColor(R.color.green)));
    }

    @Test
    public void testEmptyPasswords() {
        onView(withId(R.id.password)).perform(ViewActions.typeText(EMPTY_STRING));
        onView(withId(R.id.matchingPassword)).perform(ViewActions.typeText(EMPTY_STRING));
        onView(withId(R.id.matchButton)).perform(ViewActions.click());
        onView(withId(R.id.password)).check(matches(withText(EMPTY_STRING)));
        onView(withId(R.id.matchingPassword)).check(matches(withText(EMPTY_STRING)));

        activityScenario.onActivity(a -> {
            TextView pwView = a.findViewById(R.id.password);
            TextView matchView = a.findViewById(R.id.matchingPassword);
            assertEquals(pwView.getText().toString(),matchView.getText().toString());
        });

        onView(withId(R.id.passwordResult)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.forViewVisibility(View.VISIBLE))));
        onView(withId(R.id.passwordResult)).check(matches(withText(R.string.passwords_do_not_match_notice)));
        onView(withId(R.id.passwordResult)).check(matches(hasTextColor(R.color.red)));
    }

    @Test
    public void testNotEqualPasswords() {
        onView(withId(R.id.password)).perform(ViewActions.typeText(GOOD_PASSWORD));
        onView(withId(R.id.matchingPassword)).perform(ViewActions.typeText(BAD_PASSWORD));
        onView(withId(R.id.matchButton)).perform(ViewActions.click());

        activityScenario.onActivity(a -> {
            TextView pwView = a.findViewById(R.id.password);
            TextView matchView = a.findViewById(R.id.matchingPassword);
            assertNotEquals(pwView.getText().toString(),matchView.getText().toString());
        });

        onView(withId(R.id.passwordResult)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.forViewVisibility(View.VISIBLE))));
        onView(withId(R.id.passwordResult)).check(matches(withText(R.string.passwords_do_not_match_notice)));
        onView(withId(R.id.passwordResult)).check(matches(hasTextColor(R.color.red)));
    }
}
