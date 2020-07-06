package com.aboelela924.android.physiatry.activites.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.activites.home.HomeActivity;
import com.aboelela924.android.physiatry.activites.signup.SignupActivity;
import com.aboelela924.android.physiatry.presenters.login.LoginPresenter;
import com.aboelela924.android.physiatry.utils.DialoguesUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView(R.id.emailTextInputLayout) TextInputLayout mEmailTextInputLayout;
    @BindView(R.id.passwordEditText) EditText mPasswordTextField;
    @BindView(R.id.passwordTextInputLayout) TextInputLayout mPasswordTextInputLayout;
    @BindView(R.id.loginButton) Button mLoginButton;
    @BindView(R.id.dontHaveAccountTextView) TextView mDontHaveAccountTextView;
    @BindView(R.id.loadingIndicator) AVLoadingIndicatorView mLoadingIndicator;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this, this);
        mPresenter.checkUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.loginButton)
    public void onLoginClick(){
        mPresenter.login(mEmailEditText.getText().toString(),
                mPasswordTextField.getText().toString(), false);
    }

    @OnClick(R.id.dontHaveAccountTextView)
    public void goToRegistration(){
        Intent i = new Intent(this, SignupActivity.class);
        finish();
        startActivity(i);
    }

    @OnTextChanged(value = R.id.emailEditText, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onEmailChange(){
        mPresenter.checkEmail(this.mEmailEditText.getText().toString());
    }

    @Override
    public void showErrorMessage(String title, String message) {
        DialoguesUtils.showErrorMessage(this, title, message);
    }

    @Override
    public void showSuccess(String title, String message) {
        //DialoguesUtils.showSuccessMessage(this, title, message);
        startActivity(HomeActivity.getIntent(this));
    }

    @Override
    public void checkEmail(boolean isValid, String message) {
        if(isValid){
            mEmailTextInputLayout.setErrorEnabled(false);
        }else{
            mEmailTextInputLayout.setError(message);
        }
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingIndicator.setVisibility(AVLoadingIndicatorView.VISIBLE);
        mLoginButton.setEnabled(false);
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(AVLoadingIndicatorView.INVISIBLE);
        mLoginButton.setEnabled(true);
    }
}
