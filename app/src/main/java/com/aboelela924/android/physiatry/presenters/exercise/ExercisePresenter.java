package com.aboelela924.android.physiatry.presenters.exercise;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.aboelela924.android.physiatry.fragments.exercises.IExerciseView;
import com.aboelela924.android.physiatry.model.dataModeling.Exercise;
import com.aboelela924.android.physiatry.model.networking.downloadImageNetworking.DownloadImageNetworking;
import com.aboelela924.android.physiatry.model.networking.downloadImageNetworking.IDownloadImagePresenter;
import com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking.IReadDirectoryFilesPresenter;
import com.aboelela924.android.physiatry.model.networking.getDirectoriesNetworking.ReadDirectoryFilesNetworking;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ExercisePresenter implements IReadDirectoryFilesPresenter, IDownloadImagePresenter {

    private static final String TAG = "ExercisePresenter" ;
    private IExerciseView mView;
    private ReadDirectoryFilesNetworking mDirectoryNetworking;
    private DownloadImageNetworking mImageNetworking;
    private Context mContext;

    private int counter = -1;

    private List<Exercise> mExercises;

    private final String DIR = "/exercises";

    public ExercisePresenter(IExerciseView view, Context context) {
        mView = view;
        mContext = context;
        mDirectoryNetworking = new ReadDirectoryFilesNetworking(this);
        mImageNetworking = new DownloadImageNetworking(this);

        mExercises = new ArrayList<>();
    }

    public void getExercises(){
        mDirectoryNetworking.getDirectoriesInDirectory(DIR);
    }

    @Override
    public void onSuccess(Uri uri) {
        Log.d(TAG, "onSuccess: "+ uri);
        //uri.getLastPathSegment() index => 1
        String name = uri.getLastPathSegment().split("/")[1];
        String uriString = uri.getLastPathSegment();
        String toBeRemoved = uri.getLastPathSegment().split("/")[2];
        uriString = uriString.replace(toBeRemoved, "");
        Uri path = Uri.parse(uriString);
        Exercise e = new Exercise(name,uri, path);
        mExercises.add(e);
        if(mExercises.size() == this.counter){
            mView.displayExercises(mExercises);
        }

    }

    @Override
    public void onSuccess(List<StorageReference> list) {
        this.counter = list.size();
        for(StorageReference ref : list){
            mImageNetworking.getImageURi(ref.getPath()+"/cover.jpg");
        }
    }

    @Override
    public void onFailure(String message) {

    }
}
