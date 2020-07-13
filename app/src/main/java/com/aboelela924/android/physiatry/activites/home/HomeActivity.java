package com.aboelela924.android.physiatry.activites.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.activites.BasicActivity;
import com.aboelela924.android.physiatry.fragments.exercises.ExercisesFragment;
import com.aboelela924.android.physiatry.fragments.profile.ProfileFragment;
import com.aboelela924.android.physiatry.fragments.quiz.QuizFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BasicActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;

    private FragmentManager mFragmentManager;

    private Fragment mArticlesFragment;
    private Fragment mQuizFragment;
    private Fragment mProfileFragment;

    @BindView(R.id.home_bottom_navigation) BottomNavigationView mBottomNavigation;
    private Toolbar mToolbar;

    public static Intent getIntent(Context c){
        Intent i = new Intent(c, HomeActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mArticlesFragment = new ExercisesFragment();
        mQuizFragment = new QuizFragment();
        mProfileFragment = new ProfileFragment();

        mFragmentManager = getSupportFragmentManager();

        mToolbar  = (Toolbar) findViewById(R.id.include);
        setSupportActionBar(mToolbar);

        mBottomNavigation.setOnNavigationItemSelectedListener(this);
        mBottomNavigation.setSelectedItemId(R.id.articles_item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment f;
        getSupportActionBar().show();
        switch (item.getItemId()){
            case R.id.articles_item:
                f = mArticlesFragment;
                break;
            case R.id.quizes_item:
                f = mQuizFragment;
                break;
            case R.id.user_profile_item:
//                getSupportActionBar().hide();
                f = mProfileFragment;
                break;
            default:
                f =mArticlesFragment;
                break;
        }
        mFragmentManager.beginTransaction().replace(R.id.home_content_frame_layout, f).commit();
        return true;
    }
}
