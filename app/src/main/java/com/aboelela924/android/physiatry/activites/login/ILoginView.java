package com.aboelela924.android.physiatry.activites.login;

import com.aboelela924.android.physiatry.activites.IBasicView;

public interface ILoginView extends IBasicView {
    void checkEmail(boolean isValid, String message);
    void showLoadingIndicator();
    void hideLoadingIndicator();
}
