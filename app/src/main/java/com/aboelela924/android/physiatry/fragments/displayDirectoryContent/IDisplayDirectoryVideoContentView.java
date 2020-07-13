package com.aboelela924.android.physiatry.fragments.displayDirectoryContent;

import android.net.Uri;

import com.aboelela924.android.physiatry.activites.IBasicView;
import com.aboelela924.android.physiatry.model.dataModeling.Video;

import java.util.List;

public interface IDisplayDirectoryVideoContentView extends IBasicView {

    void onSuccess(List<Video> videos);
    void FolderName(String name);
    void displayCover(Uri imageUrl);

}
