package com.rynkbit.progressivestrength.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by michael on 22.03.18.
 */

public class ExerciseTest {
    @Test
    public void exerciseHasWorkingProperties(){
        Exercise exercise = new Exercise();

        assertEquals(0, exercise.getId());
        assertEquals(0, exercise.getSets());
        assertEquals(0, exercise.getRepetitions());
        assertEquals(0, exercise.getWeight(), 0.01);
        assertEquals(null, exercise.getName());

        exercise = new Exercise(1, "Übung", 12, 4, 14.5);

        assertEquals(1, exercise.getId());
        assertEquals(4, exercise.getSets());
        assertEquals(12, exercise.getRepetitions());
        assertEquals(14.5, exercise.getWeight(), 0.01);
        assertEquals("Übung", exercise.getName());

        exercise = new Exercise();

        exercise.setId(2);
        exercise.setName("Übung 2");
        exercise.setSets(12);
        exercise.setRepetitions(4);
        exercise.setWeight(10);

        assertEquals(2, exercise.getId());
        assertEquals(12, exercise.getSets());
        assertEquals(4, exercise.getRepetitions());
        assertEquals(10, exercise.getWeight(), 0.01);
        assertEquals("Übung 2", exercise.getName());
    }
}
