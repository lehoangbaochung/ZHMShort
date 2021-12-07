package com.zitherharpmusic.zhmshort.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.zitherharpmusic.zhmshort.ui.artist.ArtistActivity;
import com.zitherharpmusic.zhmshort.ui.video.Video;
import com.zitherharpmusic.zhmshort.ui.video.VideoFullScreenActivity;
import com.zitherharpmusic.zhmshort.ui.video.VideoFullscreenFragment;

import java.io.Serializable;
import java.util.List;

public class ListenerUtils {
    public static YouTubePlayerListener requireYoutubePlayerListener(String videoId) {
        return new YouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }

            @Override
            public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
                if (playerState.equals(PlayerConstants.PlayerState.ENDED))  {
                    youTubePlayer.play();
                }
            }

            @Override
            public void onPlaybackQualityChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackQuality playbackQuality) {

            }

            @Override
            public void onPlaybackRateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackRate playbackRate) {

            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError playerError) {

            }

            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoDuration(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoLoadedFraction(YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoId(YouTubePlayer youTubePlayer, String s) {

            }

            @Override
            public void onApiChange(YouTubePlayer youTubePlayer) {

            }
        };
    }

    public static View.OnClickListener launchShareIntent(Context context, String text) {
        return v -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, text);
            shareIntent.setType("text/plain");
            context.startActivity(shareIntent);
        };
    }

    public static View.OnClickListener launchArtistActivity(Context context, String artistId) {
        return v -> {
            Intent intent = new Intent(context, ArtistActivity.class);
            intent.putExtra(ArtistActivity.class.getSimpleName(), artistId);
            context.startActivity(intent);
        };
    }

    public static View.OnClickListener launchVideoFullscreenActivity(Context context, List<Video> videos, int position) {
        return v -> {
            Intent intent = new Intent(context, VideoFullScreenActivity.class);
            Class<?>[] classes = VideoFullScreenActivity.class.getClasses();
            intent.putExtra(classes[0].getName(), (Serializable) videos);
            intent.putExtra(classes[1].getName(), position);
            context.startActivity(intent);
        };
    }
}
