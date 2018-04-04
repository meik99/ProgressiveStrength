package com.rynkbit.progressivestrength.db.sqlite.facade;

import android.content.Context;

import com.rynkbit.progressivestrength.db.sqlite.repository.DayRepository;
import com.rynkbit.progressivestrength.entity.Day;
import com.rynkbit.progressivestrength.entity.Exercise;

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
        return new DayRepository(context).findAll();
    }

    @Override
    public Day findById(int id) {
        return new DayRepository(context).findById(id);
    }

    @Override
    public void merge(Day entity) {
        DayRepository dayRepository = new DayRepository(context);

        if(dayRepository.findById(entity.getId()) != null){
            dayRepository.update(entity);
        }else{
            dayRepository.insert(entity);
        }
    }

    @Override
    public void remove(int id) {
        new DayRepository(context).remove(
                new Day(id, (Exercise[])null)
        );
    }
}
