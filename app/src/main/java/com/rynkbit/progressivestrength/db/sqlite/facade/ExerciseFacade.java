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

public class ExerciseFacade implements Facade {
    private final Context context;

    public ExerciseFacade(Context context){
        this.context = context;
    }

    public List<Exercise> findAll(){
        try {
            Dao<Exercise, Integer> exercisesDao = OpenHelperManager.getHelper(context, DBHelper.class).getDao(Exercise.class);
            return exercisesDao.queryForAll();
        } catch (SQLException e) {
            return null;
        }
    }
}
