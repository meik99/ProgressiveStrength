package com.rynkbit.progressivestrength.db.sqlite.facade;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.entity.Day;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by michael on 26.03.18.
 */

public class DayFacade implements Facade<Day> {
    private final Context context;

    public DayFacade(Context context){
        this.context = context;
    }

    private Dao<Day, Integer> getDao(){
        try {
            Dao<Day, Integer> dao = OpenHelperManager.getHelper(context, DBHelper.class).getDao(Day.class);
            return dao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Day> findAll() {
        try {
            return getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Day findById(int id) {
        try {
            return getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void merge(Day entity) {
        try {
            getDao().createOrUpdate(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
