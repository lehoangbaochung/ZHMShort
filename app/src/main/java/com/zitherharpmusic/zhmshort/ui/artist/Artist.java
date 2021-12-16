package com.zitherharpmusic.zhmshort.ui.artist;

import androidx.annotation.NonNull;

import com.zitherharpmusic.zhmshort.data.DataProvider;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.data.Music;
import com.zitherharpmusic.zhmshort.ui.video.Video;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Music {
    private final String playlistId = "";
    private List<Song> songs;
    private List<Video> videos;

    public Artist(String id) {
        this.id = id;
    }

    public String getPlaylistUrl() {
        return "https://www.youtube.com/playlist?list=" + playlistId;
    }

    public List<Song> getSongs() {
        if (songs == null) {
            songs = new ArrayList<>();
            for (Song song : DataProvider.getSongs()) {
                for (Artist artist : song.getArtists()) {
                    if (artist.getId().equals(id)) {
                        songs.add(song);
                    }
                }
            }
        }
        return songs;
    }

    public List<Video> getVideos() {
        if (videos == null) {
            videos = new ArrayList<>();
            for (Video video : DataProvider.getVideos()) {
                for (Artist artist : video.getArtists()) {
                    if (artist.getId().equals(id)) {
                        videos.add(video);
                    }
                }
            }
        }
        return videos;
    }
}
