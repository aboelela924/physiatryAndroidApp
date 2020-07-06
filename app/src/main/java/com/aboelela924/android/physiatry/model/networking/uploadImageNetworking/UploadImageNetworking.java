package com.aboelela924.android.physiatry.model.networking.uploadImageNetworking;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class UploadImageNetworking {


    private final String TAG = "UploadImageNetworking";

    private IUploadImagePresenter mPresenter;

    public UploadImageNetworking(IUploadImagePresenter presenter){
        this.mPresenter = presenter;
    }

    public void uploadImage(Bitmap bitmap, String uploadPath){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference reference = FirebaseStorage.getInstance("gs://psychiatry-5d5a0.appspot.com").getReference();
        StorageReference ref = reference.child(uploadPath+"/"+user.getUid());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70, stream);

        byte[] data = stream.toByteArray();

        UploadTask uploadTask = ref.putBytes(data);
        uploadTask
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mPresenter.onFailure("Failed to update " + uploadPath + " image.");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });
    }

}
