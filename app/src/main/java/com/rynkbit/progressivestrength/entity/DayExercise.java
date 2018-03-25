package com.rynkbit.progressivestrength.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by michael on 25.03.18.
 */
@DatabaseTable
public class DayExercise {
    @DatabaseField
    private int dayId;
    @DatabaseField
    private int exerciseId;
}
