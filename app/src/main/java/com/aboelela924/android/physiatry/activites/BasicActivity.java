package com.aboelela924.android.physiatry.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aboelela924.android.physiatry.utils.DialoguesUtils;

public abstract class BasicActivity extends AppCompatActivity implements IBasicView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showErrorMessage(String title, String message) {
        DialoguesUtils.showErrorMessage(this, title, message);
    }

    @Override
    public void showSuccess(String title, String message) {
        DialoguesUtils.showErrorMessage(this, title, message);
    }
}
