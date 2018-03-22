package com.rynkbit.progressivestrength.db.memory.repository;

import com.rynkbit.progressivestrength.db.Repository;
import com.rynkbit.progressivestrength.entity.Exercise;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 22.03.18.
 */

public class ExerciseRepository implements Repository<Exercise>{
    private static ExerciseRepository instance;

    private List<Exercise> exercises = new LinkedList<>();

    public static ExerciseRepository getInstance(){
        if(instance == null){
            instance = new ExerciseRepository();
        }
        return instance;
    }

    private ExerciseRepository(){}

    @Override
    public List<Exercise> getAll() {
        return exercises;
    }
}
