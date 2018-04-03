package com.rynkbit.progressivestrength.repository;

import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rynkbit.progressivestrength.MainActivity;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.db.sqlite.repository.ExerciseRepository;
import com.rynkbit.progressivestrength.entity.Exercise;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ExerciseRepositoryTest {
    private final int N_EXERCISES = 100;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule;

    @Before
    public void initTestRule(){
        Intent intent = new Intent();
        mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);
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
        ExerciseRepository exerciseRepository = new ExerciseRepository(mainActivityActivityTestRule.getActivity());

        assertNotNull(exerciseRepository);
        assertNotNull(exerciseRepository.findAll());
        assertEquals(0, exerciseRepository.findAll().size());
    }

    @Test
    public void insertOne(){
        ExerciseRepository exerciseRepository = new ExerciseRepository(mainActivityActivityTestRule.getActivity());
        Exercise exercise = new Exercise(0, "EXERCISE_1", 1, 2, 3);

        assertEquals(0, exercise.getId());

        exercise = exerciseRepository.insert(exercise);

        assertNotNull("Exercise must not be null", exercise);
        assertEquals(1, exercise.getId());
        assertEquals("EXERCISE_1", exercise.getName());
        assertEquals(1, exercise.getRepetitions());
        assertEquals(2, exercise.getSets());
        assertEquals(3, exercise.getWeight(), 0.01);

        assertEquals(1, exerciseRepository.findAll().size());
        assertEquals(1, exerciseRepository.findAll().get(0).getId());
    }

    @Test
    public void findOne(){
        insertOne();

        ExerciseRepository exerciseRepository = new ExerciseRepository(mainActivityActivityTestRule.getActivity());
        Exercise exercise = exerciseRepository.findById(1);

        assertNotNull("Exercise must not be null", exercise);
        assertEquals(1, exercise.getId());
        assertEquals("EXERCISE_1", exercise.getName());
        assertEquals(1, exercise.getRepetitions());
        assertEquals(2, exercise.getSets());
        assertEquals(3, exercise.getWeight(), 0.01);
    }

    @Test
    public void insertMany(){
        ExerciseRepository exerciseRepository = new ExerciseRepository(mainActivityActivityTestRule.getActivity());

        for(int i = 0; i < N_EXERCISES; i++){
            Exercise exercise = new Exercise(0, "EXERCISE_" + i, 1+i, 2+i, 3+i);
            try {
                exercise = exerciseRepository.insert(exercise);
            }catch (SQLiteCantOpenDatabaseException ex){
                System.err.println("cannot insert element " + i);
                ex.printStackTrace();
            }


            assertNotNull("Exercise must not be null", exercise);
            assertEquals((i+1), exercise.getId());
            assertEquals("EXERCISE_" + i, exercise.getName());
            assertEquals(1+i, exercise.getRepetitions());
            assertEquals(2+i, exercise.getSets());
            assertEquals(3+i, exercise.getWeight(), 0.01);
        }

        assertNotNull(exerciseRepository.findAll());
        assertEquals(N_EXERCISES, exerciseRepository.findAll().size());
    }

    @Test
    public void updateOne(){
        insertOne();
        ExerciseRepository exerciseRepository = new ExerciseRepository(mainActivityActivityTestRule.getActivity());
        Exercise exercise = exerciseRepository.findAll().get(0);

        int id = exercise.getId();
        String name = exercise.getName();
        int sets = exercise.getSets();
        int repetitions = exercise.getRepetitions();
        double weight = exercise.getWeight();

        String newName = "new" + name;
        int newSets = sets + 1;
        int newRepetitions = repetitions + 1;
        double newWeight = weight + 1.5;

        exercise.setName(newName);
        exercise.setRepetitions(newRepetitions);
        exercise.setSets(newSets);
        exercise.setWeight(newWeight);

        exercise = exerciseRepository.update(exercise);

        assertNotNull(exercise);
        assertEquals(id, exercise.getId());
        assertEquals(newName, exercise.getName());
        assertEquals(newRepetitions, exercise.getRepetitions());
        assertEquals(newSets, exercise.getSets());
        assertEquals(newWeight, exercise.getWeight(), 0.01);

    }

    @Test
    public void removeOne(){
        insertOne();
        ExerciseRepository exerciseRepository = new ExerciseRepository(mainActivityActivityTestRule.getActivity());
        Exercise exercise = exerciseRepository.findAll().get(0);

        exerciseRepository.remove(exercise);

        assertEquals(0, exerciseRepository.findAll().size());
    }

}
