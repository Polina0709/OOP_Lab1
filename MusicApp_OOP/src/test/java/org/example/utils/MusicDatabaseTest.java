package org.example.utils;

import org.example.music.MusicTrack;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class MusicDatabaseTest {

    @Test
    public void testGetAvailableTracks() {
        List<MusicTrack> tracks = MusicDatabase.getAvailableTracks();
        assertFalse(tracks.isEmpty());
    }
}
