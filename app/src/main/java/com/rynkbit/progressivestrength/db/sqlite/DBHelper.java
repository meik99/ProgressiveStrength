package com.rynkbit.progressivestrength.db.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rynkbit.bananaorm.TableFactory;
import com.rynkbit.progressivestrength.entity.Day;
import com.rynkbit.progressivestrength.entity.Exercise;

/**
 * Created by michael on 24.03.18.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "progressivestrength.sqlite";
    private static final int VERSION = 22;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableFactory tableFactory = new TableFactory();

        String[] statements = tableFactory.createTableQuery(Day.class).split(";");

        db.execSQL(tableFactory.createTableQuery(Exercise.class));
        db.execSQL(statements[0]);
        db.execSQL(statements[1]);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TableFactory tableFactory = new TableFactory();

        try {
            String[] statements = tableFactory.dropTableQuery(Day.class).split(";");
            db.execSQL(statements[0]);
            db.execSQL(statements[1]);
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        try {
            db.execSQL(tableFactory.dropTableQuery(Exercise.class));
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        onCreate(db);

    }
}
