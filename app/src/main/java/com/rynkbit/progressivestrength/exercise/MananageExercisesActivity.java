package com.rynkbit.progressivestrength.exercise;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.exercise.edit.EditExerciseActivity;

public class MananageExercisesActivity extends AppCompatActivity {

    ExerciseListAdapter exerciseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mananage_exercises);

        FloatingActionButton fabAddExercise = findViewById(R.id.fabAddExercise);
        RecyclerView listExercises = findViewById(R.id.listExercises);
        exerciseListAdapter = new ExerciseListAdapter();

        fabAddExercise.setOnClickListener((v) -> startActivity(
                new Intent(v.getContext(), EditExerciseActivity.class)));

        listExercises.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listExercises.setAdapter(exerciseListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        exerciseListAdapter.notifyDataSetChanged();
    }
}
