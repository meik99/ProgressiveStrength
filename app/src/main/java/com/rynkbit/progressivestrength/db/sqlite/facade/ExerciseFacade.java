package com.rynkbit.progressivestrength.db.sqlite.facade;

import android.content.Context;

import com.rynkbit.progressivestrength.entity.Exercise;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 24.03.18.
 */

public class ExerciseFacade implements Facade<Exercise> {
    private final Context context;

    public ExerciseFacade(Context context){
        this.context = context;
    }

    @Override
    public List<Exercise> findAll() {
        return new LinkedList<>();
    }

    @Override
    public Exercise findById(int id) {
        return new Exercise();
    }

    @Override
    public void merge(Exercise entity) {

    }

    @Override
    public void remove(int id) {

    }
}
