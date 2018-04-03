package com.rynkbit.bananaorm;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableFactoryTest {
    private static final String CREATE_EXERCISE_TABLE = "CREATE TABLE EXERCISE(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,repetitions INTEGER,sets INTEGER,weight REAL);";
    private static final String CREATE_DAY_TABLE = "CREATE TABLE DAY(id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT);CREATE TABLE DAY_EXERCISE(DayId INTEGER,ExerciseId INTEGER);";

    private static final String DROP_EXERCISE_TABLE = "DROP TABLE IF EXISTS EXERCISE;";
    private static final String DROP_DAY_TABLE = "DROP TABLE IF EXISTS DAY_EXERCISE;DROP TABLE IF EXISTS DAY;";

    @Test
    public void createExerciseTable(){
        String exerciseTable = new TableFactory().createTableQuery(Exercise.class);
        assertEquals(CREATE_EXERCISE_TABLE, exerciseTable);
    }

    @Test
    public void createDayTable(){
        String dayTable = new TableFactory().createTableQuery(Day.class);
        assertEquals(CREATE_DAY_TABLE, dayTable);
    }

    @Test
    public void dropExerciseTable(){
        assertEquals(DROP_EXERCISE_TABLE, new TableFactory().dropTableQuery(Exercise.class));
    }

    @Test
    public void dropDayTable(){
        assertEquals(DROP_DAY_TABLE, new TableFactory().dropTableQuery(Day.class));
    }
}
