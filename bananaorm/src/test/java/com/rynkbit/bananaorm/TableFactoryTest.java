package com.rynkbit.bananaorm;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableFactoryTest {
    private static final String EXERCISE_TABLE = "CREATE TABLE EXERCISE(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,repetions INTEGER,sets INTEGER,weight REAL);";
    private static final String DAY_TABLE = "CREATE TABLE DAY(id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT);CREATE TABLE DayExercise(DayId INTEGER,ExerciseId INTEGER);";

    @Test
    public void createExerciseTable(){
        String exerciseTable = TableFactory.createTableQuery(Exercise.class);
        assertEquals(EXERCISE_TABLE, exerciseTable);
    }

    @Test
    public void createDayTable(){
        String dayTable = TableFactory.createTableQuery(Day.class);
        assertEquals(DAY_TABLE, dayTable);
    }
}
