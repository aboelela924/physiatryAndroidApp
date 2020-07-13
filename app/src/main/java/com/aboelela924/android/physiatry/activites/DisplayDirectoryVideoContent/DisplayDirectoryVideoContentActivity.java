package com.aboelela924.android.physiatry.activites.DisplayDirectoryVideoContent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.fragments.displayDirectoryContent.DisplayDirectoryVideoContentFragment;

public class DisplayDirectoryVideoContentActivity extends AppCompatActivity {

    private static final String PATH = "PATH";

    public static Intent getInstance(Context c, Uri path){
        Intent i = new Intent(c, DisplayDirectoryVideoContentActivity.class);
        i.putExtra(PATH, path);
        return  i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_directory_video_content);
        Intent i  = getIntent();
        Fragment f;
        if(i != null){
            Uri path = i.getParcelableExtra(PATH);
            f = DisplayDirectoryVideoContentFragment.newInstance(path);
        }else{
            f = new DisplayDirectoryVideoContentFragment();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.container, f).commit();
    }
}