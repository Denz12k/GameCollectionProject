package com.example.gamecollection;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String name;
    private String developer;
    private int year;
    private String steamAppId;
    private List<String> genres;
    private List<String> publishers;
    private List<String> platforms;
    private int totalPlaytimeHours;
    private String imagePath = "image.jpeg";
    private List<Localization> localizations;

    public static class Localization {
        private String language;
        private List<String> translators;
        private List<String> dubbingArtists;

        public Localization(String language) {
            this.language = language;
            this.translators = new ArrayList<>();
            this.dubbingArtists = new ArrayList<>();
        }

        public String getLanguage() { return language; }
        public List<String> getTranslators() { return translators; }
        public List<String> getDubbingArtists() { return dubbingArtists; }

        public void addTranslator(String translator) {
            translators.add(translator);
        }

        public void addDubbingArtist(String artist) {
            dubbingArtists.add(artist);
        }
    }

    public Game(String name, String developer, int year, String steamAppId) {
        this.name = name;
        this.developer = developer;
        this.year = year;
        this.steamAppId = steamAppId;
        this.genres = new ArrayList<>();
        this.publishers = new ArrayList<>();
        this.platforms = new ArrayList<>();
        this.localizations = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getDeveloper() { return developer; }
    public int getYear() { return year; }
    public String getSteamAppId() { return steamAppId; }
    public List<String> getGenres() { return genres; }
    public List<String> getPublishers() { return publishers; }
    public List<String> getPlatforms() { return platforms; }
    public int getTotalPlaytimeHours() { return totalPlaytimeHours; }
    public String getImagePath() { return imagePath; }
    public List<Localization> getLocalizations() { return localizations; }

    public void setTotalPlaytimeHours(int hours) { totalPlaytimeHours = hours; }
    public void setImagePath(String path) { imagePath = path; }

    public void addGenre(String genre) { genres.add(genre); }
    public void addPublisher(String publisher) { publishers.add(publisher); }
    public void addPlatform(String platform) { platforms.add(platform); }
    public void addLocalization(Localization localization) { localizations.add(localization); }

    @Override
    public String toString() {
        return String.format(
                "Game: %s (%d)\nDeveloper: %s\nSteam ID: %s\nGenres: %s\nPlaytime: %d hours",
                name, year, developer, steamAppId, genres, totalPlaytimeHours
        );
    }
}