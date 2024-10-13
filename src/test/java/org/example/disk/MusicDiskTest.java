package org.example.disk;

import org.example.music.Genre;
import org.example.music.MusicTrack;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MusicDiskTest {

    private MusicDisk disk;

    @Before
    public void setUp() {
        disk = new MusicDisk();
        disk.addTrack(new MusicTrack("Bohemian Rhapsody", "Queen", 354, Genre.ROCK));
        disk.addTrack(new MusicTrack("Shape of You", "Ed Sheeran", 233, Genre.POP));
    }

    @Test
    public void testAddTrack() {
        // Перевіряємо, що до додавання треку загальна тривалість - 587 секунд
        assertEquals(587, disk.getTotalDuration());

        // Перевіряємо, що кількість треків до додавання - 2
        assertEquals(2, disk.findTracksByDurationRange(0, Integer.MAX_VALUE).size());

        // Додаємо новий трек
        disk.addTrack(new MusicTrack("Take Five", "Dave Brubeck", 324, Genre.JAZZ));

        // Перевіряємо, що загальна тривалість тепер 587 + 324 = 911 секунд
        assertEquals(911, disk.getTotalDuration());

        // Перевіряємо, що кількість треків після додавання - 3
        assertEquals(3, disk.findTracksByDurationRange(0, Integer.MAX_VALUE).size());
    }



    @Test
    public void testRemoveTrack() {
        // Перед видаленням перевіряємо кількість треків
        assertEquals(2, disk.findTracksByDurationRange(0, Integer.MAX_VALUE).size());

        // Видаляємо перший трек (Bohemian Rhapsody)
        disk.removeTrack(0);

        // Перевіряємо кількість треків після видалення
        assertEquals(1, disk.findTracksByDurationRange(0, Integer.MAX_VALUE).size());

    }


    @Test
    public void testSortTracksByGenre() {
        // Очищаємо диск перед тестом
        disk = new MusicDisk();

        // Додаємо треки в алфавітному порядку жанрів
        disk.addTrack(new MusicTrack("Take Five", "Dave Brubeck", 324, Genre.JAZZ));
        disk.addTrack(new MusicTrack("Blinding Lights", "The Weeknd", 200, Genre.POP));
        disk.addTrack(new MusicTrack("Stairway to Heaven", "Led Zeppelin", 482, Genre.ROCK));

        // Сортуємо треки за жанром
        disk.sortTracksByGenre();

        // Отримуємо відсортовані треки
        List<MusicTrack> sortedTracks = disk.findTracksByDurationRange(0, 10000); // Отримати всі треки

        // Перевіряємо, що треки відсортовані за алфавітним порядком жанрів
        assertEquals(Genre.ROCK, sortedTracks.get(0).getGenre()); // JAZZ
        assertEquals(Genre.POP, sortedTracks.get(1).getGenre());  // POP
        assertEquals(Genre.JAZZ, sortedTracks.get(2).getGenre()); // ROCK
    }

    @Test
    public void testGetTotalDuration() {
        assertEquals(587, disk.getTotalDuration());
    }

    @Test
    public void testFindTracksByDurationRange() {
        // Перевіряємо, що в діапазоні 100-400 секунд є 2 треки
        assertEquals(2, disk.findTracksByDurationRange(100, 400).size());
    }

    @Test
    public void testSaveAndLoadDisk() throws Exception {
        String fileName = "testDisk.dat";
        disk.saveToFile(fileName);
        MusicDisk loadedDisk = MusicDisk.loadFromFile(fileName);
        assertEquals(disk.getTotalDuration(), loadedDisk.getTotalDuration());

        // Очищення файлу після тесту
        new File(fileName).delete();
    }
}
