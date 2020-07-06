package com.aboelela924.android.physiatry.fragments.profile;

import android.graphics.Bitmap;
import android.net.Uri;

import com.aboelela924.android.physiatry.activites.IBasicView;
import com.aboelela924.android.physiatry.model.dataModeling.User;

import java.net.URI;

public interface IProfileView extends IBasicView {
    void displayProfileImage(Bitmap bitmap);
    void displayCoverImage(Bitmap bitmap);
    void displayProfileImage(Uri uri);
    void displayCoverImage(Uri uri);
    void displayUserData(User user);
}
