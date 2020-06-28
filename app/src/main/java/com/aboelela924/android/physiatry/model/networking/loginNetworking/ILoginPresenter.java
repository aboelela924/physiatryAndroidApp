package com.aboelela924.android.physiatry.model.networking.loginNetworking;

import com.aboelela924.android.physiatry.model.IBasicPresenter;
import com.google.firebase.auth.FirebaseUser;

public interface ILoginPresenter extends IBasicPresenter {
    void onSuccess(FirebaseUser user);
}
