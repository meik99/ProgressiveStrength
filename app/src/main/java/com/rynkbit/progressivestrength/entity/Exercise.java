package com.rynkbit.progressivestrength.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.rynkbit.bananaorm.IdEntity;

import java.util.Locale;

public class Exercise implements Parcelable, IdEntity {
    private int id;
    private String name;
    private int repetitions;
    private int sets;
    private double weight;


    public Exercise() {
    }

    public Exercise(int id, String name, int repetions, int sets, double weight) {
        this.id = id;
        this.name = name;
        this.repetitions = repetions;
        this.sets = sets;
        this.weight = weight;
    }

    protected Exercise(Parcel in) {
        id = in.readInt();
        name = in.readString();
        repetitions = in.readInt();
        sets = in.readInt();
        weight = in.readDouble();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetions) {
        this.repetitions = repetions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(repetitions);
        dest.writeInt(sets);
        dest.writeDouble(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Id: %d, Name: %s, Sets: %d, Repetitions: %d, Weight %f", id, name, sets, repetitions, weight);
    }

    public String getStats(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getSets())
                .append(" Sets, ")
                .append(getRepetitions())
                .append(" Reps, ")
                .append(getWeight())
                .append(" kg");
        return stringBuilder.toString();
    }
}
