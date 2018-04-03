package com.rynkbit.progressivestrength.db.sqlite.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.entity.Exercise;

import java.util.LinkedList;
import java.util.List;

public class ExerciseRepository implements Repository<Exercise>{
    private final String TABLE = "EXERCISE";
    private final Context context;

    public ExerciseRepository(Context context){
        this.context = context;
    }

    @Override
    public Exercise insert(Exercise exercise){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", exercise.getName());
        contentValues.put("sets", exercise.getSets());
        contentValues.put("repetitions", exercise.getRepetitions());
        contentValues.put("weight", exercise.getWeight());

        long id = new DBHelper(context)
                .getWritableDatabase()
                .insert(TABLE, null, contentValues);

        return findById(id);
    }

    @Override
    public List<Exercise> findAll(){
        List<Exercise> result = new LinkedList<>();
        Cursor cursor = new DBHelper(context)
                .getReadableDatabase()
                .query(TABLE,
                        null, null, null,
                        null, null, null);

        if(cursor.moveToFirst()){
            do{
                result.add(fromCursor(cursor));
            }while (cursor.moveToNext());
        }

        cursor.close();

        return result;
    }

    @Override
    public void remove(Exercise entity) {
        new DBHelper(context)
                .getWritableDatabase()
                .delete(
                        TABLE,
                        "id = ?",
                        new String[]{String.valueOf(entity.getId())}
                );
    }

    @Override
    public Exercise update(Exercise exercise){
        new DBHelper(context)
                .getWritableDatabase()
                .update(TABLE,
                        getContentValues(exercise, false),
                        "id = ?",
                        new String[]{String.valueOf(exercise.getId())});
        return findById(exercise.getId());
    }

    private ContentValues getContentValues(Exercise exercise, boolean withId){
        ContentValues contentValues = new ContentValues();

        if(withId == true){
            contentValues.put("id", exercise.getId());
        }

        contentValues.put("name", exercise.getName());
        contentValues.put("sets", exercise.getSets());
        contentValues.put("repetitions", exercise.getRepetitions());
        contentValues.put("weight", exercise.getWeight());

        return contentValues;
    }

    private Exercise fromCursor(Cursor cursor){
        return new Exercise(
                cursor.getInt(
                    cursor.getColumnIndex("id")
                ),
                cursor.getString(
                        cursor.getColumnIndex("name")
                ),
                cursor.getInt(
                        cursor.getColumnIndex("repetitions")
                ),
                cursor.getInt(
                        cursor.getColumnIndex("sets")
                ),
                cursor.getDouble(
                        cursor.getColumnIndex("weight")
                ));
    }

    @Override
    public Exercise findById(long id) {
        Exercise result = null;
        Cursor cursor = new DBHelper(context)
                .getReadableDatabase()
                .query(TABLE,
                        null,
                        "id = ?",
                        new String[]{String.valueOf(id)},
                        null, null, null);

        if(cursor.moveToFirst()){
            result = fromCursor(cursor);
        }

        cursor.close();

        return result;
    }
}
