package org.example.disk;

import org.example.music.Genre;
import org.example.music.MusicTrack;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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

        // Додаємо новий трек
        disk.addTrack(new MusicTrack("Take Five", "Dave Brubeck", 324, Genre.JAZZ));

        // Перевіряємо, що загальна тривалість тепер 587 + 324 = 911 секунд
        assertEquals(911, disk.getTotalDuration());
    }

    @Test
    public void testRemoveTrack() {
        // Перед видаленням треку, перевіряємо загальну тривалість
        assertEquals(587, disk.getTotalDuration());

        // Видаляємо перший трек (Bohemian Rhapsody)
        disk.removeTrack(0);

        // Перевіряємо загальну тривалість після видалення
        assertEquals(233, disk.getTotalDuration()); // Тільки "Shape of You" залишається
    }

    @Test
    public void testSortTracksByGenre() {
        // Тест на сортировку треків за жанром
        disk.sortTracksByGenre();
        // Тут можна додати перевірки, якщо потрібно
    }

    @Test
    public void testGetTotalDuration() {
        assertEquals(587, disk.getTotalDuration());
    }

    @Test
    public void testFindTracksByDurationRange() {
        assertEquals(1, disk.findTracksByDurationRange(200, 400).size());
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
