package com.example.gamecollection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static ArrayList<Game> importGames(File file) {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<ArrayList<Game>>() {});
        } catch (IOException e) {
            System.err.println("JSON import error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void exportGames(ArrayList<Game> games,File file) {
        try {
            objectMapper.writeValue(file, games);
        } catch (IOException e) {
            System.err.println("JSON export error: " + e.getMessage());
        }
    }
}