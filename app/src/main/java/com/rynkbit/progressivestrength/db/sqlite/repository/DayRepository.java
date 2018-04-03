package com.rynkbit.progressivestrength.db.sqlite.repository;

import android.content.Context;

import com.rynkbit.progressivestrength.entity.Day;

import java.util.List;

public class DayRepository implements Repository<Day> {
    private final Context context;

    public DayRepository(Context context){
        this.context = context;
    }

    @Override
    public Day update(Day entity) {
        return null;
    }

    @Override
    public Day insert(Day entity) {
        return null;
    }

    @Override
    public List<Day> findAll() {
        return null;
    }

    @Override
    public Day findById(long id) {
        return null;
    }

    @Override
    public void remove(Day entity) {

    }
}
