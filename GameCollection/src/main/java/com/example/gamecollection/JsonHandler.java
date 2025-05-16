package com.example.gamecollection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static ArrayList<Game> importGames(File file, ArrayList<Game> currentGames) {
        if (currentGames == null) {
            currentGames = new ArrayList<>();
        }
        if (file == null || !file.exists()) {
            return currentGames;
        }
        try {
            List<Game> imported =
                    objectMapper.readValue(file, new TypeReference<List<Game>>() {});
            currentGames.addAll(imported);
        } catch (IOException e) {
            System.err.println("JSON import error: " + e.getMessage());
        }

        return currentGames;
    }
    public static void exportGames(ArrayList<Game> games, File file) {
        if (games == null || file == null) {
            return;
        }
        try {
            if (file.isDirectory()) {
                file = new File(file, "games.json");
            }
            ArrayList<Game> toWrite = new ArrayList<>(games);
            if (file.exists()
                    && file.isFile()
                    && file.getName().toLowerCase().endsWith(".json")) {
                try {
                    List<Game> existing =
                            objectMapper.readValue(file, new TypeReference<List<Game>>() {});
                    toWrite.addAll(0, existing);
                } catch (IOException ignored) {
                }
            }
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(file, toWrite);
        } catch (IOException e) {
            System.err.println("JSON export error: " + e.getMessage());
        }
    }
}