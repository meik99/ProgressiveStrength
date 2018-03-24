package com.rynkbit.progressivestrength;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rynkbit.progressivestrength.db.sqlite.facade.ExerciseFacade;
import com.rynkbit.progressivestrength.db.sqlite.facade.Facade;
import com.rynkbit.progressivestrength.entity.Exercise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by michael on 24.03.18.
 */
@RunWith(AndroidJUnit4.class)
public class ExerciseFacadeTest{

    private Context instrumentationContext;

    @Before
    public void initContext(){
        instrumentationContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void isExerciseFacadeFromFacade(){
        ExerciseFacade exerciseFacade = new ExerciseFacade(instrumentationContext);

        assertNotNull(exerciseFacade);
        assertTrue(exerciseFacade instanceof Facade);
    }

    @Test
    public void findExercises(){
        ExerciseFacade exerciseFacade = new ExerciseFacade(instrumentationContext);

        assertNotNull(exerciseFacade);
        assertTrue(exerciseFacade instanceof Facade);

        List<Exercise> exercises = exerciseFacade.findAll();

        assertNotNull(exercises);
        assertEquals(0, exercises.size());

        exercises.add(new Exercise());

        exercises = exerciseFacade.findAll();

        assertNotNull(exercises);
        assertEquals(0, exercises.size());
    }
}
