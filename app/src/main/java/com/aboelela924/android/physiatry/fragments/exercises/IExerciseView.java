package com.aboelela924.android.physiatry.fragments.exercises;

import com.aboelela924.android.physiatry.activites.IBasicView;
import com.aboelela924.android.physiatry.model.dataModeling.Exercise;

import java.util.List;

public interface IExerciseView extends IBasicView {

    void displayExercises(List<Exercise> exercises);

}
