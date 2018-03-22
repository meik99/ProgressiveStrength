package com.rynkbit.progressivestrength.exercise;

import com.rynkbit.progressivestrength.entity.Exercise;

/**
 * Created by michael on 22.03.18.
 */

public interface ExerciseModelListener {
    void onExerciseModelChanged(Exercise exercise);
}
