package com.rynkbit.progressivestrength.db.sqlite.facade;

import android.content.Context;

import com.rynkbit.progressivestrength.db.sqlite.repository.ExerciseRepository;
import com.rynkbit.progressivestrength.entity.Exercise;

import java.util.List;

/**
 * Created by michael on 24.03.18.
 */

public class ExerciseFacade implements Facade<Exercise> {
    private final Context context;
    private final ExerciseRepository exerciseRepository;

    public ExerciseFacade(Context context){
        this.context = context;
        exerciseRepository = new ExerciseRepository(context);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findById(int id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public void merge(Exercise entity) {
        if(exerciseRepository.findById(entity.getId()) != null){
            exerciseRepository.update(entity);
        }else{
            exerciseRepository.insert(entity);
        }
    }

    @Override
    public void remove(int id) {
        exerciseRepository.remove(new Exercise(id, null, 0, 0, 0));
    }
}
