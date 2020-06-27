package com.aboelela924.android.physiatry.presenters.signup;

import android.app.Activity;
import android.util.Log;

import com.aboelela924.android.physiatry.activites.signup.ISignupView;
import com.aboelela924.android.physiatry.networking.signUpNetworking.SignUpNetworking;
import com.aboelela924.android.physiatry.utils.DataChecking;
import com.aboelela924.android.physiatry.utils.DialoguesUtils;
import com.google.android.gms.common.util.DataUtils;
import com.google.firebase.auth.FirebaseUser;

public class SignupPresenter implements ISignUpPresenter {
    private ISignupView mView;
    private SignUpNetworking mSignUpNetworking;
    private Activity mActivity;

    public SignupPresenter(ISignupView view, Activity activity){
        this.mView = view;
        this.mActivity = activity;
        mSignUpNetworking = new SignUpNetworking(this);
    }

    public void signUpUser(Activity a, String email, String password, String firstName, String lastName, int type){
        mView.showProgressBar();
        if(DataChecking.isEmpty(email)
        && DataChecking.isEmpty(password)
        && DataChecking.isEmpty(firstName)
        && DataChecking.isEmpty(lastName)){
            DialoguesUtils.showErrorMessage(a,"Empty", "Fill all the Fields, Please.");
        }else{
            mSignUpNetworking.signUp(a, email, password);
        }
    }

    @Override
    public void onSignUpSuccess(FirebaseUser user) {
        mView.hideProgressBar();
        DialoguesUtils.showSuccessMessage(mActivity,"Login", "Login Successfully");
    }

    @Override
    public void onSignUpFailure() {
        mView.hideProgressBar();
        DialoguesUtils.showErrorMessage(mActivity, "Login", "Unable to login, please try agagin.");
    }

    public void checkEmail(String email){
        if(!DataChecking.isValidEmail(email)){
            mView.notValidEmail();
        }else{
            mView.removeNotValidEmailError();
        }
    }

    public void isPasswordLongEnough(String password){
        if(!DataChecking.isLongEnough(password, 6)) {
            mView.notValidPassword();
        }else{
            mView.removeNotValidPasswordError();
        }
    }

    public void isPasswordMatch(String password, String confirmPassword){
        if(!DataChecking.isMatch(password, confirmPassword)){
            mView.noPasswordMatch();
        }else{
            mView.removeNoPasswordMatchError();
        }
    }

}
