package com.zitherharpmusic.zhmshort.ui.empty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.ui.video.VideoFullscreenFragment;

import java.io.Serializable;

public class EmptyFragment extends Fragment {

    public static EmptyFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(EmptyFragment.class.getName(), text);
        EmptyFragment fragment = new EmptyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView text = view.findViewById(R.id.text);
        text.setText(getArguments().getString(EmptyFragment.class.getName()));
    }
}