package com.aboelela924.android.physiatry.presenters.login;

import android.app.Activity;
import android.content.SharedPreferences;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.activites.login.ILoginView;
import com.aboelela924.android.physiatry.model.networking.loginNetworking.ILoginPresenter;
import com.aboelela924.android.physiatry.model.networking.loginNetworking.LoginNetworking;
import com.aboelela924.android.physiatry.utils.Constants;
import com.aboelela924.android.physiatry.utils.DataChecking;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView mView;
    private LoginNetworking mLoginNetworking;
    private Activity mActivity;

    public LoginPresenter(ILoginView view, Activity a){
        this.mView = view;
        this.mActivity = a;
        mLoginNetworking = new LoginNetworking(this);
    }

    public void login(String email, String password, boolean isFromSP){
        if(DataChecking.isEmpty(email)
        || DataChecking.isEmpty(password)){
            mView.showErrorMessage("Login", "Please, Fill all the fields first.");
        }else{
            mView.showLoadingIndicator();
            mLoginNetworking.loginUser(email,password);

            if(!isFromSP){
                SharedPreferences sp = mActivity.getSharedPreferences(Constants.SP_NAME, mActivity.MODE_PRIVATE);
                sp.edit()
                        .putString(Constants.sp_email_key, email)
                        .putString(Constants.sp_password_key, password)
                        .commit();
            }

        }
    }


    public void checkUser(){
        SharedPreferences sp = mActivity.getSharedPreferences(Constants.SP_NAME, mActivity.MODE_PRIVATE);
        String email = sp.getString(Constants.sp_email_key,"");
        String password = sp.getString(Constants.sp_password_key, "");
        if(!email.isEmpty() && !password.isEmpty()){
            login(email, password, true);
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
        mActivity.finish();
        mView.showSuccess("Login", "Login successful");
    }

    @Override
    public void onFailure(String message) {
        mView.hideLoadingIndicator();
        mView.showErrorMessage("Login", message);
    }
}
