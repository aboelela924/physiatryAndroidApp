package com.aboelela924.android.physiatry.fragments.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.model.dataModeling.User;
import com.aboelela924.android.physiatry.presenters.profile.ProfilePresenter;
import com.aboelela924.android.physiatry.utils.DialoguesUtils;
import com.squareup.picasso.Picasso;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements IProfileView{
    private static final String TAG = "ProfileFragment";
    private static final int PICK_PROFILE_IMAGE = 0;
    private static final int PICK_COVER_IMAGE = 1;

    @BindView(R.id.cover_image_image_view) ImageView mCoverImage;
    @BindView(R.id.profile_image_cirlce_image_view) CircleImageView mProfileImage;
    @BindView(R.id.user_name_text_view) TextView mUsernameTextView;
    @BindView(R.id.email_text_view) TextView mEmailTextView;

    private ProfilePresenter mPresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getActivity().setTitle(getResources().getString(R.string.profile));

        mPresenter = new ProfilePresenter(this, getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, v);
        mPresenter.loadProfileImage();
        mPresenter.loadCoverImage();
        mPresenter.getUserData();
        return v;
    }

    @OnClick(R.id.change_profile_image_image_view)
    public void changeProfileImage(){
        mPresenter.getPermissions();
        if(mPresenter.isPermissionGranted()){
            mPresenter.getImage(this, PICK_PROFILE_IMAGE);
        }
    }

    @OnClick(R.id.change_cover_image_view)
    public void changeCoverImage(){
        mPresenter.getPermissions();
        if(mPresenter.isPermissionGranted()){
            mPresenter.getImage(this, PICK_COVER_IMAGE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_PROFILE_IMAGE && resultCode == Activity.RESULT_OK) {
            mPresenter.getProfileImageBitmap(data.getData());
        }else if(requestCode == PICK_COVER_IMAGE && resultCode == Activity.RESULT_OK){
            mPresenter.getCoverImageBitmap(data.getData());
        }
    }

    @Override
    public void displayProfileImage(Bitmap bitmap) {
        mProfileImage.setImageBitmap(bitmap);
    }

    @Override
    public void displayCoverImage(Bitmap bitmap) {
        mCoverImage.setImageBitmap(bitmap);
    }

    @Override
    public void displayProfileImage(Uri uri) {
        Picasso.get()
                .load(uri)
                .into(mProfileImage);
    }

    @Override
    public void displayCoverImage(Uri uri) {
        Picasso.get()
                .load(uri)
                .into(mCoverImage);
    }

    @Override
    public void displayUserData(User user) {
        mEmailTextView.setText(user.getEmail());
        mUsernameTextView.setText(user.getFirstName()+" "+user.getLastName());
    }

    @Override
    public void showErrorMessage(String title, String message) {
        DialoguesUtils.showErrorMessage(getActivity(), title, message);
    }

    @Override
    public void showSuccess(String title, String message) {
        DialoguesUtils.showSuccessMessage(getActivity(), title, message);
    }
}
