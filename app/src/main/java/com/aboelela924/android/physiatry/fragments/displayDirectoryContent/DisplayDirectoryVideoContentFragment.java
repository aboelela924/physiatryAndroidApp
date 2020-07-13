package com.aboelela924.android.physiatry.fragments.displayDirectoryContent;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboelela924.android.physiatry.R;

public class DisplayDirectoryVideoContentFragment extends Fragment {


    private static final String DIRECTORY_PATH = "DIRECTORY_PATH";


    private Uri mDirectoryPath;

    public DisplayDirectoryVideoContentFragment() {

    }


    public static DisplayDirectoryVideoContentFragment newInstance(Uri directoryPath) {
        DisplayDirectoryVideoContentFragment fragment = new DisplayDirectoryVideoContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(DIRECTORY_PATH, directoryPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDirectoryPath = getArguments().getParcelable(DIRECTORY_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display_directory_video_content,
                container,
                false);
        return v;
    }
}