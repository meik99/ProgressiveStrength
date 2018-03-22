package com.rynkbit.progressivestrength.exercise;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rynkbit.progressivestrength.MainActivity;
import com.rynkbit.progressivestrength.R;

public class MananageExercisesActivity extends AppCompatActivity {

    private FloatingActionButton fabAddExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mananage_exercises);

        fabAddExercise = findViewById(R.id.fabAddExercise);

        fabAddExercise.setOnClickListener((v) -> startActivity(
                new Intent(v.getContext(), EditExerciseActivity.class)));
    }
}
