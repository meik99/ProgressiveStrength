package com.rynkbit.progressivestrength;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.rynkbit.progressivestrength.db.sqlite.DBHelper;
import com.rynkbit.progressivestrength.exercise.MananageExercisesActivity;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAddDay;
    private RecyclerView listDays;
    private Button btnManageExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenHelperManager.setHelper(new DBHelper(this));

        fabAddDay = findViewById(R.id.fabAddDays);
        listDays = findViewById(R.id.listDays);
        btnManageExercises = findViewById(R.id.btnManageExercises);

        btnManageExercises.setOnClickListener((view) -> {
            startActivity(
                    new Intent(view.getContext(), MananageExercisesActivity.class)
            );
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(OpenHelperManager.getHelper(this, DBHelper.class) != null){
            OpenHelperManager.releaseHelper();
        }
    }
}
