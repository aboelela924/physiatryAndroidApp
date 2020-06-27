package com.aboelela924.android.physiatry.presenters.signup;

import com.google.firebase.auth.FirebaseUser;

public interface ISignUpPresenter {
    void onSignUpSuccess(FirebaseUser user);
    void onSignUpFailure();
}
