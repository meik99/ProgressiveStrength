package com.rynkbit.progressivestrength.ui.exercise;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rynkbit.progressivestrength.MainActivity;
import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.db.sqlite.facade.ExerciseFacade;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExerciseManagementTest {
    private final int N_EXERCISES = 10;
    private final String[] EXERCISE_NAMES =
            new String[]{"Latzug", "Bankdruecken", "Bizepscurls", "Trizepscurls", "Situps", "Dips", "Klimmzug", "Rudern", "Laufen", "Schwimmen"};
    private final String REPETITIONS = "8";
    private final String SETS = "3";
    private final String WEIGHT = "10.5";


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

    @Test
    public void createOneExercise(){
        onView(withId(R.id.btnManageExercises))
                .perform(click());
        onView(withId(R.id.fabAddExercise))
                .perform(click());
        onView(withId(R.id.editName))
                .perform(typeText(EXERCISE_NAMES[0]));
        onView(withId(R.id.editRepetions))
                .perform(clearText(), typeText(REPETITIONS));
        onView(withId(R.id.editSets))
                .perform(clearText(), typeText(SETS));
        onView(withId(R.id.editWeight))
                .perform(clearText(), typeText(WEIGHT), closeSoftKeyboard());
        onView(withId(R.id.btnAccept))
                .perform(click());
        onView(withId(R.id.txtName))
                .check(matches(isDisplayed()));
        onView(withId(R.id.txtStats))
                .check(matches(isDisplayed()));

        assertEquals(1, new ExerciseFacade(mainActivityActivityTestRule.getActivity()).findAll().size());
    }

    @Test
    public void createManyExercises(){
        onView(withId(R.id.btnManageExercises))
                .perform(click());

        for (String exercise :
                EXERCISE_NAMES) {
            onView(withId(R.id.fabAddExercise))
                    .perform(click());
            onView(withId(R.id.editName))
                    .perform(typeText(exercise));
            onView(withId(R.id.editRepetions))
                    .perform(clearText(), typeText(REPETITIONS));
            onView(withId(R.id.editSets))
                    .perform(clearText(), typeText(SETS));
            onView(withId(R.id.editWeight))
                    .perform(clearText(), typeText(WEIGHT), closeSoftKeyboard());
            onView(withId(R.id.btnAccept))
                    .perform(click());
        }

        assertEquals(EXERCISE_NAMES.length, new ExerciseFacade(mainActivityActivityTestRule.getActivity()).findAll().size());
    }
}
