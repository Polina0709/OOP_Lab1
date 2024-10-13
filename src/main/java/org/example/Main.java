package org.example;

import org.example.disk.MusicDisk;
import org.example.music.MusicTrack;
import org.example.utils.MusicDatabase;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "musicDisk.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicDisk disk = null;

        // Завантаження або створення файлу
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                disk = MusicDisk.loadFromFile(FILE_NAME);
                System.out.println("Loaded disk from file.");
            } catch (Exception e) {
                System.out.println("Failed to load disk from file: " + e.getMessage());
                disk = new MusicDisk();
            }
        } else {
            // Якщо файл не існує, створюємо порожній файл
            try {
                file.createNewFile();
                System.out.println("Created new file: " + FILE_NAME);
                disk = new MusicDisk();
                saveDiskToFile(disk); // Зберігаємо порожній диск у новий файл
            } catch (Exception e) {
                System.out.println("Failed to create new file: " + e.getMessage());
            }
        }

        // Основне меню
        boolean running = true;
        while (running) {
            System.out.println("\nMENU:");
            System.out.println("1. Show all available tracks");
            System.out.println("2. Add tracks to disk");
            System.out.println("3. Show tracks on disk");
            System.out.println("4. Sort tracks by genre");
            System.out.println("5. Find tracks by duration range");
            System.out.println("6. Calculate total duration of all tracks");
            System.out.println("7. Save disk to file");
            System.out.println("8. Load disk from file");
            System.out.println("9. Remove a track from the disk");
            System.out.println("10. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // споживаємо символ нової строки після введення числа

            switch (choice) {
                case 1:
                    showAvailableTracks();
                    break;
                case 2:
                    addTracksToDisk(disk, scanner);
                    break;
                case 3:
                    showTracksOnDisk(disk);
                    break;
                case 4:
                    sortTracksByGenre(disk);
                    break;
                case 5:
                    findTracksByDurationRange(disk, scanner);
                    break;
                case 6:
                    calculateTotalDuration(disk);
                    break;
                case 7:
                    saveDiskToFile(disk);
                    break;
                case 8:
                    disk = loadDiskFromFile();
                    break;
                case 9:
                    removeTrackFromDisk(disk, scanner);
                    break;
                case 10:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Показати всі доступні треки
    private static void showAvailableTracks() {
        List<MusicTrack> availableTracks = MusicDatabase.getAvailableTracks();
        System.out.println("\nAvailable Tracks:");
        int index = 1;
        for (MusicTrack track : availableTracks) {
            System.out.println(index++ + ". " + track);
        }
    }

    // Додати треки на диск
    private static void addTracksToDisk(MusicDisk disk, Scanner scanner) {
        showAvailableTracks();
        System.out.println("Select tracks to add to disk (by numbers, separated by space):");
        String[] trackNumbers = scanner.nextLine().split(" ");
        List<MusicTrack> availableTracks = MusicDatabase.getAvailableTracks();
        for (String num : trackNumbers) {
            int trackIndex = Integer.parseInt(num) - 1;
            if (trackIndex >= 0 && trackIndex < availableTracks.size()) {
                disk.addTrack(availableTracks.get(trackIndex));
                System.out.println("Track added: " + availableTracks.get(trackIndex));
            } else {
                System.out.println("Invalid track number: " + num);
            }
        }
    }

    // Показати треки на диску
    private static void showTracksOnDisk(MusicDisk disk) {
        System.out.println("\nTracks on disk:");
        disk.showTracks();
    }

    // Сортувати треки за жанром
    private static void sortTracksByGenre(MusicDisk disk) {
        System.out.println("\nSorting tracks by genre...");
        disk.sortTracksByGenre();
        disk.showTracks();
    }

    // Шукати треки за тривалістю
    private static void findTracksByDurationRange(MusicDisk disk, Scanner scanner) {
        System.out.println("Enter minimum and maximum track duration (in seconds):");
        int minDuration = scanner.nextInt();
        int maxDuration = scanner.nextInt();
        System.out.println("Tracks found:");
        List<MusicTrack> foundTracks = disk.findTracksByDurationRange(minDuration, maxDuration);
        foundTracks.forEach(System.out::println);
    }

    // Підрахувати загальну тривалість усіх треків
    private static void calculateTotalDuration(MusicDisk disk) {
        int totalDuration = disk.getTotalDuration();
        System.out.println("\nTotal duration of all tracks on the disk: " + totalDuration + " seconds");
    }

    // Зберегти диск у файл
    private static void saveDiskToFile(MusicDisk disk) {
        try {
            disk.saveToFile(FILE_NAME);
            System.out.println("Disk saved to file.");
        } catch (Exception e) {
            System.out.println("Failed to save disk to file: " + e.getMessage());
        }
    }

    // Завантажити диск із файлу
    private static MusicDisk loadDiskFromFile() {
        try {
            MusicDisk disk = MusicDisk.loadFromFile(FILE_NAME);
            System.out.println("Disk loaded from file.");
            return disk;
        } catch (Exception e) {
            System.out.println("Failed to load disk from file: " + e.getMessage());
            return new MusicDisk();
        }
    }

    // Видалити трек з диска
    private static void removeTrackFromDisk(MusicDisk disk, Scanner scanner) {
        System.out.println("Current tracks on disk:");
        disk.showTracks();
        System.out.print("Select track number to remove: ");
        int trackNumber = scanner.nextInt();
        disk.removeTrack(trackNumber - 1);
    }
}
