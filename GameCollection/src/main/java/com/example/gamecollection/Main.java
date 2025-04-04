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

    private ArrayList<Game> games = new ArrayList<>();
    private GridPane gridPane = new GridPane();

    @Override
    public void start(Stage stage) {
        listFiller();
        MenuBar menuBar = menuBar();
        HBox bottomHBox = bottomBox();

        VBox root = new VBox(menuBar, bottomHBox);
        Scene scene = new Scene(root, 900, 600);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setTitle("Game Collection Library");
        stage.setScene(scene);
        stage.show();
    }

    private HBox bottomBox(){
        VBox left = leftVBox();
        VBox right = rightBox();
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(left,right);
        hbox.setPadding(new Insets(0,0,0,20));
        HBox.setHgrow(right, Priority.ALWAYS);
        return hbox;
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
        hbox.setStyle("-fx-padding: 0 0 20 380;");

        return hbox;
    }

    private VBox createGameBox(Game game) {
        ImageView imageView = new ImageView(new Image(game.getImagePath()));
        imageView.setFitWidth(110);
        imageView.setFitHeight(150);
        //imageView.setOnMouseClicked(e -> openGameDetail(game));

        Label nameLabel = new Label(game.getName());
        VBox vBox = new VBox(5, imageView, nameLabel);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private void  createGameGrid() {
        gridPane.getChildren().clear();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        int col = 0, row = 0;
        for (Game game : games) {
            VBox gameBox = createGameBox(game);
            gameBox.setPrefWidth(100);
            gridPane.add(gameBox, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }

    private VBox rightBox(){
        HBox hBox = createSortBox(games);
        createGameGrid();
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        VBox box = new VBox(hBox,scrollPane);
        box.setPadding(new Insets(20, 0, 0, 20));
        return box;
    }

    private VBox leftVBox(){
        VBox searchBox = new VBox(10);
        searchBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("GAME LIBRARY");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField searchField = new TextField();
        searchField.setPromptText("Search for a game...");

        Button searchButton = new Button("Search");
        HBox hBox = new HBox(10,searchField,searchButton);

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
        CheckBox turnBasedCheckBox = new CheckBox("Turn-based (Sıra tabanlı)");
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

        searchBox.getChildren().addAll(titleLabel, hBox,
                platformBox, featuresBox, genreBox);

        return searchBox;
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

    private void listFiller(){
        games.add(new Game("League of Legends", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Minecraft", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png",2011));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Game 4", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 5", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 6", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 7", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 8", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 9", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 1", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 2", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 3", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 4", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 5", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 6", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 7", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 8", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Game 9", "https://upload.wikimedia.org/wikipedia/tr/thumb/7/77/League_of_Legends_logo.png/220px-League_of_Legends_logo.png",2009));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Game 10", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Game 10", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Game 10", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Counter Strike Global Offensive", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
        games.add(new Game("Game 10", "https://upload.wikimedia.org/wikipedia/en/6/6e/CSGOcoverMarch2020.jpg",2012));
    }

    public static void main(String[] args) {
        launch();
}
}