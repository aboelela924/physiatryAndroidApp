package com.aboelela924.android.physiatry.model.dataModeling;

import android.graphics.Bitmap;
import android.net.Uri;

public class Exercise {
    private String name;
    private Uri cover;
    private Uri exerciseRef;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getCover() {
        return cover;
    }

    public void setCover(Uri cover) {
        this.cover = cover;
    }

    public Uri getExerciseRef() {
        return exerciseRef;
    }

    public void setExerciseRef(Uri exerciseRef) {
        this.exerciseRef = exerciseRef;
    }

    public Exercise(String name, Uri cover, Uri exerciseRef) {
        this.name = name;
        this.cover = cover;
        this.exerciseRef = exerciseRef;
    }


}
