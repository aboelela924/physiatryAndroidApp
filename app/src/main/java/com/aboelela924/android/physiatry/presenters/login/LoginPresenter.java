package com.aboelela924.android.physiatry.presenters.login;

import com.aboelela924.android.physiatry.activites.login.ILoginView;
import com.aboelela924.android.physiatry.model.networking.loginNetworking.ILoginPresenter;
import com.aboelela924.android.physiatry.model.networking.loginNetworking.LoginNetworking;
import com.aboelela924.android.physiatry.utils.DataChecking;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView mView;
    private LoginNetworking mLoginNetworking;

    public LoginPresenter(ILoginView view){
        this.mView = view;
        mLoginNetworking = new LoginNetworking(this);
    }

    public void login(String email, String password){
        if(DataChecking.isEmpty(email)
        || DataChecking.isEmpty(password)){
            mView.showErrorMessage("Login", "Please, Fill all the fields first.");
        }else{
            mView.showLoadingIndicator();
            mLoginNetworking.loginUser(email,password);

        }
    }


    public void checkEmail(String email){
        if(DataChecking.isValidEmail(email)){
            mView.checkEmail(true, "");
        }else{
            mView.checkEmail(false,"Not valid Email");
        }
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        mView.hideLoadingIndicator();
        mView.showSuccess("Login", "Login successful");
    }

    @Override
    public void onFailure(String message) {
        mView.hideLoadingIndicator();
        mView.showErrorMessage("Login", message);
    }
}
