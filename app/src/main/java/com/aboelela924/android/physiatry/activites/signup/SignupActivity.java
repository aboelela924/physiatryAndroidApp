package com.aboelela924.android.physiatry.activites.signup;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.activites.BasicActivity;
import com.aboelela924.android.physiatry.presenters.signup.SignupPresenter;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SignupActivity extends BasicActivity implements ISignupView {

    private SignupPresenter mPresenter;

    @BindView(R.id.firstNameTextField) EditText mFirstNameEditText;
    @BindView(R.id.lastNameTextField) EditText mLastNameEditText;
    @BindView(R.id.emailTextField) EditText mEmailEditText;
    @BindView(R.id.passwordTextField) EditText mPasswordEditText;
    @BindView(R.id.emailTextInputLayout) TextInputLayout mEmailTextLayout;
    @BindView(R.id.passwordTextInputLayout) TextInputLayout mPasswordTextLayout;
    @BindView(R.id.confirmPasswordTextInputLayout) TextInputLayout mConfirmPasswordTextLayout;
    @BindView(R.id.confirmPasswordTextField) EditText mConfirmPasswordEditText;
    @BindView(R.id.loadingIndicator) AVLoadingIndicatorView mLoadingIndicatorView;
    @BindView(R.id.userTypeSpinner) Spinner mUserTypeSpinner;
    @BindView(R.id.registerButton) Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mPresenter = new SignupPresenter(this, this);
    }


    @OnClick(R.id.registerButton)
    public void register(){
        String firstName = mFirstNameEditText.getText().toString();
        String lastName = mLastNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        int userType = mUserTypeSpinner.getSelectedItemPosition()+1;
        Log.d("Tag", String.valueOf(userType));
        mPresenter.signUpUser(this,email, password, firstName, lastName, userType);
    }

    @OnClick(R.id.AlreadyHaveAccountTextView)
    public void goToLogin(){

    }

    @OnTextChanged(value = R.id.emailTextField, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void  emailChanged(){
        mPresenter.checkEmail(this.mEmailEditText.getText().toString());
    }

    @OnTextChanged(value = R.id.passwordTextField, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void  passwordChanged(){
        mPresenter.isPasswordLongEnough(this.mPasswordEditText.getText().toString());
    }

    @OnTextChanged(value = R.id.confirmPasswordTextField, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void  confirmPasswordChanged(){
        mPresenter.isPasswordMatch(this.mPasswordEditText.getText().toString(),
                this.mConfirmPasswordEditText.getText().toString());
    }

    @Override
    public void showProgressBar() {
        mLoadingIndicatorView.setVisibility(AVLoadingIndicatorView.VISIBLE);
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void hideProgressBar() {
        mLoadingIndicatorView.setVisibility(AVLoadingIndicatorView.INVISIBLE);
        mRegisterButton.setEnabled(true);
    }

    @Override
    public void signupClick() {


    }

    @Override
    public void notValidEmail() {
        mEmailTextLayout.setError("Not Valid Email");
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void notValidPassword() {
        mPasswordTextLayout.setError("Not Long Enough");
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void noPasswordMatch() {
        mConfirmPasswordTextLayout.setError("No Match");
        mConfirmPasswordTextLayout.setError("No Match");
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void removeNotValidEmailError() {
        mEmailTextLayout.setErrorEnabled(false);
        mRegisterButton.setEnabled(true);
    }

    @Override
    public void removeNotValidPasswordError() {
        mPasswordTextLayout.setErrorEnabled(false);
        mRegisterButton.setEnabled(true);
    }

    @Override
    public void removeNoPasswordMatchError() {
        mPasswordTextLayout.setErrorEnabled(false);
        mConfirmPasswordTextLayout.setErrorEnabled(false);
        mRegisterButton.setEnabled(true);
    }

    @Override
    public void moveToLogin() {

    }
}
