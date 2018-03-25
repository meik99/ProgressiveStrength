package com.rynkbit.progressivestrength.db.sqlite.facade;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.entity.Exercise;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by michael on 24.03.18.
 */

public class ExerciseFacade implements Facade<Exercise> {
    private final Context context;

    public ExerciseFacade(Context context){
        this.context = context;
    }

    private Dao<Exercise, Integer> getDao(){
        try {
            Dao<Exercise, Integer> exercisesDao = OpenHelperManager.getHelper(context, DBHelper.class).getDao(Exercise.class);
            return exercisesDao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Exercise> findAll(){
        Dao<Exercise, Integer> exerciseDao = getDao();
        try {
            return exerciseDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void merge(Exercise exercise){
        try {
            getDao().createOrUpdate(exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise findById(int id){
        try {
            return getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(int id){
        try {
            getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
