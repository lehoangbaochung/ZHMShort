package com.zitherharpmusic.zhmshort.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.data.DataProvider;
import com.zitherharpmusic.zhmshort.data.DataUtils;
import com.zitherharpmusic.zhmshort.ui.login.LoginFragment;
import com.zitherharpmusic.zhmshort.ui.user.User;
import com.zitherharpmusic.zhmshort.ui.video.Video;
import com.zitherharpmusic.zhmshort.ui.video.VideoFullscreenFragment;

import java.util.Collections;
import java.util.List;

public class HomeAdapter extends FragmentStateAdapter {
    private final Fragment context;
    private final boolean isLoggedIn;
    private List<Video> recommendVideos;

    public HomeAdapter(@NonNull Fragment fragment, User user) {
        super(fragment);
        context = fragment;
        isLoggedIn = user.isLoggedIn();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            if (isLoggedIn) {
                return VideoFullscreenFragment.newInstance(DataProvider.getVideos());
            } else {
                return new LoginFragment();
            }
        }
        return VideoFullscreenFragment.newInstance(DataProvider.getVideos());
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void attach(TabLayout tabLayout, ViewPager2 viewPager) {
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(context.getString(R.string.following));
            } else if (position == 1) {
                tab.select();
                tab.setText(context.getString(R.string.recommend));
            } else {
                tab.setText(context.getString(R.string.recent));
            }
        }).attach();
        tabLayout.bringToFront();
    }
}