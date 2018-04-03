package com.rynkbit.progressivestrength.exercise.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.db.sqlite.facade.ExerciseFacade;
import com.rynkbit.progressivestrength.entity.Exercise;

public class EditExerciseActivity extends AppCompatActivity implements EditExerciseModelListener, View.OnClickListener {
    public static final String EXTRA_EXERCISE = "exercise";

    private EditText editName;
    private EditText editSets;
    private EditText editRepetions;
    private EditText editWeight;
    private Button btnAccept;
    private EditExerciseModel exerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exercise);


        exerciseModel = new EditExerciseModel();
        exerciseModel.addExerciseModelListener(this);

        editName = findViewById(R.id.editName);
        editSets = findViewById(R.id.editSets);
        editRepetions = findViewById(R.id.editRepetions);
        editWeight = findViewById(R.id.editWeight);
        btnAccept = findViewById(R.id.btnAccept);

        if(getIntent().hasExtra(EXTRA_EXERCISE)){
            exerciseModel.setExercise(getIntent().getParcelableExtra(EXTRA_EXERCISE));
        }else{
            exerciseModel.setExercise(new Exercise());
        }

        editName.setOnKeyListener(exerciseModel);
        editSets.setOnKeyListener(exerciseModel);
        editWeight.setOnKeyListener(exerciseModel);
        editRepetions.setOnKeyListener(exerciseModel);
        btnAccept.setOnClickListener(this);
    }

    @Override
    public void onExerciseModelChanged(Exercise exercise) {
        editName.setText(exercise.getName());
        editSets.setText(String.valueOf(exercise.getSets()));
        editRepetions.setText(String.valueOf(exercise.getRepetitions()));
        editWeight.setText(String.valueOf(exercise.getWeight()));
    }

    @Override
    public void onClick(View v) {
        new ExerciseFacade(this).merge(exerciseModel.getExercise());
        finish();
    }
}
