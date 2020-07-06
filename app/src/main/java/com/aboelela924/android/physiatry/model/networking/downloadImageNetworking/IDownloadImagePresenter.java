package com.aboelela924.android.physiatry.model.networking.downloadImageNetworking;


import android.net.Uri;

import com.aboelela924.android.physiatry.model.IBasicPresenter;

public interface IDownloadImagePresenter extends IBasicPresenter {
    void onSuccess(Uri uri);
}
