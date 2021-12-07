package com.zitherharpmusic.zhmshort.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.zitherharpmusic.zhmshort.data.DataProvider;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.video.Video;

import java.util.ArrayList;
import java.util.List;

import static com.zitherharpmusic.zhmshort.data.Music.EMPTY_CHARACTER;
import static com.zitherharpmusic.zhmshort.data.Music.SPLIT_CHARACTER;

public class User {
    private static final String ACCOUNT_NAME = "accountName";
    private static final String ACCOUNT_SONG = "accountSong";
    private static final String ACCOUNT_VIDEO = "accountVideo";
    private static final String ACCOUNT_ARTIST = "accountArtist";

    private final Activity activity;
    private SharedPreferences.Editor editor;

    public User(Activity activity) {
        this.activity = activity;
    }

    public String getId() {
        return activity.getPreferences(Context.MODE_PRIVATE)
                .getString(ACCOUNT_NAME, null);
    }

    public String getName() {
        return getId().substring(0, getId().indexOf('@'));
    }
//
//    public void addFollowingArtist(String artistId) {
//        editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
//        if (!getFollowingArtistId().equals(EMPTY_CHARACTER)) {
//            editor.putString(ACCOUNT_ARTIST, String.format("%s/%s",
//                    getFollowingArtistId(), artistId));
//        } else {
//            editor.putString(ACCOUNT_ARTIST, artistId);
//        }
//        editor.apply();
//    }

    public void logIn(String accountName) {
        editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString(ACCOUNT_NAME, accountName).apply();
    }

    public void logOut() {
        editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
        editor.clear().apply();
    }

    public boolean isLoggedIn() {
        return getId() != null;
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        String[] songIds = activity.getPreferences(Context.MODE_PRIVATE)
                .getString(ACCOUNT_VIDEO, EMPTY_CHARACTER).split(SPLIT_CHARACTER);
        for (String songId : songIds) {
            for (Song song : DataProvider.getInstance().getSongs()) {
                if (song.getId().equals(songId)) {
                    songs.add(song);
                }
            }
        }
        return songs;
    }

    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<>();
        String[] videoIds = activity.getPreferences(Context.MODE_PRIVATE)
                .getString(ACCOUNT_VIDEO, EMPTY_CHARACTER).split(SPLIT_CHARACTER);
        for (String videoId : videoIds) {
            for (Video video : DataProvider.getInstance().getVideos()) {
                if (video.getId().equals(videoId)) {
                    videos.add(video);
                }
            }
        }
        return videos;
    }

    public List<Artist> getArtists() {
        List<Artist> artists = new ArrayList<>();
        String[] artistIds = activity.getPreferences(Context.MODE_PRIVATE)
                .getString(ACCOUNT_ARTIST, EMPTY_CHARACTER).split(SPLIT_CHARACTER);
        for (String artistId : artistIds) {
            for (Artist artist : DataProvider.getInstance().getArtists()) {
                if (artist.getId().equals(artistId)) {
                    artists.add(artist);
                }
            }
        }
        return artists;
    }
}
