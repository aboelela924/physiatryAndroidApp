package com.aboelela924.android.physiatry.fragments.displayDirectoryContent;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboelela924.android.physiatry.R;
import com.aboelela924.android.physiatry.model.dataModeling.Video;
import com.aboelela924.android.physiatry.presenters.displayContent.DisplayDirectoryVideoContentPresenter;
import com.squareup.picasso.Picasso;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayDirectoryVideoContentFragment extends Fragment implements IDisplayDirectoryVideoContentView{


    private static final String DIRECTORY_PATH = "DIRECTORY_PATH";

    @BindView(R.id.cover_image_view) ImageView mCoverImageView;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.video_content_recycler_view) RecyclerView mRecyclerView;

    private List<Video> mVideos;

    private Uri mDirectoryPath;
    private DisplayDirectoryVideoContentPresenter mPresenter;
    private VideoNameAdapter mAdapter;


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
        mPresenter = new DisplayDirectoryVideoContentPresenter(this, getActivity());
        if (getArguments() != null) {
            mDirectoryPath = getArguments().getParcelable(DIRECTORY_PATH);
        }
        mPresenter.getPathContentFiles(mDirectoryPath);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display_directory_video_content,
                container,
                false);
        ButterKnife.bind(this, v);

        mVideos = new ArrayList<>();

        mAdapter = new VideoNameAdapter(mVideos);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onSuccess(List<Video> videos) {
        mVideos.clear();
        mVideos.addAll(videos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void FolderName(String name) {
        mToolbar.setTitle(name);
    }

    @Override
    public void displayCover(Uri imageUrl) {
        Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(mCoverImageView);
    }

    @Override
    public void showErrorMessage(String title, String message) {

    }

    @Override
    public void showSuccess(String title, String message) {

    }


    private class VideoTitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private Video mVideo;

        public VideoTitleViewHolder(View v) {
            super(v);

            mNameTextView = v.findViewById(R.id.video_name_text_view);
            v.findViewById(R.id.video_name_linear_layout).setOnClickListener(this);
        }

        public void bind(Video v){
            mVideo = v;
            mNameTextView.setText(v.getName());
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class VideoNameAdapter extends RecyclerView.Adapter<VideoTitleViewHolder>{

        private List<Video> mVideos;

        public VideoNameAdapter(List<Video> videos) {
            mVideos = videos;
        }

        @NonNull
        @Override
        public VideoTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.fragment_display_directory_video_content_recycler_view_item, parent, false);
            return new VideoTitleViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoTitleViewHolder holder, int position) {
            holder.bind(mVideos.get(position));
        }

        @Override
        public int getItemCount() {
            return mVideos.size();
        }
    }
}