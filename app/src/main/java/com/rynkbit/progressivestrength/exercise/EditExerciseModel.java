package com.rynkbit.progressivestrength.exercise;

import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.rynkbit.progressivestrength.R;
import com.rynkbit.progressivestrength.entity.Exercise;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 22.03.18.
 */

public class EditExerciseModel implements View.OnKeyListener {
    private Exercise exercise;
    private List<ExerciseModelListener> listeners;

    public void addExerciseModelListener(ExerciseModelListener listener) {
        listeners.add(listener);
    }

    public void removeExerciseModelListener(ExerciseModelListener listener) {
        listeners.remove(listener);
    }

    public EditExerciseModel() {
        listeners = new LinkedList<>();
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;

        for (ExerciseModelListener listener :
                listeners) {
            listener.onExerciseModelChanged(exercise);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (v instanceof EditText) {
            EditText editText = (EditText) v;
            try {
                switch (editText.getId()) {
                    case R.id.editName:
                        exercise.setName(editText.getText().toString());
                        break;
                    case R.id.editRepetions:
                        exercise.setRepetions(
                                Integer.parseInt(
                                        editText.getText().toString()));
                        break;
                    case R.id.editSets:
                        exercise.setSets(
                                Integer.parseInt(
                                        editText.getText().toString()));
                        break;
                    case R.id.editWeight:
                        exercise.setWeight(
                                Double.parseDouble(
                                        editText.getText().toString()));
                        break;
                }
            } catch (NumberFormatException ex) {
                editText.setError(editText.getContext().getString(R.string.wrong_number));
            }
        }
        return false;
    }
}
