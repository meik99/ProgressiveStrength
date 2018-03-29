package com.rynkbit.progressivestrength.db.sqlite.facade;

import android.content.Context;

import com.rynkbit.progressivestrength.entity.Day;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 26.03.18.
 */

public class DayFacade implements Facade<Day> {
    private final Context context;

    public DayFacade(Context context){
        this.context = context;
    }


    @Override
    public List<Day> findAll() {
        return new LinkedList<>();
    }

    @Override
    public Day findById(int id) {
        return new Day();
    }

    @Override
    public void merge(Day entity) {

    }

    @Override
    public void remove(int id) {

    }
}
