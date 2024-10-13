package org.example.disk;

import org.example.music.MusicTrack;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MusicDisk implements Serializable {
    private List<MusicTrack> tracks;

    public MusicDisk() {
        this.tracks = new ArrayList<>();
    }

    public void addTrack(MusicTrack track) {
        tracks.add(track);
    }

    public void showTracks() {
        if (tracks.isEmpty()) {
            System.out.println("The disk is empty.");
        } else {
            for (int i = 0; i < tracks.size(); i++) {
                System.out.println((i + 1) + ". " + tracks.get(i));
            }
        }
    }

    public int getTotalDuration() {
        return tracks.stream().mapToInt(MusicTrack::getDuration).sum();
    }

    public void sortTracksByGenre() {
        tracks.sort(Comparator.comparing(MusicTrack::getGenre));
    }

    public List<MusicTrack> findTracksByDurationRange(int minDuration, int maxDuration) {
        return tracks.stream()
                .filter(track -> track.getDuration() >= minDuration && track.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        }
    }

    public static MusicDisk loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (MusicDisk) ois.readObject();
        }
    }

    // Доданий метод для видалення треків
    public void removeTrack(int index) {
        if (index >= 0 && index < tracks.size()) {
            tracks.remove(index);
            System.out.println("Track removed.");
        } else {
            System.out.println("Invalid track index.");
        }
    }
}