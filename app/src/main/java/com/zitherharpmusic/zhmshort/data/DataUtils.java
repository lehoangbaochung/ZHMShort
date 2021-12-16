package com.zitherharpmusic.zhmshort.data;

import androidx.annotation.Nullable;

import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.user.User;
import com.zitherharpmusic.zhmshort.ui.video.Video;
import com.zitherharpmusic.zhmshort.ui.video.VideoType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataUtils {
    public static List<Video> getExampleVideos() {
        List<Video> videos = new ArrayList<>();
        videos.add(new Video("v8fHgUjVGR8", "001B2drs3Jq4EX", "Cẩm Linh"));
        videos.add(new Video("YZYP6ECeJNw", "001B2drs3Jq4EX", "Dã Tiểu Mã"));
        videos.add(new Video("YZYP6ECeJNw", "001B2drs3Jq4EX", "Dã Tiểu Mã"));
        videos.add(new Video("v8fHgUjVGR8", "001B2drs3Jq4EX", "Cẩm Linh"));
        videos.add(new Video("v8fHgUjVGR8", "001B2drs3Jq4EX", "Cẩm Linh"));
        videos.add(new Video("v8fHgUjVGR8", "001B2drs3Jq4EX", "Cẩm Linh"));
        videos.add(new Video("YZYP6ECeJNw", "001B2drs3Jq4EX", "Dã Tiểu Mã"));
        videos.add(new Video("YZYP6ECeJNw", "001B2drs3Jq4EX", "Dã Tiểu Mã"));
        videos.add(new Video("v8fHgUjVGR8", "001B2drs3Jq4EX", "Cẩm Linh"));
        videos.add(new Video("v8fHgUjVGR8", "001B2drs3Jq4EX", "Cẩm Linh"));
        return videos;
    }

    @Nullable
    public static Artist findArtist(String artistId) {
        for (Artist artist : DataProvider.getArtists()) {
            if (artist.getId().equals(artistId)) {
                return artist;
            }
        }
        return null;
    }

    public static List<Song> findRecommendSongs(Song song) {
        List<Song> songs = new ArrayList<>();
        for (Song s : DataProvider.getSongs()) {
            for (Artist artist : s.getArtists()) {
                if (song.getArtists().contains(artist) && !song.getId().equals(s.getId())) {
                    songs.add(s);
                }
            }
        }
        return songs;
    }

    public static List<Video> getVideos(User user, VideoType videoType) {
        List<Video> videos = new ArrayList<>();
        switch (videoType) {
            case RECENT:
                videos = DataProvider.getVideos();
                break;
            case FOLLOWING:
                for (Artist artist : user.getArtists()) {
                    videos.addAll(artist.getVideos());
                }
                break;
            case RECOMMEND:
                videos = DataProvider.getVideos();
                Collections.shuffle(videos);
                break;
        }
        return videos;
    }
}
