package com.zitherharpmusic.zhmshort.ui.artist;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.data.PhotoQuality;
import com.zitherharpmusic.zhmshort.ui.user.User;
import com.zitherharpmusic.zhmshort.util.ListenerUtils;
import com.zitherharpmusic.zhmshort.util.MainUtils;
import com.zitherharpmusic.zhmshort.R;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);
        getSupportActionBar().hide();
        onViewCreated();
    }

    private void onViewCreated() {
        // TODO: find views
        ImageView photo = findViewById(R.id.photo);
        TextView title = findViewById(R.id.title);
        TextView subtitle = findViewById(R.id.subtitle);
        TextView description = findViewById(R.id.description);
        ImageButton followButton = findViewById(R.id.follow);
        ImageButton shareButton = findViewById(R.id.share);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        // TODO: load intent
        Artist artist = (Artist) getIntent().getSerializableExtra(ArtistActivity.class.getName());
        MainUtils.loadImage(this, photo, artist.getPhotoUrl(PhotoQuality.SMALL));
        // TODO: set text
        title.setText(artist.getName(Language.VIETNAMESE));
        subtitle.setText(artist.getName(Language.SIMPLIFIED_CHINESE));
        description.setText(artist.getDescription(Language.VIETNAMESE));
        // TODO: set button listeners
        User user = new User(this);
        if (user.isLoggedIn()) {
            if (!user.getArtists().contains(artist)) {
                followButton.setImageResource(R.drawable.ic_favourite_border_24);
            } else {
                followButton.setImageResource(R.drawable.ic_favourite_24);
            }
        }

        photo.setOnClickListener(v -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ImageView photoView = new ImageView(this);
            MainUtils.loadImage(this, photoView, artist.getPhotoUrl(PhotoQuality.LARGE));
            ad.setView(photoView);
            ad.show();
        });
        //Toast.makeText(this, artist.getSongs() + "-" + artist.getVideos(), Toast.LENGTH_SHORT).show();
        shareButton.setOnClickListener(ListenerUtils.launchShareIntent(this, artist.getPlaylistUrl()));
        // TODO: others
        ArtistAdapter artistAdapter = new ArtistAdapter(this, artist);
        viewPager.setAdapter(artistAdapter);
        artistAdapter.attach(tabLayout, viewPager);
    }
}