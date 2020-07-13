package com.aboelela924.android.physiatry.presenters.displayContent;

import android.content.Context;
import android.net.Uri;

import com.aboelela924.android.physiatry.fragments.displayDirectoryContent.IDisplayDirectoryVideoContentView;
import com.aboelela924.android.physiatry.model.dataModeling.Video;
import com.aboelela924.android.physiatry.model.networking.downloadImageNetworking.DownloadImageNetworking;
import com.aboelela924.android.physiatry.model.networking.downloadImageNetworking.IDownloadImagePresenter;
import com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking.IReadDirectoryFilesPresenter;
import com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking.ReadDirectoryFilesNetworking;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class DisplayDirectoryVideoContentPresenter implements IReadDirectoryFilesPresenter, IDownloadImagePresenter {

    private final String[] VIDEO_EXTENSIONS = {"mp4"};
    private final String[] IMAGE_EXTENSIONS = {"jpg"};

    private List<Video> mVideos;

    private ReadDirectoryFilesNetworking mReadDirectoryFilesNetworking;
    private DownloadImageNetworking mImageNetworking;
    private IDisplayDirectoryVideoContentView mView;
    private Context mContext;

    public DisplayDirectoryVideoContentPresenter(IDisplayDirectoryVideoContentView view, Context context){
        this.mContext = context;
        this.mView = view;
        this.mReadDirectoryFilesNetworking = new ReadDirectoryFilesNetworking(this);
        mImageNetworking = new DownloadImageNetworking(this);
        mVideos = new ArrayList<>();
    }

    public void getPathContentFiles(Uri path){
        mReadDirectoryFilesNetworking.getFilesInDirectory(path.toString());
    }

    @Override
    public void onSuccess(List<StorageReference> list) {
        String folderName="";
        for(StorageReference ref : list){
            String path = ref.getPath();
            int size = path.split("/").length - 1;
            folderName = path.split("/")[size-1];
            String fileName = path.split("/")[size].split("\\.")[0];
            String fileExtension = path.split("/")[size].split("\\.")[1];
            if(check(VIDEO_EXTENSIONS, fileExtension)){

                Video v = new Video(fileName, ref.getPath());
                mVideos.add(v);

            }else if (check(IMAGE_EXTENSIONS, fileExtension)){

                mImageNetworking.getImageURi(path);

            }
        }
        mView.onSuccess(mVideos);
        mView.FolderName(folderName);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onSuccess(Uri uri) {
        mView.displayCover(uri);
    }

    private boolean check(String[] arr, String toCheckValue)
    {
        for (String element : arr) {
            if (element.equalsIgnoreCase(toCheckValue) ) {
                return true;
            }
        }
        return false;
    }
}
