package com.aboelela924.android.physiatry.presenters.signup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import com.aboelela924.android.physiatry.activites.home.HomeActivity;
import com.aboelela924.android.physiatry.activites.signup.ISignupView;
import com.aboelela924.android.physiatry.model.dataModeling.User;
import com.aboelela924.android.physiatry.model.networking.signUpNetworking.ISignUpPresenter;
import com.aboelela924.android.physiatry.model.networking.signUpNetworking.SignUpNetworking;
import com.aboelela924.android.physiatry.model.networking.writeToDB.IWriteToDBPresenter;
import com.aboelela924.android.physiatry.model.networking.writeToDB.WriteToDBNetworking;
import com.aboelela924.android.physiatry.utils.Constants;
import com.aboelela924.android.physiatry.utils.DataChecking;
import com.aboelela924.android.physiatry.utils.DialoguesUtils;
import com.google.firebase.auth.FirebaseUser;

public class SignupPresenter implements ISignUpPresenter, IWriteToDBPresenter {
    private ISignupView mView;
    private SignUpNetworking mSignUpNetworking;
    private WriteToDBNetworking mWriteToDBNetworking;
    private Activity mActivity;
    private FirebaseUser mFirebaseUser;
    private User mUser;

    public SignupPresenter(ISignupView view, Activity activity){
        this.mView = view;
        this.mActivity = activity;
        mSignUpNetworking = new SignUpNetworking(this);
        mWriteToDBNetworking = new WriteToDBNetworking(this);
    }

    public void signUpUser(Activity a, String email, String password, String firstName, String lastName, int type){

        mUser = new User(email);
        mUser.setFirstName(firstName);
        mUser.setLastName(lastName);
        mUser.setType(type);

        mView.showProgressBar();
        if(DataChecking.isEmpty(email)
        && DataChecking.isEmpty(password)
        && DataChecking.isEmpty(firstName)
        && DataChecking.isEmpty(lastName)){
            DialoguesUtils.showErrorMessage(a,"Empty", "Fill all the Fields, Please.");
        }else{
            mSignUpNetworking.signUp(a, email, password);
            SharedPreferences sp = mActivity.getSharedPreferences(Constants.SP_NAME, mActivity.MODE_PRIVATE);
            sp.edit()
                    .putString(Constants.sp_email_key, email)
                    .putString(Constants.sp_password_key, password)
                    .apply();

        }
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

    @Override
    public void onSuccess(FirebaseUser user) {
        mFirebaseUser = user;
        mUser.setId(user.getUid());
        mWriteToDBNetworking.writeUserData(mFirebaseUser.getUid(), mUser);
    }

    @Override
    public void onSuccess(String message) {
        mView.hideProgressBar();
        Intent i = new Intent(mActivity, HomeActivity.class);
        mActivity.startActivity(i);
        mActivity.finish();
//        DialoguesUtils.showSuccessMessage(mActivity,"Login", "Login Successfully");
    }

    @Override
    public void onFailure(String message) {
        mView.hideProgressBar();
        if(message.isEmpty()){
            DialoguesUtils.showErrorMessage(mActivity, "Login", "Unable to login, please try agagin.");
        }else{
            DialoguesUtils.showErrorMessage(mActivity, "Login", message);
        }
    }
}
