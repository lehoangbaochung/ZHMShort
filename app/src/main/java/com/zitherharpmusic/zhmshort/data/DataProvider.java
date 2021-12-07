package com.zitherharpmusic.zhmshort.data;

import android.util.Log;

import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.song.Song;
import com.zitherharpmusic.zhmshort.ui.video.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataProvider {
    private static final List<Song> songs = new ArrayList<>();
    private static final List<Video> videos = new ArrayList<>();
    private static final List<Artist> artists = new ArrayList<>();

    private static Song song;
    private static Video video;
    private static Artist artist;

    private static JSONArray valueArray, jsonArray;
    private static String primaryId, referenceId;
    private static String vietnameseName, pinyinName, simplifiedChineseName, traditionalChineseName;
    private static String vietnameseDescription, simplifiedChineseDescription, traditionalChineseDescription;

    public static void initialize() {
        try {
            loadSongs();
            loadVideos();
            loadArtists();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<Song> getSongs() {
        return songs;
    }

    public static List<Video> getVideos() {
        return videos;
    }

    public static List<Artist> getArtists() {
        return artists;
    }

    private static void loadSongs() throws JSONException {
        jsonArray = getJSONArray(getSheetUrl("song"), "values");
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

    private static void loadVideos() throws JSONException {
        jsonArray = getJSONArray(getSheetUrl("short"), "values");
        String artistId, songId;
        for (int i = 0; i < jsonArray.length(); i++) {
            valueArray = jsonArray.getJSONArray(i);
            primaryId = valueArray.getString(Music.PRIMARY_ID);
            artistId = valueArray.getString(Video.ARTIST_ID);
            songId = valueArray.isNull(Video.SONG_ID) ? "" : valueArray.getString(Video.SONG_ID);
            video = new Video(primaryId, artistId, songId);
            videos.add(video);
        }
    }

    private static void loadArtists() throws JSONException {
        jsonArray = getJSONArray(getSheetUrl("artist"), "values");
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
    }

    private static String getSheetUrl(String tableName) {
        return "https://sheets.googleapis.com/v4/spreadsheets/1znQOtTDJz0UqDs0uB2MQZV3wN0l_J0TrU44d9chH2SI/values/" +
                tableName + "?key=AIzaSyAD91OiEeWRoqhsw0peq94qg5joZe47r_s";
    }

    private static JSONArray getJSONArray(String url, String arrayName) throws JSONException {
        JSONArray jsonArray = null;
        try {
            Scanner scanner = new Scanner(new URL(url).openStream());
            StringBuilder jsonString = new StringBuilder();
            Log.e("scaanner", scanner.next());
            Log.e("scaannerLine", scanner.nextLine());
            jsonString.append(scanner.next());
            while (scanner.hasNextLine()) {
                jsonString.append(scanner.nextLine());
            }
            JSONObject jsonObj = new JSONObject(jsonString.toString());
            jsonArray = jsonObj.getJSONArray(arrayName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
