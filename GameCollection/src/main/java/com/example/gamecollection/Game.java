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

        public Localization() {
            this.translators = new ArrayList<>();
            this.dubbingArtists = new ArrayList<>();
        }

        public Localization(String language) {
            this();
            this.language = language;
        }

        public String getLanguage() { return language; }
        public List<String> getTranslators() { return translators; }
        public List<String> getDubbingArtists() { return dubbingArtists; }
        public void setLanguage(String language) { this.language = language; }
        public void setTranslators(List<String> translators) { this.translators = translators; }
        public void setDubbingArtists(List<String> dubbingArtists) { this.dubbingArtists = dubbingArtists; }

        public void addTranslator(String translator) {
            translators.add(translator);
        }

        public void addDubbingArtist(String artist) {
            dubbingArtists.add(artist);
        }
    }

    public Game() {
        this.genres = new ArrayList<>();
        this.publishers = new ArrayList<>();
        this.platforms = new ArrayList<>();
        this.localizations = new ArrayList<>();
    }

    public Game(String name, String developer, int year, String steamAppId) {
        this();
        this.name = name;
        this.developer = developer;
        this.year = year;
        this.steamAppId = steamAppId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getSteamAppId() { return steamAppId; }
    public void setSteamAppId(String steamAppId) { this.steamAppId = steamAppId; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    public List<String> getPublishers() { return publishers; }
    public void setPublishers(List<String> publishers) { this.publishers = publishers; }

    public List<String> getPlatforms() { return platforms; }
    public void setPlatforms(List<String> platforms) { this.platforms = platforms; }

    public int getTotalPlaytimeHours() { return totalPlaytimeHours; }
    public void setTotalPlaytimeHours(int totalPlaytimeHours) { this.totalPlaytimeHours = totalPlaytimeHours; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public List<Localization> getLocalizations() { return localizations; }
    public void setLocalizations(List<Localization> localizations) { this.localizations = localizations; }

    public void addGenre(String genre) { genres.add(genre); }
    public void addPublisher(String publisher) { publishers.add(publisher); }
    public void addPlatform(String platform) { platforms.add(platform); }
    public void addLocalization(Localization localization) { localizations.add(localization); }
}