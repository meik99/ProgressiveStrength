package com.rynkbit.progressivestrength.db.memory;

import com.rynkbit.progressivestrength.db.Repository;
import com.rynkbit.progressivestrength.db.memory.repository.ExerciseRepository;
import com.rynkbit.progressivestrength.entity.Exercise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by michael on 22.03.18.
 */

public class ExerciseRepositoryTest {
    private static final int N_EXERCISES = 10000;

    @Test
    public void isExerciseRepositoryARepository(){
        ExerciseRepository exerciseRepository = ExerciseRepository.getInstance();
        assertNotNull(exerciseRepository);
        assertTrue(exerciseRepository instanceof Repository);
    }

    @Test
    public void getAllExercises(){
        ExerciseRepository exerciseRepository = ExerciseRepository.getInstance();

        assertNotNull(exerciseRepository);
        assertNotNull(exerciseRepository.getAll());

        exerciseRepository.getAll().clear();

        assertEquals(0, exerciseRepository.getAll().size());

        for (int i = 0; i < N_EXERCISES; i++){
            exerciseRepository.getAll().add(new Exercise(i, "i: " + i, i % 12, i % 5, i % 20));
        }

        assertEquals(N_EXERCISES, exerciseRepository.getAll().size());
        assertEquals(0, exerciseRepository.getAll().get(0).getId());
    }

}
