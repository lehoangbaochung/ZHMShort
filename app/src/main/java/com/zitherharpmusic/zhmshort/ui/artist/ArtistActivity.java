package com.zitherharpmusic.zhmshort.ui.artist;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zitherharpmusic.zhmshort.data.DataUtils;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.ui.user.User;
import com.zitherharpmusic.zhmshort.util.ListenerUtils;
import com.zitherharpmusic.zhmshort.util.MainUtils;
import com.zitherharpmusic.zhmshort.R;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);
        onViewCreated();
    }

    private void onViewCreated() {
        // TODO: find views
        ImageView artistPhoto = findViewById(R.id.photo);
        TextView artistTitle = findViewById(R.id.title);
        TextView artistSubtitle = findViewById(R.id.subtitle);
        TextView artistDescription = findViewById(R.id.description);
        ImageButton followButton = findViewById(R.id.follow);
        ImageButton shareButton = findViewById(R.id.share);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        // TODO: load intent
        String artistId = getIntent().getStringExtra(ArtistActivity.class.getSimpleName());
        Artist artist = DataUtils.findArtist(artistId);
        assert artist != null;
        MainUtils.loadImage(this, artistPhoto, artist.getPhotoUrl(ArtistPhoto.SMALL));
        // TODO: set text
        artistTitle.setText(artist.getName(Language.VIETNAMESE));
        artistSubtitle.setText(artist.getName(Language.SIMPLIFIED_CHINESE));
        artistDescription.setText(artist.getDescription(Language.VIETNAMESE));
        // TODO: set button listeners
        User user = new User(this);
        if (user.isLoggedIn()) {
            if (!user.getArtists().contains(artist)) {
                followButton.setImageResource(R.drawable.ic_favourite_border_24);
            } else {
                followButton.setImageResource(R.drawable.ic_favourite_24);
            }
        }
        shareButton.setOnClickListener(ListenerUtils.launchShareIntent(this, artist.getPlaylistUrl()));
        // TODO: others
        ArtistAdapter artistAdapter = new ArtistAdapter(this, artist);
        viewPager.setAdapter(artistAdapter);
        artistAdapter.attach(tabLayout, viewPager);
    }
}