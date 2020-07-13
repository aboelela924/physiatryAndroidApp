package com.aboelela924.android.physiatry.fragments.exercises;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.activites.DisplayDirectoryVideoContent.DisplayDirectoryVideoContentActivity;
import com.aboelela924.android.physiatry.model.dataModeling.Exercise;
import com.aboelela924.android.physiatry.presenters.exercise.ExercisePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExercisesFragment extends Fragment implements IExerciseView{

    private List<Exercise> mExercises;
    private ExerciseAdapter mAdapter;
    private ExercisePresenter mPresenter;

    public ExercisesFragment() {
        // Required empty public constructor
        mExercises = new ArrayList<>();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getActivity().setTitle(getResources().getString(R.string.articles));
        mPresenter = new ExercisePresenter(this, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exercises, container, false);
        ButterKnife.bind(this, v);

        mAdapter = new ExerciseAdapter(mExercises);

        RecyclerView exercisesRecyclerView = v.findViewById(R.id.exercises_recycler_view);
        exercisesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        exercisesRecyclerView.setAdapter(mAdapter);


        mPresenter.getExercises();

        return v;
    }

    @Override
    public void displayExercises(List<Exercise> exercises) {
        mExercises.clear();
        mExercises.addAll(exercises);
        Collections.sort(mExercises, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise exercise, Exercise t1) {
                return exercise.getName().compareTo(t1.getName());
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String title, String message) {

    }

    @Override
    public void showSuccess(String title, String message) {

    }


    private class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mExerciseCoverImageView;
        private TextView mExerciseNameTextView;
        private  Uri mPath;
        public ExerciseViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_exercises_recycler_view_item,
                    parent,
                    false));
            View v  = itemView;
            v.setOnClickListener(this);
            mExerciseNameTextView = v.findViewById(R.id.exercise_name_text_view);
            mExerciseCoverImageView = v.findViewById(R.id.exercise_cover_image_view);

        }

        public void bind(Uri cover, String name, Uri path){
            this.mPath = path;
            mExerciseNameTextView.setText(name);

            DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
            float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
            int dpWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);
            int imageHeight = (int) dpHeight/2;
            Picasso.get().load(cover).placeholder(R.drawable.placeholder).resize(dpWidth, imageHeight).centerInside().into(mExerciseCoverImageView);
        }

        @Override
        public void onClick(View view) {
            Intent i = DisplayDirectoryVideoContentActivity.getInstance(getActivity(), mPath);
            getActivity().startActivity(i);
        }
    }

    private class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder>{

        private List<Exercise> mExercises;

        public ExerciseAdapter(List<Exercise> exercises){
            this.mExercises = exercises;
        }

        @NonNull
        @Override
        public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ExerciseViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
            Exercise e = mExercises.get(position);
            holder.bind(e.getCover(), e.getName(), e.getExerciseRef());

        }

        @Override
        public int getItemCount() {
            return mExercises.size();
        }
    }

}
