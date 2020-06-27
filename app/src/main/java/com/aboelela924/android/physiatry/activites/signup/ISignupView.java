package com.aboelela924.android.physiatry.activites.signup;

import com.aboelela924.android.physiatry.activites.IBasicView;

public interface ISignupView extends IBasicView {
    void showProgressBar();
    void hideProgressBar();
    void signupClick();
    void notValidEmail();
    void notValidPassword();
    void noPasswordMatch();
    void removeNotValidEmailError();
    void removeNotValidPasswordError();
    void removeNoPasswordMatchError();
    void moveToLogin();
}
