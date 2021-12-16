package com.zitherharpmusic.zhmshort.ui.song;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.data.PhotoQuality;
import com.zitherharpmusic.zhmshort.ui.artist.ArtistActivity;
import com.zitherharpmusic.zhmshort.util.ListenerUtils;
import com.zitherharpmusic.zhmshort.util.MainUtils;

import java.util.Objects;

public class SongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Song song = (Song) getIntent().getSerializableExtra(SongActivity.class.getName());
        // TODO: find views
        ImageView photo = findViewById(R.id.photo);
        TextView title = findViewById(R.id.title);
        TextView subtitle = findViewById(R.id.subtitle);
        TextView description = findViewById(R.id.description);
        ImageButton followButton = findViewById(R.id.follow);
        ImageButton shareButton = findViewById(R.id.share);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        title.setText(song.getName(Language.VIETNAMESE));
        subtitle.setText(song.getName(Language.SIMPLIFIED_CHINESE));
        description.setText(song.toString(Language.VIETNAMESE));
        description.setOnClickListener(ListenerUtils.launchActivity(this, ArtistActivity.class, song.getArtists().get(0)));
        shareButton.setOnClickListener(ListenerUtils.launchShareIntent(this, song.getShareUrl()));

        MainUtils.loadImage(this, photo, song.getPhotoUrl(PhotoQuality.HQDEFAULT));
        photo.setOnClickListener(v -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ImageView photoView = new ImageView(this);
            MainUtils.loadImage(this, photoView, song.getPhotoUrl(PhotoQuality.SDDEFAULT));
            ad.setView(photoView);
            ad.show();
        });
        // TODO: others
        SongAdapter songAdapter = new SongAdapter(this, song);
        viewPager.setAdapter(songAdapter);
        songAdapter.attach(tabLayout, viewPager);
    }
}
