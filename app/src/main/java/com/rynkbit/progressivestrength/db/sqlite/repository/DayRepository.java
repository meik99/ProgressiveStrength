package com.rynkbit.progressivestrength.db.sqlite.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.entity.Day;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DayRepository implements Repository<Day> {
    private final String TABLE = "DAY";
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
        return  findById(
                    new DBHelper(context)
                        .getWritableDatabase()
                        .insert(TABLE, null, getContentValues(entity, false)));
    }

    @Override
    public List<Day> findAll() {
        List<Day> days = new LinkedList<>();
        Cursor cursor = new DBHelper(context)
                .getReadableDatabase()
                .query(TABLE, null, null,
                        null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                days.add(fromCursor(cursor));
            }while (cursor.moveToNext());
        }

        return days;
    }


    @Override
    public Day findById(long id) {
        Cursor cursor = new DBHelper(context)
                .getReadableDatabase()
                .query(TABLE, null,
                        "id = ?", new String[]{String.valueOf(id)},
                        null, null, null);

        if(cursor.moveToFirst()){
            return fromCursor(cursor);
        }
        return null;
    }

    @Override
    public void remove(Day entity) {

    }

    private ContentValues getContentValues(Day day, boolean withId){
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        if(withId == true){
            contentValues.put("id", day.getId());
        }

        contentValues.put("date", simpleDateFormat.format(day.getDate()));

        return contentValues;
    }

    private Day fromCursor(Cursor cursor){
        Day day = new Day(
                cursor.getInt(
                        cursor.getColumnIndex("id")
                ),
                null
        );
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            day.setDate(
                    sdf.parse(
                            cursor.getString(
                                    cursor.getColumnIndex("date")
                            )
                    )
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return day;
    }
}
