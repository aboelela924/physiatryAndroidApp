package com.aboelela924.android.physiatry.model.networking.signUpNetworking;

import com.aboelela924.android.physiatry.model.IBasicPresenter;
import com.google.firebase.auth.FirebaseUser;

public interface ISignUpPresenter extends IBasicPresenter {
    void onSuccess(FirebaseUser user);
}
