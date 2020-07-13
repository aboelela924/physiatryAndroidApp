package com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class ReadDirectoryFilesNetworking {

    private IReadDirectoryFilesPresenter mPresenter;

    public ReadDirectoryFilesNetworking(IReadDirectoryFilesPresenter presenter){
        this.mPresenter = presenter;
    }

    public void getDirectoriesInDirectory(String dir){

        StorageReference ref = FirebaseStorage
                .getInstance("gs://psychiatry-5d5a0.appspot.com")
                .getReference()
                .child(dir);

        ref.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        mPresenter.onSuccess(listResult.getPrefixes());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mPresenter.onFailure(e.getMessage());
                    }
                });
    }

    public void getFilesInDirectory(String dir){
        StorageReference ref = FirebaseStorage
                .getInstance("gs://psychiatry-5d5a0.appspot.com")
                .getReference()
                .child(dir);

        ref.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        mPresenter.onSuccess(listResult.getItems());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mPresenter.onFailure(e.getMessage());
                    }
                });
    }

}
