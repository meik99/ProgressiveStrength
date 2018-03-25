package com.rynkbit.progressivestrength.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Locale;

/**
 * Created by michael on 22.03.18.
 */
@DatabaseTable
public class Exercise implements Parcelable{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int repetions;
    @DatabaseField
    private int sets;
    @DatabaseField
    private double weight;

    public Exercise() {
    }

    public Exercise(int id, String name, int repetions, int sets, double weight) {
        this.id = id;
        this.name = name;
        this.repetions = repetions;
        this.sets = sets;
        this.weight = weight;
    }

    protected Exercise(Parcel in) {
        id = in.readInt();
        name = in.readString();
        repetions = in.readInt();
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

    public int getRepetions() {
        return repetions;
    }

    public void setRepetions(int repetions) {
        this.repetions = repetions;
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
        dest.writeInt(repetions);
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
        return String.format(Locale.getDefault(), "Id: %d, Name: %s, Sets: %d, Repetitions: %d, Weight %f", id, name, sets, repetions, weight);
    }
}
