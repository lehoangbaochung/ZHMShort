package com.zitherharpmusic.zhmshort.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zitherharpmusic.zhmshort.R;

public class UserFragment extends Fragment {
    private User user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        user = new User(requireActivity());
        if (user.isLoggedIn()) {
            return inflater.inflate(R.layout.fragment_user, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_login, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!user.isLoggedIn()) return;
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        UserAdapter userAdapter = new UserAdapter(this, user);
        viewPager.setAdapter(userAdapter);
        userAdapter.attach(tabLayout, viewPager);
        // TODO: find views
        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        title.setText(user.getName());
        subtitle.setText(user.getId());
    }

}
