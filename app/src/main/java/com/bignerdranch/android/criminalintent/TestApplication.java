package com.bignerdranch.android.criminalintent;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.contrib.PickerActions;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by GV on 11/21/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestApplication {

    @Rule
    public ActivityTestRule<CrimeListActivity> mActivityRule = new ActivityTestRule<>(
            CrimeListActivity.class);

    @Test
    public void runThroughTest() {

        onView(withId(R.id.crime_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.crime_date), isDisplayed())).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2015, 12, 20));
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20, 52));
        onView(withText(containsString("OK"))).perform(click());

        //goes to the list view
        pressBack();
        //rotates the phone
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //returns to the item view
        onView(withId(R.id.crime_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        //verify that a date selected in the dialog propagates to the item view and is maintained
        onView(allOf(withId(R.id.crime_date), isDisplayed())).check(matches(withText(containsString("SUN DEC 20 20:52:00 EST 2015"))));
    }
}