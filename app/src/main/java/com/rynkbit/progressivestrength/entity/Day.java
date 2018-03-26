package com.rynkbit.progressivestrength.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 24.03.18.
 */
@DatabaseTable
public class Day {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private Date date;
    @ForeignCollectionField(eager = true)
    private Collection<Exercise> exercise;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Day() {
        exercise = new LinkedList<>();
        this.date = new Date();
    }

    public Day(int id, Exercise... exercises){
        this();

        this.id = id;

        this.exercise.addAll(Arrays.asList(exercises));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Exercise> getExercises() {
        return exercise;
    }

    public void setExercises(List<Exercise> exercise) {
        this.exercise = exercise;
    }
}
