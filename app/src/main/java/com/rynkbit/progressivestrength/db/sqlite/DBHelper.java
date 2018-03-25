package com.rynkbit.progressivestrength.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rynkbit.progressivestrength.entity.Day;
import com.rynkbit.progressivestrength.entity.DayExercise;
import com.rynkbit.progressivestrength.entity.Exercise;

import java.sql.SQLException;

/**
 * Created by michael on 24.03.18.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "progressivestrength.sqlite";
    private static final int VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Exercise.class);
            TableUtils.createTable(connectionSource, Day.class);
            TableUtils.createTable(connectionSource, DayExercise.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DayExercise.class, false);
            TableUtils.dropTable(connectionSource, Day.class, false);
            TableUtils.dropTable(connectionSource, Exercise.class, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }
}
