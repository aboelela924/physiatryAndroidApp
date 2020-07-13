package com.aboelela924.android.physiatry.model.networking.downloadImageNetworking;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class DownloadImageNetworking {

    private final String TAG = "DownloadImageNetworking";

    private IDownloadImagePresenter mPresenter;

    public DownloadImageNetworking(IDownloadImagePresenter presenter){
        this.mPresenter = presenter;
    }

    public void getProfileImageURi(String imagePath){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://psychiatry-5d5a0.appspot.com");
        StorageReference ref = firebaseStorage.getReference(imagePath+"/"+user.getUid());

        ref.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mPresenter.onSuccess(uri);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e.getMessage());
                        if (e instanceof StorageException
                                && ((StorageException)e).getErrorCode() == StorageException.ERROR_OBJECT_NOT_FOUND){

                        }else{
                            mPresenter.onFailure("Error loading "+ imagePath +" image.");
                        }
                    }
                });

    }

    public void getImageURi(String imagePath){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://psychiatry-5d5a0.appspot.com");
        StorageReference ref = firebaseStorage.getReference(imagePath);

        ref.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mPresenter.onSuccess(uri);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e.getMessage());
                        if (e instanceof StorageException
                                && ((StorageException)e).getErrorCode() == StorageException.ERROR_OBJECT_NOT_FOUND){

                        }else{
                            mPresenter.onFailure("Error loading "+ imagePath +" image.");
                        }
                    }
                });

    }

}
