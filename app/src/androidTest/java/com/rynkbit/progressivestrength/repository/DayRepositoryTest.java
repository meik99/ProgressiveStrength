package com.rynkbit.progressivestrength.repository;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rynkbit.progressivestrength.MainActivity;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.db.sqlite.repository.DayRepository;
import com.rynkbit.progressivestrength.entity.Day;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DayRepositoryTest {
    private final int N_DAY = 100;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initActivity(){
        Intent intent = new Intent();
        mainActivityActivityTestRule.launchActivity(intent);
    }

    @Before
    public void resetDatabase(){
        DBHelper dbHelper = new DBHelper(mainActivityActivityTestRule.getActivity());
        mainActivityActivityTestRule.getActivity().deleteDatabase(
                dbHelper.getDatabaseName());

    }

    @Test
    public void getEmptyList(){
        DayRepository dayRepository = new DayRepository(mainActivityActivityTestRule.getActivity());

        assertNotNull(dayRepository);
        assertNotNull(dayRepository.findAll());
        assertEquals(0, dayRepository.findAll().size());
    }

    @Test
    public void insertOne(){
        Day day = new Day();
        DayRepository dayRepository = new DayRepository(mainActivityActivityTestRule.getActivity());

        day = dayRepository.insert(day);

        assertNotNull(dayRepository.findAll());
        assertEquals(1, dayRepository.findAll().size());
        assertNotNull(day);
        assertEquals(1, day.getId());
        assertEquals(new Date().getTime() / 1000, day.getDate().getTime() / 1000);
    }

    @Test
    public void insertMany(){
        DayRepository dayRepository = new DayRepository(mainActivityActivityTestRule.getActivity());

        for (int i = 0; i < N_DAY; i++){
            Day day = new Day();
            dayRepository.insert(day);
        }

        assertNotNull(dayRepository.findAll());
        assertEquals(N_DAY, dayRepository.findAll().size());
        assertNotNull(dayRepository.findAll().get(0));
        assertNotNull(dayRepository.findAll().get(N_DAY-1));
    }

    @Test
    public void updateOne(){
        DayRepository dayRepository = new DayRepository(mainActivityActivityTestRule.getActivity());
        Day day = new Day();
        Date date = day.getDate();

        day = dayRepository.insert(day);

        assertNotNull(day);
        assertEquals(date.getTime(), day.getDate().getTime());

        day.setDate(new Date(0));

        day = dayRepository.update(day);

        assertNotNull(day);
        assertNotEquals(date.getTime(), day.getDate().getTime());
    }

    @Test
    public void removeOne(){
        insertOne();
        DayRepository dayRepository = new DayRepository(mainActivityActivityTestRule.getActivity());

        Day day = dayRepository.findAll().get(0);

        assertEquals(1, dayRepository.findAll().size());

        dayRepository.remove(day);
        assertEquals(0, dayRepository.findAll().size());
    }

    @Test
    public void findById(){
        insertOne();
        DayRepository dayRepository = new DayRepository(mainActivityActivityTestRule.getActivity());
        Day day = dayRepository.findById(dayRepository.findAll().get(0).getId());

        assertNotNull(day);
        assertEquals(dayRepository.findAll().get(0).getId(), day.getId());
    }
}
