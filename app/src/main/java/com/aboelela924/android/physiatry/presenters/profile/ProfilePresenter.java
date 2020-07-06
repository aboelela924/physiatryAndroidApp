package com.aboelela924.android.physiatry.presenters.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.fragments.profile.IProfileView;
import com.aboelela924.android.physiatry.model.dataModeling.User;
import com.aboelela924.android.physiatry.model.networking.downloadImageNetworking.DownloadImageNetworking;
import com.aboelela924.android.physiatry.model.networking.downloadImageNetworking.IDownloadImagePresenter;
import com.aboelela924.android.physiatry.model.networking.readUserFromDB.IReadFromDBPresenter;
import com.aboelela924.android.physiatry.model.networking.readUserFromDB.ReadFromDBNetworking;
import com.aboelela924.android.physiatry.model.networking.uploadImageNetworking.IUploadImagePresenter;
import com.aboelela924.android.physiatry.model.networking.uploadImageNetworking.UploadImageNetworking;
import com.aboelela924.android.physiatry.utils.Constants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;

public class ProfilePresenter implements IUploadImagePresenter, IDownloadImagePresenter, IReadFromDBPresenter {

    private final String TAG = "ProfilePresenter";

    private IProfileView mView;
    private UploadImageNetworking mUploadImageNetworking;
    private DownloadImageNetworking mDownloadImageNetworking;
    private ReadFromDBNetworking mReadFromDBNetworking;
    private Context mContext;

    public ProfilePresenter(IProfileView view, Context ctx){
        this.mView = view;
        this.mContext = ctx;
        this.mDownloadImageNetworking = new DownloadImageNetworking(this);
        this.mUploadImageNetworking = new UploadImageNetworking(this);
        this.mReadFromDBNetworking = new ReadFromDBNetworking(this);
    }

    public void getUserData(){
        mReadFromDBNetworking.readUser();
    }

    public void getPermissions(){
        PermissionListener dialogPermissionListener =
                DialogOnDeniedPermissionListener.Builder
                        .withContext(mContext)
                        .withTitle("Storage permission")
                        .withMessage("Storage permission is needed to be able to get your images")
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.drawable.camera)
                        .build();
        Dexter.withContext(mContext)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();
    }

    public boolean isPermissionGranted(){
        int checkVal = mContext.checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        return  checkVal == PackageManager.PERMISSION_GRANTED;
    }

    private Bitmap getBitMapFromUri(Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void getProfileImageBitmap(Uri uri){
        Bitmap bitmap = getBitMapFromUri(uri);
        if(bitmap == null){
            mView.showErrorMessage("Invalid", "Please choose a valid image.");
        }else{
            mView.displayProfileImage(bitmap);
            mUploadImageNetworking.uploadImage(bitmap, Constants.PROFILE_PATH);
        }
    }

    public void loadProfileImage(){
        mDownloadImageNetworking.getImageURi(Constants.PROFILE_PATH);
    }

    public void loadCoverImage(){
        mDownloadImageNetworking.getImageURi(Constants.COVER_PATH);
    }

    public void getCoverImageBitmap(Uri uri){
        Bitmap bitmap = getBitMapFromUri(uri);
        if(bitmap == null){
            mView.showErrorMessage("Invalid", "Please choose a valid image.");
        }else{
            mView.displayCoverImage(bitmap);
            mUploadImageNetworking.uploadImage(bitmap, Constants.COVER_PATH);
        }
    }

    public void getImage(Fragment f, int constant){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        f.startActivityForResult(Intent.createChooser(intent, "Select Picture"), constant);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String message) {
        mView.showErrorMessage("Error", message);
    }

    @Override
    public void onSuccess(Uri uri) {
        String lastSegment = uri.getLastPathSegment();
        String[] lastSegments = lastSegment.split("/");
        if (lastSegments[0].equals(Constants.PROFILE_PATH)){
            mView.displayProfileImage(uri);
        }else if (lastSegments[0].equals(Constants.COVER_PATH)){
            mView.displayCoverImage(uri);
        }
    }

    @Override
    public void onSuccess(User user) {
        mView.displayUserData(user);
    }
}
