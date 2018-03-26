package com.rynkbit.progressivestrength.day;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.entity.Exercise;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by michael on 26.03.18.
 */

class ExerciseSpinnerAdapter extends BaseAdapter {
    private final List<Exercise> exercises;

    public ExerciseSpinnerAdapter(Context context, List<Exercise> exercises) {
        super();
        this.exercises = exercises;
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Nullable
    @Override
    public Exercise getItem(int position) {
        return exercises.get(position);
    }
//
//    @Override
//    public int getPosition(@Nullable Exercise item) {
//        return exercises.indexOf(item);
//    }

    @Override
    public long getItemId(int position) {
        return exercises.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Exercise exercise = exercises.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_exercise, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtStats = convertView.findViewById(R.id.txtStats);

        txtName.setText(exercise.getName());
        txtStats.setText(exercise.getStats());

        return convertView;
    }


}
