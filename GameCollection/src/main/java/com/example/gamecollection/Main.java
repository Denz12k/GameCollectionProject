package com.example.gamecollection;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Comparator;

public class Main extends Application {

    private final ArrayList<Game> games = new ArrayList<>();
    private final GridPane gridPane = new GridPane();

    @Override
    public void start(Stage stage) {
        listFiller();
        MenuBar menuBar = menuBar();
        HBox bottomHBox = bottomBox();

        VBox root = new VBox(menuBar, bottomHBox);
        root.setStyle("-fx-background-color: #f9f9f9;");

        Scene scene = new Scene(root, 1200, 700);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setTitle("Game Collection Library");
        stage.setScene(scene);
        stage.show();
    }

    public HBox createSortBox(ArrayList<Game> games) {
        ComboBox<String> sortOptions = new ComboBox<>();
        sortOptions.getItems().addAll("Sort by Name", "Sort by Release Year");
        sortOptions.setValue("Sort by Name");

        Button sortButton = new Button("Sort");

        sortButton.setOnAction(e -> {
            String selected = sortOptions.getValue();
            if (selected.equals("Sort by Name")) {
                games.sort(Comparator.comparing(Game::getName));
            } else if (selected.equals("Sort by Release Year")) {
                games.sort(Comparator.comparingInt(Game::getYear));
            }
            createGameGrid();
        });

        HBox hbox = new HBox(10, sortOptions, sortButton);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(10));

        return hbox;
    }

    private void listFiller() {
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png", 2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png", 2011));
        games.add(new Game("Counter Strike GO", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg", 2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
    }

    private HBox bottomBox() {
        VBox left = leftVBox();
        VBox right = rightBox();
        HBox hbox = new HBox(25, left, right);
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    private VBox rightBox() {
        HBox sortBox = createSortBox(games);
        createGameGrid();
        sortBox.setStyle("-fx-background-color: #f0f0f0;");

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #f0f0f0;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox box = new VBox(10, sortBox, scrollPane);
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }

    private MenuBar menuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem importJson = new MenuItem("Import JSON");
        MenuItem exportJson = new MenuItem("Export JSON");
        fileMenu.getItems().addAll(newGame, importJson, exportJson);

        Menu editMenu = new Menu("Edit");
        MenuItem editGame = new MenuItem("Edit Game");
        MenuItem deleteGame = new MenuItem("Delete Game");
        editMenu.getItems().addAll(editGame, deleteGame);

        Menu helpMenu = new Menu("Help");
        MenuItem userManual = new MenuItem("User Manual");
        MenuItem about = new MenuItem("About");
        helpMenu.getItems().addAll(userManual, about);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        return menuBar;
    }

    private VBox leftVBox() {
        VBox searchBox = new VBox(20);
        searchBox.setPadding(new Insets(10));
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPrefWidth(250);

        Label titleLabel = new Label("🎮 GAME LIBRARY");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        titleLabel.setPrefWidth(250);

        TextField searchField = new TextField();
        searchField.setPromptText("Search for a game...");

        Button searchButton = new Button("Search");
        searchButton.setMaxWidth(Double.MAX_VALUE);

        VBox searchArea = new VBox(10, searchField, searchButton);

        VBox platformBox = new VBox(5);
        platformBox.setAlignment(Pos.TOP_LEFT);
        Label platformLabel = new Label("Platforms");
        platformLabel.setStyle("-fx-font-weight: bold;");
        CheckBox pcCheckBox = new CheckBox("PC");
        CheckBox psCheckBox = new CheckBox("PS");
        CheckBox xboxCheckBox = new CheckBox("Xbox");
        platformBox.getChildren().addAll(platformLabel, pcCheckBox, psCheckBox, xboxCheckBox);

        VBox featuresBox = new VBox(5);
        featuresBox.setAlignment(Pos.TOP_LEFT);
        Label featuresLabel = new Label("Game Features");
        featuresLabel.setStyle("-fx-font-weight: bold;");
        CheckBox multiplayerCheckBox = new CheckBox("Multiplayer");
        CheckBox singlePlayerCheckBox = new CheckBox("Single-player");
        CheckBox onlineCheckBox = new CheckBox("Online");
        CheckBox coopCheckBox = new CheckBox("Co-op");
        CheckBox openWorldCheckBox = new CheckBox("Open-world");
        featuresBox.getChildren().addAll(featuresLabel, multiplayerCheckBox, singlePlayerCheckBox,
                onlineCheckBox, coopCheckBox, openWorldCheckBox);

        VBox genreBox = new VBox(5);
        genreBox.setAlignment(Pos.TOP_LEFT);
        Label genreLabel = new Label("Game Genres");
        genreLabel.setStyle("-fx-font-weight: bold;");
        CheckBox rpgCheckBox = new CheckBox("RPG (Role-Playing Game)");
        CheckBox turnBasedCheckBox = new CheckBox("Turn-based");
        CheckBox actionCheckBox = new CheckBox("Action");
        CheckBox adventureCheckBox = new CheckBox("Adventure");
        CheckBox strategyCheckBox = new CheckBox("Strategy");
        CheckBox puzzleCheckBox = new CheckBox("Puzzle");
        CheckBox simulationCheckBox = new CheckBox("Simulation");
        CheckBox fpsCheckBox = new CheckBox("FPS (First Person Shooter)");
        CheckBox racingCheckBox = new CheckBox("Racing");
        CheckBox fightingCheckBox = new CheckBox("Fighting");
        genreBox.getChildren().addAll(genreLabel, rpgCheckBox, turnBasedCheckBox,
                actionCheckBox, adventureCheckBox, strategyCheckBox, puzzleCheckBox,
                simulationCheckBox, fpsCheckBox, racingCheckBox, fightingCheckBox);

        searchBox.getChildren().addAll(titleLabel, searchArea,
                platformBox, featuresBox, genreBox);
        return searchBox;
    }

    private void createGameGrid() {
        gridPane.getChildren().clear();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));

        int col = 0, row = 0;
        for (Game game : games) {
            VBox gameBox = createGameBox(game);
            gameBox.setPrefWidth(150);
            gridPane.add(gameBox, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }

    private VBox createGameBox(Game game) {
        ImageView imageView = new ImageView(new Image(game.getImagePath(), true));
        imageView.setFitWidth(130);
        imageView.setFitHeight(180);
        imageView.setOnMouseClicked(e -> openGameDetail(game));

        Label nameLabel = new Label(game.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        nameLabel.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10, imageView, nameLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);"
        );
        vBox.setPrefWidth(150);

        return vBox;
    }

    private void openGameDetail(Game game) {
        Stage detailStage = new Stage();

        ImageView imageView = new ImageView(new Image(game.getImagePath()));
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);

        Label nameLabel = new Label("Game: " + game.getName());
        Label yearLabel = new Label("Release Year: " + game.getYear());

        VBox vBox = new VBox(20, imageView, nameLabel, yearLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        Scene scene = new Scene(vBox, 350, 450);
        detailStage.setTitle(game.getName());
        detailStage.setScene(scene);
        detailStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}