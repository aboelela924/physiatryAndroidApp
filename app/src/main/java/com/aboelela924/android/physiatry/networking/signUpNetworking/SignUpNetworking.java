package com.aboelela924.android.physiatry.networking.signUpNetworking;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.aboelela924.android.physiatry.presenters.signup.ISignUpPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpNetworking {

    private FirebaseAuth mAuth;
    private ISignUpPresenter mPresenter;

    public SignUpNetworking(ISignUpPresenter presenter){
        this.mAuth = FirebaseAuth.getInstance();

        this.mPresenter = presenter;
    }

    public void signUp(Activity ctx, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            SignUpNetworking.this.mPresenter.onSignUpSuccess(user);
                        }else{
                            SignUpNetworking.this.mPresenter.onSignUpFailure();
                        }
                    }
                });
    }

}
