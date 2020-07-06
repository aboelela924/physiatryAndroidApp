package com.aboelela924.android.physiatry.model.networking.readUserFromDB;

import androidx.annotation.NonNull;

import com.aboelela924.android.physiatry.model.dataModeling.User;
import com.aboelela924.android.physiatry.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReadFromDBNetworking {

    private IReadFromDBPresenter mPresenter;

    public ReadFromDBNetworking(IReadFromDBPresenter presenter){
        this.mPresenter = presenter;
    }

    public void readUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.USER)
                .child(user.getUid());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userModel = null;
                if(snapshot.getValue() instanceof HashMap){
                    HashMap data = (HashMap) snapshot.getValue();
                    userModel = User.fromMap(data);
                }
                if(userModel != null){
                    mPresenter.onSuccess(userModel);
                }else{
                    mPresenter.onFailure("Error loading User Data.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
