package com.zitherharpmusic.zhmshort.ui.video;

import androidx.annotation.NonNull;

import com.zitherharpmusic.zhmshort.data.DataProvider;
import com.zitherharpmusic.zhmshort.data.Music;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.song.Song;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Video extends Music {
    public static final int ARTIST_ID = 1;
    public static final int SONG_ID = 2;

    public final String artistId;
    public final String songId;
    public int viewCount = 0;
    public int favouriteCount = 0;
    public int commentCount = 0;

    private List<Song> songs;
    private List<Artist> artists;

    public Video(String id, String artistId, String songId) {
        this.id = id;
        this.artistId = artistId;
        this.songId = songId;
    }

    public String getShareUrl() {
        return "https://youtu.be/" + id;
    }

    public String getThumbnailUrl(@NonNull VideoThumbnail videoThumbnail) {
        return String.format("https://i.ytimg.com/vi/%s/%s.jpg", id, videoThumbnail.name().toLowerCase());
    }

    public void setStatistics(int viewCount, int favouriteCount, int commentCount) {
        this.viewCount = viewCount;
        this.favouriteCount = favouriteCount;
        this.commentCount = commentCount;
    }

    public List<Artist> getArtists() {
        if (artists == null) {
            artists = new ArrayList<>();
            for (Artist artist : DataProvider.getInstance().getArtists()) {
                if (artist.getId().equals(artistId)) {
                    artists.add(artist);
                }
            }
        }
        return artists;
    }

    public List<Song> getSongs() {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        return songs;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public String getFavouriteCountToString() {
        return favouriteCount + "";
    }

    public String getCommentCountToString() {
        return commentCount + "";
    }
}
