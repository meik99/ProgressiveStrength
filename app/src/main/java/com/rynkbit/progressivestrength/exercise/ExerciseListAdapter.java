package com.rynkbit.progressivestrength.exercise;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.db.memory.repository.ExerciseRepository;
import com.rynkbit.progressivestrength.entity.Exercise;

/**
 * Created by michael on 23.03.18.
 */

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{
    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false);
        ExerciseViewHolder viewHolder = new ExerciseViewHolder(v);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return ExerciseRepository.getInstance().getAll().size();
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        Exercise exercise = ExerciseRepository
                .getInstance()
                .getAll()
                .get(position);

        stringBuilder
                .append(exercise.getWeight())
                .append(" kg, ")
                .append(exercise.getRepetions())
                .append(" Reps, ")
                .append(exercise.getSets())
                .append(" Sets");

        holder.txtName.setText(exercise.getName());
        holder.txtStats.setText(stringBuilder);
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
