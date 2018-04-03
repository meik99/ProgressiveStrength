package com.rynkbit.progressivestrength;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void startApp(){
        MainActivity activity = activityActivityTestRule.getActivity();
        assertNotNull(activity);

        Intent intent = new Intent(activity, MainActivity.class);
        activityActivityTestRule.launchActivity(intent);

        assertNotNull(activity.findViewById(R.id.fabAddDays));
        assertNotNull(activity.findViewById(R.id.listDays));
        assertNotNull(activity.findViewById(R.id.btnManageExercises));
    }
}
