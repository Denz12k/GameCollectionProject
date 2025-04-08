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
        Game witcher3 = new Game("The Witcher 3: Wild Hunt", "CD Projekt Red", 2015, "292030");
        witcher3.addGenre("RPG");
        witcher3.addGenre("Open World");
        witcher3.addPublisher("CD Projekt");
        witcher3.addPlatform("PC");
        witcher3.addPlatform("PlayStation 4");
        witcher3.addPlatform("Xbox One");
        witcher3.setTotalPlaytimeHours(150);
        witcher3.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Witcher_3_cover_art.jpg/250px-Witcher_3_cover_art.jpg");
        games.add(witcher3);

        Game lol = new Game("League of Legends", "Riot Games", 2009, "123456");
        lol.addGenre("MOBA");
        lol.addGenre("Strategy");
        lol.addPublisher("Riot Games");
        lol.addPlatform("PC");
        lol.setTotalPlaytimeHours(500);
        lol.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/League_of_Legends_2019_vector.svg/250px-League_of_Legends_2019_vector.svg.png");
        games.add(lol);

        Game cs2 = new Game("Counter-Strike 2", "Valve", 2023, "234567");
        cs2.addGenre("FPS");
        cs2.addGenre("Shooter");
        cs2.addPublisher("Valve");
        cs2.addPlatform("PC");
        cs2.setTotalPlaytimeHours(300);
        cs2.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg");
        games.add(cs2);

        Game valorant = new Game("Valorant", "Riot Games", 2020, "345678");
        valorant.addGenre("FPS");
        valorant.addGenre("Tactical Shooter");
        valorant.addPublisher("Riot Games");
        valorant.addPlatform("PC");
        valorant.setTotalPlaytimeHours(250);
        valorant.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/220px-Valorant_logo_-_pink_color_version.svg.png");
        games.add(valorant);

        Game minecraft = new Game("Minecraft", "Mojang Studios", 2011, "456789");
        minecraft.addGenre("Sandbox");
        minecraft.addGenre("Adventure");
        minecraft.addPublisher("Xbox Game Studios");
        minecraft.addPlatform("PC");
        minecraft.addPlatform("PlayStation");
        minecraft.addPlatform("Xbox");
        minecraft.addPlatform("Nintendo Switch");
        minecraft.addPlatform("Mobile");
        minecraft.setTotalPlaytimeHours(1000);
        minecraft.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png");
        games.add(minecraft);

        Game amongUs = new Game("Among Us", "InnerSloth", 2018, "567890");
        amongUs.addGenre("Party");
        amongUs.addGenre("Social Deduction");
        amongUs.addPublisher("InnerSloth");
        amongUs.addPlatform("PC");
        amongUs.addPlatform("Mobile");
        amongUs.addPlatform("Nintendo Switch");
        amongUs.addPlatform("PlayStation");
        amongUs.addPlatform("Xbox");
        amongUs.setTotalPlaytimeHours(50);
        amongUs.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Among_Us_cover_art.jpg/250px-Among_Us_cover_art.jpg");
        games.add(amongUs);

        Game pubg = new Game("PUBG: Battlegrounds", "PUBG Corporation", 2017, "678901");
        pubg.addGenre("Battle Royale");
        pubg.addGenre("Shooter");
        pubg.addPublisher("Krafton");
        pubg.addPlatform("PC");
        pubg.addPlatform("PlayStation");
        pubg.addPlatform("Xbox");
        pubg.addPlatform("Mobile");
        pubg.setTotalPlaytimeHours(200);
        pubg.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Pubgbattlegrounds.png/250px-Pubgbattlegrounds.png");
        games.add(pubg);

        Game cyberpunk = new Game("Cyberpunk 2077", "CD Projekt Red", 2020, "789012");
        cyberpunk.addGenre("RPG");
        cyberpunk.addGenre("FPS");
        cyberpunk.addGenre("Open World");
        cyberpunk.addPublisher("CD Projekt");
        cyberpunk.addPlatform("PC");
        cyberpunk.addPlatform("PlayStation 5");
        cyberpunk.addPlatform("Xbox Series X/S");
        cyberpunk.setTotalPlaytimeHours(80);
        cyberpunk.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Cyberpunk_2077_box_art.jpg/250px-Cyberpunk_2077_box_art.jpg");
        games.add(cyberpunk);

        Game eldenRing = new Game("Elden Ring", "FromSoftware", 2022, "890123");
        eldenRing.addGenre("Action RPG");
        eldenRing.addGenre("Open World");
        eldenRing.addPublisher("Bandai Namco");
        eldenRing.addPublisher("FromSoftware");
        eldenRing.addPlatform("PC");
        eldenRing.addPlatform("PlayStation 5");
        eldenRing.addPlatform("PlayStation 4");
        eldenRing.addPlatform("Xbox Series X/S");
        eldenRing.addPlatform("Xbox One");
        eldenRing.setTotalPlaytimeHours(120);
        eldenRing.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Elden_Ring_Box_art.jpg/250px-Elden_Ring_Box_art.jpg");
        games.add(eldenRing);

        Game.Localization englishLoc = new Game.Localization("English");
        englishLoc.addTranslator("Ryan Morris");
        englishLoc.addTranslator("Emily Johnson");
        englishLoc.addDubbingArtist("Paul Bandey"); // Godrick seslendirmen
        englishLoc.addDubbingArtist("Lindsay Seidel"); // Melina seslendirmen

        Game.Localization turkishLoc = new Game.Localization("Turkish");
        turkishLoc.addTranslator("Mehmet Can Korkut");
        turkishLoc.addTranslator("Ayşe Demir");
        turkishLoc.addDubbingArtist("Sercan Gidiş"); // Godrick seslendirmen
        turkishLoc.addDubbingArtist("Zeynep Taşkın"); // Melina seslendirmen

        Game.Localization japaneseLoc = new Game.Localization("Japanese");
        japaneseLoc.addTranslator("Hiroshi Yamamoto");
        japaneseLoc.addDubbingArtist("Kōichi Yamadera"); // Godrick seslendirmen

        eldenRing.addLocalization(englishLoc);
        eldenRing.addLocalization(turkishLoc);
        eldenRing.addLocalization(japaneseLoc);

        Game witcher3x = new Game("The Witcher 3: Wild Hunt", "CD Projekt Red", 2015, "292030");
        witcher3x.addGenre("RPG");
        witcher3x.addGenre("Open World");
        witcher3x.addPublisher("CD Projekt");
        witcher3x.addPlatform("PC");
        witcher3x.addPlatform("PlayStation 4");
        witcher3x.addPlatform("Xbox One");
        witcher3x.setTotalPlaytimeHours(150);
        witcher3x.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Witcher_3_cover_art.jpg/250px-Witcher_3_cover_art.jpg");
        games.add(witcher3x);

        Game lol1 = new Game("League of Legends", "Riot Games", 2009, "123456");
        lol1.addGenre("MOBA");
        lol1.addGenre("Strategy");
        lol1.addPublisher("Riot Games");
        lol1.addPlatform("PC");
        lol1.setTotalPlaytimeHours(500);
        //lol1.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/League_of_Legends_2019_vector.svg/250px-League_of_Legends_2019_vector.svg.png");
        games.add(lol1);

        Game cs2x = new Game("Counter-Strike 2", "Valve", 2023, "234567");
        cs2x.addGenre("FPS");
        cs2x.addGenre("Shooter");
        cs2x.addPublisher("Valve");
        cs2x.addPlatform("PC");
        cs2x.setTotalPlaytimeHours(300);
        cs2x.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg");
        games.add(cs2x);

        Game valorant1 = new Game("Valorant", "Riot Games", 2020, "345678");
        valorant1.addGenre("FPS");
        valorant1.addGenre("Tactical Shooter");
        valorant1.addPublisher("Riot Games");
        valorant1.addPlatform("PC");
        valorant1.setTotalPlaytimeHours(250);
        //valorant1.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/220px-Valorant_logo_-_pink_color_version.svg.png");
        games.add(valorant1);

        Game minecraft1 = new Game("Minecraft", "Mojang Studios", 2011, "456789");
        minecraft1.addGenre("Sandbox");
        minecraft1.addGenre("Adventure");
        minecraft1.addPublisher("Xbox Game Studios");
        minecraft1.addPlatform("PC");
        minecraft1.addPlatform("PlayStation");
        minecraft1.addPlatform("Xbox");
        minecraft1.addPlatform("Nintendo Switch");
        minecraft1.addPlatform("Mobile");
        minecraft1.setTotalPlaytimeHours(1000);
        minecraft1.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Minecraft_2024_cover_art.png/250px-Minecraft_2024_cover_art.png");
        games.add(minecraft1);

        Game amongUs1 = new Game("Among Us", "InnerSloth", 2018, "567890");
        amongUs1.addGenre("Party");
        amongUs1.addGenre("Social Deduction");
        amongUs1.addPublisher("InnerSloth");
        amongUs1.addPlatform("PC");
        amongUs1.addPlatform("Mobile");
        amongUs1.addPlatform("Nintendo Switch");
        amongUs1.addPlatform("PlayStation");
        amongUs1.addPlatform("Xbox");
        amongUs1.setTotalPlaytimeHours(50);
        amongUs1.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Among_Us_cover_art.jpg/250px-Among_Us_cover_art.jpg");
        games.add(amongUs1);

        Game pubg1 = new Game("PUBG: Battlegrounds", "PUBG Corporation", 2017, "678901");
        pubg1.addGenre("Battle Royale");
        pubg1.addGenre("Shooter");
        pubg1.addPublisher("Krafton");
        pubg1.addPlatform("PC");
        pubg1.addPlatform("PlayStation");
        pubg1.addPlatform("Xbox");
        pubg1.addPlatform("Mobile");
        pubg1.setTotalPlaytimeHours(200);
        pubg1.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Pubgbattlegrounds.png/250px-Pubgbattlegrounds.png");
        games.add(pubg1);

        Game cyberpunk1 = new Game("Cyberpunk 2077", "CD Projekt Red", 2020, "789012");
        cyberpunk1.addGenre("RPG");
        cyberpunk1.addGenre("FPS");
        cyberpunk1.addGenre("Open World");
        cyberpunk1.addPublisher("CD Projekt");
        cyberpunk1.addPlatform("PC");
        cyberpunk1.addPlatform("PlayStation 5");
        cyberpunk1.addPlatform("Xbox Series X/S");
        cyberpunk1.setTotalPlaytimeHours(80);
        cyberpunk1.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Cyberpunk_2077_box_art.jpg/250px-Cyberpunk_2077_box_art.jpg");
        games.add(cyberpunk1);

        Game eldenRing1 = new Game("Elden Ring", "FromSoftware", 2022, "890123");
        eldenRing1.addGenre("Action RPG");
        eldenRing1.addGenre("Open World");
        eldenRing1.addPublisher("Bandai Namco");
        eldenRing1.addPublisher("FromSoftware");
        eldenRing1.addPlatform("PC");
        eldenRing1.addPlatform("PlayStation 5");
        eldenRing1.addPlatform("PlayStation 4");
        eldenRing1.addPlatform("Xbox Series X/S");
        eldenRing1.addPlatform("Xbox One");
        eldenRing1.setTotalPlaytimeHours(120);
        eldenRing1.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Elden_Ring_Box_art.jpg/250px-Elden_Ring_Box_art.jpg");
        games.add(eldenRing1);
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