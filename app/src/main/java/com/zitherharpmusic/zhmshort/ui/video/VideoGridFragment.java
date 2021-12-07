package com.zitherharpmusic.zhmshort.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitherharpmusic.zhmshort.R;

import java.io.Serializable;
import java.util.List;

public class VideoGridFragment extends Fragment {

    public static VideoGridFragment newInstance(List<Video> videos) {
        Bundle args = new Bundle();
        args.putSerializable(VideoGridFragment.class.getName(), (Serializable) videos);
        VideoGridFragment fragment = new VideoGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new VideoGridAdapter(requireActivity(),
                (List<Video>) getArguments().getSerializable(VideoGridFragment.class.getName())));
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
    }
}
