package com.zitherharpmusic.zhmshort.data;

import androidx.annotation.Nullable;

import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.video.Video;

import java.util.ArrayList;
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

    @Nullable
    public static Song findSong(String songId) {
        for (Song song : DataProvider.getSongs()) {
            if (song.getId().equals(songId)) {
                return song;
            }
        }
        return null;
    }
}
