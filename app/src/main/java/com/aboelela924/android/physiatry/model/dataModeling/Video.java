package com.aboelela924.android.physiatry.model.dataModeling;

import android.net.Uri;

public class Video {

    private String mName;
    private String mPath;

    public Video(String name, String path) {
        mName = name;
        mPath = path;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }
}
