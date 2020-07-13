package com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking;

import com.aboelela924.android.physiatry.model.IBasicPresenter;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public interface IReadDirectoryFilesPresenter extends IBasicPresenter {
    void onSuccess(List<StorageReference> list);
}
