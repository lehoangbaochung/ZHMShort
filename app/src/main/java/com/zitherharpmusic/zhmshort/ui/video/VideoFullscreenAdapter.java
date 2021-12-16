package com.zitherharpmusic.zhmshort.ui.video;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController;
import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.data.PhotoQuality;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.artist.ArtistActivity;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.song.SongActivity;
import com.zitherharpmusic.zhmshort.util.ListenerUtils;
import com.zitherharpmusic.zhmshort.util.MainUtils;

import java.util.List;

public class VideoFullscreenAdapter extends RecyclerView.Adapter<VideoFullscreenAdapter.ViewHolder> {
    private final FragmentActivity context;
    private final List<Video> videos;

    public VideoFullscreenAdapter(FragmentActivity context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_fullscreen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videos.get(position);
        if (video.getArtists().size() == 0) {
            Toast.makeText(context, video.getId(), Toast.LENGTH_SHORT).show();
            return;
        }
        Artist artist = video.getArtists().get(0);
        holder.videoName.setText(video.getName(Language.VIETNAMESE));
        holder.artistName.setText(String.format("@%s", artist.getName(Language.VIETNAMESE)));
        holder.artistName.setOnClickListener(ListenerUtils.launchActivity(context, ArtistActivity.class, artist));

        holder.songName.setSelected(true);
        List<Song> songs = video.getSongs();
        if (songs.size() > 0) {
            holder.songName.setText(songs.get(0).toString(Language.VIETNAMESE));
            holder.songName.setOnClickListener(ListenerUtils.launchActivity(context, SongActivity.class, songs.get(0)));
        }
//        holder.videoView.setOnFocusChangeListener((v, hasFocus) -> {
//            holder.videoView.getYouTubePlayerWhenReady(youTubePlayer -> {
//                youTubePlayer.loadVideo(video.getId(), 0);
//                if (hasFocus) {
//                    Toast.makeText(context, "Chạm", Toast.LENGTH_SHORT).show();
//                    youTubePlayer.play();
//                } else {
//                    Toast.makeText(context, "hết chạm", Toast.LENGTH_SHORT).show();
//                    youTubePlayer.pause();
//                }
//            });
//        });
        //holder.videoView.requestFocusFromTouch();
        //holder.videoView.getOnFocusChangeListener().onFocusChange(holder.videoView, true);
        holder.videoView.addYouTubePlayerListener(ListenerUtils.requireYoutubePlayerListener(video.getId()));
        holder.favouriteCount.setText(video.getFavouriteCountToString());
        holder.commentCount.setText(video.getCommentCountToString());

        holder.artistImage.setOnClickListener(ListenerUtils.launchActivity(context, ArtistActivity.class, video.getArtists().get(0)));
        MainUtils.loadImage(context, holder.artistImage, artist.getPhotoUrl(PhotoQuality.SMALL));
        holder.shareButton.setOnClickListener(ListenerUtils.launchShareIntent(context, video.getShareUrl()));
    }
    @Override
    public int getItemCount() {
        return videos.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected final YouTubePlayerView videoView;
        protected final ImageView artistImage;
        protected final TextView artistName, videoName, songName;
        protected final TextView favouriteCount, commentCount;
        protected final ImageButton favouriteButton, commentButton, shareButton;

        public ViewHolder(View view) {
            super(view);
            videoView = view.findViewById(R.id.video_view);
            artistName = view.findViewById(R.id.artist_name);
            videoName = view.findViewById(R.id.video_name);
            songName = view.findViewById(R.id.song_name);
            artistImage = view.findViewById(R.id.artist_image);
            favouriteCount = view.findViewById(R.id.favourite_count);
            commentCount = view.findViewById(R.id.comment_count);
            favouriteButton = view.findViewById(R.id.favourite_button);
            commentButton = view.findViewById(R.id.comment_button);
            shareButton = view.findViewById(R.id.share_button);
        }
    }
}
