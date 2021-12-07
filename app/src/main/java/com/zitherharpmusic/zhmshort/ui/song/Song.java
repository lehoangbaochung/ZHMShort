package com.zitherharpmusic.zhmshort.ui.song;

import com.zitherharpmusic.zhmshort.data.DataProvider;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.data.Music;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;

import java.util.ArrayList;
import java.util.List;

public class Song extends Music {
    private final String artistIds;

    private List<Artist> artists;

    public Song(String id, String artistIds) {
        this.id = id;
        this.artistIds = artistIds;
    }

    public List<Artist> getArtists() {
        if (artists == null) {
            artists = new ArrayList<>();
            for (String artistId : artistIds.split(SPLIT_CHARACTER)) {
                for (Artist artist : DataProvider.getArtists()) {
                    if (artist.getId().equals(artistId)) {
                        artists.add(artist);
                    }
                }
            }
        }
        return artists;
    }

    public String toString(Language language) {
        switch (language) {
            case VIETNAMESE:
                return vietnameseName;
            case PINYIN:
                return pinyinName;
            case SIMPLIFIED_CHINESE:
                return simplifiedChineseName;
            case TRADITIONAL_CHINESE:
                return traditionalChineseName;
        }
        return null;
    }
}
