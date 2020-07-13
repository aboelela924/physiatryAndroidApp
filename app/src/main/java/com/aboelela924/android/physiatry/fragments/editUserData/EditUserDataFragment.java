package com.aboelela924.android.physiatry.fragments.editUserData;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboelela924.android.physiatry.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditUserDataFragment extends Fragment {

    public EditUserDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_user_data, container, false);
    }
}
