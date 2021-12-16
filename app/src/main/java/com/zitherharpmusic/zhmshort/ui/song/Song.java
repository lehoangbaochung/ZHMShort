package com.zitherharpmusic.zhmshort.ui.song;

import androidx.annotation.NonNull;

import com.zitherharpmusic.zhmshort.data.DataProvider;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.data.Music;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.video.Video;
import com.zitherharpmusic.zhmshort.ui.video.VideoThumbnail;

import java.util.ArrayList;
import java.util.List;

public class Song extends Music {
    private final String artistIds;

    private List<Video> videos;
    private List<Artist> artists;

    public Song(String id, String artistIds) {
        this.id = id;
        this.artistIds = artistIds;
    }

    public List<Video> getVideos() {
        if (videos == null) {
            videos = new ArrayList<>();
            for (Video video : DataProvider.getVideos()) {
                for (Song song : video.getSongs()) {
                    if (song.getId().equals(id)) {
                        videos.add(video);
                    }
                }
            }
        }
        return videos;
    }

    public List<Artist> getArtists() {
        if (artists == null) {
            artists = new ArrayList<>();
            for (Artist artist : DataProvider.getArtists()) {
                for (String artistId : artistIds.split(SPLIT_CHARACTER)) {
                    if (artist.getId().equals(artistId)) {
                        artists.add(artist);
                    }
                }
            }
        }
        return artists;
    }

    public String toString(Language language) {
        StringBuilder artistNames = new StringBuilder();
        for (Artist artist : getArtists()) {
            artistNames.append(artist.getName(language)).append(SPLIT_CHARACTER);
        }
        return getName(language) + " - " + artistNames.toString().substring(0, artistNames.toString().length() - 1);
    }
}
