package com.rynkbit.progressivestrength.day;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.db.sqlite.facade.DayFacade;
import com.rynkbit.progressivestrength.db.sqlite.facade.ExerciseFacade;
import com.rynkbit.progressivestrength.entity.Day;
import com.rynkbit.progressivestrength.entity.Exercise;
import com.rynkbit.progressivestrength.exercise.ExerciseListAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DayActivity extends AppCompatActivity {
    public static final String EXTRA_DAY = "day";

    private DayModel dayModel;

    private TextView txtDate;
    private Spinner spinnerExercises;
    private Button btnAddExercise;
    private Button btnAccept;
    private RecyclerView listExercises;
    private ExerciseListAdapter exerciseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dddd dd.MMMM.yyyy", Locale.getDefault());
        dayModel = new DayModel();

        txtDate = findViewById(R.id.txtDate);
        listExercises = findViewById(R.id.listExercises);
        spinnerExercises = findViewById(R.id.spinnerExercises);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        btnAccept = findViewById(R.id.btnAccept);

        if(getIntent().hasExtra(EXTRA_DAY)){
            dayModel.setDay(getIntent().getParcelableExtra(EXTRA_DAY));

            if(dayModel.getDay() == null){
                dayModel.setDay(new Day());
            }
        }else{
            dayModel.setDay(new Day());
        }

        exerciseListAdapter = new ExerciseListAdapter(dayModel.getDay().getExercises());

        txtDate.setText(simpleDateFormat.format(dayModel.getDay().getDate()));

        listExercises.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        listExercises.setAdapter(exerciseListAdapter);

        spinnerExercises.setAdapter(new ExerciseSpinnerAdapter(this, new ExerciseFacade(this).findAll()));

        btnAddExercise.setOnClickListener((view) -> {
            dayModel.getDay().getExercises().add((Exercise) spinnerExercises.getSelectedItem());
            exerciseListAdapter.setExercises(dayModel.getDay().getExercises());
        });

        btnAccept.setOnClickListener((view) -> {
            new DayFacade(view.getContext()).merge(dayModel.getDay());
            finish();
        });
    }
}
