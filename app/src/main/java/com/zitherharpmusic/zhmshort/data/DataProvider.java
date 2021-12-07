package com.zitherharpmusic.zhmshort.data;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.video.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataProvider {
    private final List<Song> songs;
    private final List<Video> videos = new ArrayList<>();
    private final List<Artist> artists;

    private static DataProvider instance;

    public static DataProvider getInstance() {
        try {
            instance = new DataProvider();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public DataProvider() throws JSONException {
        JSONArray valueArray, jsonArray;
        String primaryId, referenceId;
        String vietnameseName, pinyinName, simplifiedChineseName, traditionalChineseName;
        String vietnameseDescription, simplifiedChineseDescription, traditionalChineseDescription;
        // TODO: load videos
        jsonArray = getJSONArray(getSheetUrl("short"), "values");
        Log.e("videojson", jsonArray.toString());
        Video video;
        String artistId, songId;
        for (int i = 0; i < jsonArray.length(); i++) {
            valueArray = jsonArray.getJSONArray(i);
            primaryId = valueArray.getString(Music.PRIMARY_ID);
            artistId = valueArray.getString(Video.ARTIST_ID);
            songId = valueArray.isNull(Video.SONG_ID) ? "" : valueArray.getString(Video.SONG_ID);
            video = new Video(primaryId, artistId, songId);
            videos.add(video);
        }
        // TODO: load artists
        artists = new ArrayList<>();
        jsonArray = getJSONArray(getSheetUrl("artist"), "values");
        Log.e("artjson", jsonArray.toString());
        Artist artist;
        for (int i = 0; i < jsonArray.length(); i++) {
            valueArray = jsonArray.getJSONArray(i);
            primaryId = valueArray.getString(Music.PRIMARY_ID);
            vietnameseName = valueArray.getString(Music.VIETNAMESE_NAME);
            pinyinName = valueArray.getString(Music.PINYIN_NAME);
            simplifiedChineseName = valueArray.getString(Music.SIMPLIFIED_CHINESE_NAME);
            traditionalChineseName = valueArray.getString(Music.TRADITIONAL_CHINESE_NAME);
            vietnameseDescription = valueArray.getString(Music.VIETNAMESE_DESCRIPTION);
            simplifiedChineseDescription = valueArray.getString(Music.SIMPLIFIED_CHINESE_DESCRIPTION);
            traditionalChineseDescription = valueArray.getString(Music.TRADITIONAL_CHINESE_DESCRIPTION);
            artist = new Artist(primaryId);
            artist.setName(vietnameseName, pinyinName, simplifiedChineseName, traditionalChineseName);
            artist.setDescription(vietnameseDescription, simplifiedChineseDescription, traditionalChineseDescription);
            artists.add(artist);
        }
        // TODO: load songs
        songs = new ArrayList<>();
        jsonArray = getJSONArray(getSheetUrl("song"), "values");
        Log.e("songjson", jsonArray.toString());
        Song song;
        for (int i = 0; i < jsonArray.length(); i++) {
            valueArray = jsonArray.getJSONArray(i);
            primaryId = valueArray.getString(Music.PRIMARY_ID);
            referenceId = valueArray.getString(Music.REFERENCE_ID);
            vietnameseName = valueArray.getString(Music.VIETNAMESE_NAME);
            pinyinName = valueArray.getString(Music.PINYIN_NAME);
            simplifiedChineseName = valueArray.getString(Music.SIMPLIFIED_CHINESE_NAME);
            traditionalChineseName = valueArray.getString(Music.TRADITIONAL_CHINESE_NAME);
            vietnameseDescription = valueArray.getString(Music.VIETNAMESE_DESCRIPTION);
            simplifiedChineseDescription = valueArray.getString(Music.SIMPLIFIED_CHINESE_DESCRIPTION);
            traditionalChineseDescription = valueArray.getString(Music.TRADITIONAL_CHINESE_DESCRIPTION);
            song = new Song(primaryId, referenceId);
            song.setName(vietnameseName, pinyinName, simplifiedChineseName, traditionalChineseName);
            song.setDescription(vietnameseDescription, simplifiedChineseDescription, traditionalChineseDescription);
            songs.add(song);
        }
    }

    private String getSheetUrl(String tableName) {
        return "https://sheets.googleapis.com/v4/spreadsheets/1znQOtTDJz0UqDs0uB2MQZV3wN0l_J0TrU44d9chH2SI/values/" +
                tableName + "?key=AIzaSyAD91OiEeWRoqhsw0peq94qg5joZe47r_s";
    }

    private JSONArray getJSONArray(String url, String arrayName) throws JSONException {
        JSONArray jsonArray = null;
        try {
            Scanner scanner = new Scanner(new URL(url).openStream());
            StringBuilder jsonString = new StringBuilder();
            jsonString.append(scanner.next());
            while (scanner.hasNext()) {
                jsonString.append(scanner.next());
            }
            JSONObject jsonObj = new JSONObject(jsonString.toString());
            jsonArray = jsonObj.getJSONArray(arrayName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
