package com.zitherharpmusic.zhmshort.ui.user;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.ui.empty.EmptyFragment;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.video.Video;
import com.zitherharpmusic.zhmshort.ui.video.VideoGridFragment;

import java.util.List;

public class UserAdapter extends FragmentStateAdapter {
    private final Fragment fragment;
    private final List<Song> songs;
    private final List<Video> videos;

    public UserAdapter(@NonNull Fragment fragment, User user) {
        super(fragment);
        this.fragment = fragment;
        songs = user.getSongs();
        videos = user.getVideos();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            if (songs.size() > 0) {
                return VideoGridFragment.newInstance(videos);
            } else {
                return EmptyFragment.newInstance(fragment.getString(R.string.empty));
            }
        } else if (position == 1){
            if (videos.size() > 0) {
                return VideoGridFragment.newInstance(videos);
            } else {
                return EmptyFragment.newInstance(fragment.getString(R.string.empty));
            }
        } else {
            return EmptyFragment.newInstance(fragment.getString(R.string.empty));
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void attach(TabLayout tabLayout, ViewPager2 viewPager) {
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(fragment.getString(R.string.audios) + " " + songs.size());
            } else if (position == 1){
                tab.select();
                tab.setText(fragment.getString(R.string.shorts) + " " + videos.size());
            } else {
                tab.setText(fragment.getString(R.string.artists) + " " + videos.size());
            }
        }).attach();
        tabLayout.bringToFront();
    }
}
