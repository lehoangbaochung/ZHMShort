package com.zitherharpmusic.zhmshort.ui.video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.artist.ArtistPhoto;
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
        Artist artist = video.getArtists().get(0);
        holder.videoName.setText(video.getName(Language.VIETNAMESE));
        holder.artistName.setText(artist.getName(Language.VIETNAMESE));
        holder.artistName.setOnClickListener(ListenerUtils.launchArtistActivity(context, artist.getId()));
        //holder.songName.setText(video.getDescription(Language.VIETNAMESE));
        holder.songName.setSelected(true);
        holder.videoView.addYouTubePlayerListener(ListenerUtils.requireYoutubePlayerListener(video.getId()));
        holder.favouriteCount.setText(video.getFavouriteCountToString());
        holder.commentCount.setText(video.getCommentCountToString());

        holder.artistImage.setOnClickListener(ListenerUtils.launchArtistActivity(context, artist.getId()));
        MainUtils.loadImage(context, holder.artistImage, artist.getPhotoUrl(ArtistPhoto.SMALL));
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
