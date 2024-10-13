package org.example.music;

import java.io.Serializable;

public class MusicTrack implements Serializable {
    private String title;
    private String artist;
    private int duration; // в секундах
    private Genre genre;

    public MusicTrack(String title, String artist, int duration, Genre genre) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Track: " + title + " by " + artist + " [" + genre + "] (" + duration + " sec)";
    }
}
