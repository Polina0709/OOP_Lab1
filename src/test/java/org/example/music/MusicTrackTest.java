package org.example.music;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MusicTrackTest {

    @Test
    public void testMusicTrackCreation() {
        MusicTrack track = new MusicTrack("Bohemian Rhapsody", "Queen", 354, Genre.ROCK);
        assertEquals("Bohemian Rhapsody", track.getTitle());
        assertEquals("Queen", track.getArtist());
        assertEquals(354, track.getDuration());
        assertEquals(Genre.ROCK, track.getGenre());
    }
}

