package com.rynkbit.progressivestrength.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 24.03.18.
 */

public class Day {
    private int id;
    private List<Integer> exerciseIds;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Day() {
        exerciseIds = new LinkedList<>();
        this.date = new Date();
    }

    public Day(int id, int... exerciseIds){
        this();

        this.id = id;

        for(int exerciseId : exerciseIds){
            this.exerciseIds.add(exerciseId);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<Integer> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }
}
