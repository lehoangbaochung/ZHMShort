package com.zitherharpmusic.zhmshort.ui.artist;

public enum ArtistPhoto {
    SMALL(300),
    MEDIUM(500),
    LARGE(800);

    ArtistPhoto(int pixel) {
        this.pixel = pixel;
    }

    private final int pixel;

    public String getSize() {
        return pixel + "x" + pixel;
    }
}
