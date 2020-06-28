package com.aboelela924.android.physiatry.model.networking.writeToDB;

import androidx.annotation.NonNull;

import com.aboelela924.android.physiatry.model.dataModeling.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteToDBNetworking {

    private FirebaseDatabase mDatabase;
    private IWriteToDBPresenter mPresenter;

    public WriteToDBNetworking(IWriteToDBPresenter presenter){
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mPresenter = presenter;
    }

    public void writeUserData(String userId, User user){
        DatabaseReference ref = mDatabase.getReference("user");
        ref.child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mPresenter.onSuccess("Data saved Successfully.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mPresenter.onSuccess("An error occured please try again.");
                    }
                });
    }

}
