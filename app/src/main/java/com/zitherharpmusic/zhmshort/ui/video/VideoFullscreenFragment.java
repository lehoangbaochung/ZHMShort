package com.zitherharpmusic.zhmshort.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.data.DataUtils;

import java.io.Serializable;
import java.util.List;

public class VideoFullscreenFragment extends Fragment {

    public static VideoFullscreenFragment newInstance(List<Video> videos) {
        Bundle args = new Bundle();
        args.putSerializable(VideoFullscreenFragment.class.getName(), (Serializable) videos);
        VideoFullscreenFragment fragment = new VideoFullscreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_fullscreen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new VideoFullscreenAdapter(requireActivity(),
                (List<Video>) getArguments().getSerializable(VideoFullscreenFragment.class.getName())));
        new LinearSnapHelper().attachToRecyclerView(recyclerView);
    }
}
