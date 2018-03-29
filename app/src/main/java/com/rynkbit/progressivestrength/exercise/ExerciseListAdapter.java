package com.rynkbit.progressivestrength.exercise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.db.memory.repository.ExerciseRepository;
import com.rynkbit.progressivestrength.db.sqlite.facade.ExerciseFacade;
import com.rynkbit.progressivestrength.entity.Exercise;
import com.rynkbit.progressivestrength.exercise.edit.EditExerciseActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by michael on 23.03.18.
 */

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{
    private Collection<Exercise> exercises;

    public ExerciseListAdapter(Collection<Exercise> exercises) {
        this.exercises = exercises;

    }

    public void setExercises(Collection<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public Collection<Exercise> getExercises() {
        return exercises;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        ExerciseViewHolder viewHolder = new ExerciseViewHolder(v);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        Exercise exercise = (Exercise) exercises.toArray()[position];

        holder.txtName.setText(exercise.getName());
        holder.txtStats.setText(exercise.getStats());


        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), EditExerciseActivity.class);
            intent.putExtra(EditExerciseActivity.EXTRA_EXERCISE, exercise);
            view.getContext().startActivity(intent);
        });
        holder.itemView.setOnLongClickListener((view) -> {
            exercises.remove(exercise);
            notifyDataSetChanged();
            return false;
        });
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtStats;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtStats = itemView.findViewById(R.id.txtStats);
        }
    }
}
