package org.example.utils;

import org.example.music.Genre;
import org.example.music.MusicTrack;

import java.util.ArrayList;
import java.util.List;

public class MusicDatabase {
    public static List<MusicTrack> getAvailableTracks() {
        List<MusicTrack> tracks = new ArrayList<>();

        tracks.add(new MusicTrack("Levitating", "Dua Lipa", 203, Genre.POP));
        tracks.add(new MusicTrack("Blinding Lights", "The Weeknd", 200, Genre.POP));
        tracks.add(new MusicTrack("7 rings", "Ariana Grande", 179, Genre.POP));
        tracks.add(new MusicTrack("Cruel Summer", "Taylor Swift", 178, Genre.POP));
        tracks.add(new MusicTrack("Bohemian Rhapsody", "Queen", 354, Genre.ROCK));
        tracks.add(new MusicTrack("Stairway to Heaven", "Led Zeppelin", 482, Genre.ROCK));
        tracks.add(new MusicTrack("Smells Like Teen Spirit", "Nirvana", 301, Genre.ROCK));
        tracks.add(new MusicTrack("So What", "Miles Davis", 565, Genre.JAZZ));
        tracks.add(new MusicTrack("Summertime", "Ella Fitzgerald", 178, Genre.JAZZ));
        tracks.add(new MusicTrack("My Favorite Things", "John Coltrane", 820, Genre.JAZZ));

        return tracks;
    }
}

