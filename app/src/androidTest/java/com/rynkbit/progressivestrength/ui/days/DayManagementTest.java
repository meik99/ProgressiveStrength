package com.rynkbit.progressivestrength.ui.days;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rynkbit.progressivestrength.MainActivity;
import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DayManagementTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void resetDatabase(){
        Intent intent = new Intent();
        mainActivityActivityTestRule.launchActivity(intent);

        DBHelper dbHelper = new DBHelper(mainActivityActivityTestRule.getActivity());
        mainActivityActivityTestRule.getActivity().deleteDatabase(
                dbHelper.getDatabaseName());

    }

    @Before
    public void createExercises(){
        onView(withId(R.id.btnManageExercises))
                .perform(click());
        onView(withId(R.id.fabAddExercise))
                .perform(click());
        onView(withId(R.id.editName))
                .perform(typeText("Latzug"));
        onView(withId(R.id.editRepetions))
                .perform(clearText(), typeText("9"));
        onView(withId(R.id.editSets))
                .perform(clearText(), typeText("3"));
        onView(withId(R.id.editWeight))
                .perform(clearText(), typeText("10.5"), closeSoftKeyboard());
        onView(withId(R.id.btnAccept))
                .perform(click());
        onView(withId(R.id.txtName))
                .check(matches(isDisplayed()));
        onView(withId(R.id.txtStats))
                .check(matches(isDisplayed()));
        pressBack();
    }

    @Test
    public void createOneDay(){
        System.out.printf("test");
    }
}
