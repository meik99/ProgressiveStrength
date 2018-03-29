package com.rynkbit.progressivestrength.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michael on 24.03.18.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "progressivestrength.sqlite";
    private static final int VERSION = 15;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
