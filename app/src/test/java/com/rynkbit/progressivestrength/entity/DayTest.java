package com.rynkbit.progressivestrength.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by michael on 23.03.18.
 */

public class DayTest {
    @Test
    public void dayHasWorkingProperties(){
        Day day = new Day();

        assertNotNull(day);
        assertEquals(0, day.getId());
        assertEquals(new Date().getTime() / 1000, day.getDate().getTime() / 1000);
        assertNotNull(day.getExerciseIds());
        assertEquals(0, day.getExerciseIds().size());

        day = new Day(1, 0, 1, 2);

        assertNotNull(day);
        assertEquals(1, day.getId());
        assertEquals(new Date().getTime() / 1000, day.getDate().getTime() / 1000);
        assertNotNull(day.getExerciseIds());
        assertEquals(3, day.getExerciseIds().size());
        assertEquals(0, (int)day.getExerciseIds().get(0));
        assertEquals(1, (int)day.getExerciseIds().get(1));
        assertEquals(2, (int)day.getExerciseIds().get(2));


        day = new Day();

        day.setId(2);
        day.getExerciseIds().add(0);
        day.getExerciseIds().add(1);
        day.getExerciseIds().add(2);

        assertNotNull(day);
        assertEquals(2, day.getId());
        assertEquals(new Date().getTime() / 1000, day.getDate().getTime() / 1000);
        assertNotNull(day.getExerciseIds());
        assertEquals(3, day.getExerciseIds().size());
        assertEquals(0, (int)day.getExerciseIds().get(0));
        assertEquals(1, (int)day.getExerciseIds().get(1));
        assertEquals(2, (int)day.getExerciseIds().get(2));

    }
}
