package com.rynkbit.bananaorm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Day implements Parcelable, IdEntity {
    private int id;
    private Date date;
    private List<Exercise> exercises;

    protected Day(Parcel in) {
        id = in.readInt();
        date = new Date(in.readLong());
        int size = in.readInt();
        exercises = new LinkedList<>();

        for(int i = 0; i < size; i++){
            exercises.add(
                    (Exercise) in.readParcelable(Exercise.class.getClassLoader())
            );
        }
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Day() {
        exercises = new LinkedList<>();
        this.date = new Date();
    }

    public Day(int id, Exercise... exercises){
        this();

        this.id = id;

        this.exercises.addAll(Arrays.asList(exercises));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercise) {
        this.exercises = exercise;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(date.getTime());
        dest.writeInt(exercises.size());

        for(Exercise e : exercises){
            dest.writeParcelable(e, 0);
        }
    }
}
