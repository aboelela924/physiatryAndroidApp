package com.aboelela924.android.physiatry.presenters.displayContent;

import android.content.Context;

import com.aboelela924.android.physiatry.fragments.displayDirectoryContent.IDisplayDirectoryVideoContentView;
import com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking.IReadDirectoryFilesPresenter;
import com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking.ReadDirectoryFilesNetworking;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class DisplayDirectoryVideoContentPresenter implements IReadDirectoryFilesPresenter {

    private ReadDirectoryFilesNetworking mReadDirectoryFilesNetworking;
    private IDisplayDirectoryVideoContentView mView;
    private Context mContext;

    public DisplayDirectoryVideoContentPresenter(IDisplayDirectoryVideoContentView view, Context context){
        this.mContext = context;
        this.mView = view;
        this.mReadDirectoryFilesNetworking = new ReadDirectoryFilesNetworking(this);
    }


    @Override
    public void onSuccess(List<StorageReference> list) {

    }

    @Override
    public void onFailure(String message) {

    }
}
