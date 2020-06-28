package com.aboelela924.android.physiatry.model.networking.loginNetworking;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginNetworking {

    private ILoginPresenter mPresenter;
    private FirebaseAuth mFirebaseAuth;

    public LoginNetworking(ILoginPresenter presenter){
        this.mFirebaseAuth = FirebaseAuth.getInstance();
        this.mPresenter = presenter;
    }

    public void loginUser(String email, String password){
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            mPresenter.onSuccess(user);
                        }else{
                            mPresenter.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

}
