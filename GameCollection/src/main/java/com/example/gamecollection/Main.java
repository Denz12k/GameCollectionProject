package com.example.gamecollection;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Application {

    private ArrayList<Game> games = new ArrayList<>();
    private List<Game> filteredGames = new ArrayList<>();
    private GridPane gridPane = new GridPane();
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        listFiller();
        filteredGames = games;
        MenuBar menuBar = menuBar();
        HBox bottomHBox = bottomBox();

        VBox root = new VBox(menuBar, bottomHBox);
        root.setStyle("-fx-background-color: #f9f9f9;");

        Scene scene = new Scene(root, 1130, 680);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setTitle("Game Collection Library");
        stage.setScene(scene);
        stage.show();
    }

    public HBox createSortBox() {
        ComboBox<String> sortOptions = new ComboBox<>();
        sortOptions.getItems().addAll("Sort by Name (A-Z)",
                "Sort by Name (Z-A)",
                "Sort by Release Year (Newest)",
                "Sort by Release Year (Oldest)",
                "Sort by Playtime (Longest)",
                "Sort by Playtime (Shortest)",
                "Sort by Developer",
                "Sort by Platform Count",
                "Sort by Genre Count");
        sortOptions.setValue("Sort by Name (A-Z)");

        Button sortButton = new Button("Sort");

        sortButton.setOnAction(e -> {
            String selected = sortOptions.getValue();
            List<Game> recentGames = filteredGames;

            switch (selected) {
                case "Sort by Name (A-Z)" -> recentGames.sort(Comparator.comparing(Game::getName));
                case "Sort by Name (Z-A)" -> recentGames.sort(Comparator.comparing(Game::getName).reversed());
                case "Sort by Release Year (Newest)" -> recentGames.sort(Comparator.comparingInt(Game::getYear).reversed());
                case "Sort by Release Year (Oldest)" -> recentGames.sort(Comparator.comparingInt(Game::getYear));
                case "Sort by Playtime (Longest)" ->
                        recentGames.sort(Comparator.comparingInt(Game::getTotalPlaytimeHours).reversed());
                case "Sort by Playtime (Shortest)" -> recentGames.sort(Comparator.comparingInt(Game::getTotalPlaytimeHours));
                case "Sort by Developer" -> recentGames.sort(Comparator.comparing(Game::getDeveloper));
                case "Sort by Platform Count" -> recentGames.sort(Comparator.<Game>comparingInt(g -> g.getPlatforms().size()).reversed());
                case "Sort by Genre Count" -> recentGames.sort(Comparator.<Game>comparingInt(g -> g.getGenres().size()).reversed());
            }
            createGameGrid(recentGames);
        });

        Label infoLabel = new Label("🔍 Click on a game cover to view its details.");
        infoLabel.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #2a2a2a;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 5 10 10 120;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox hbox = new HBox(10, infoLabel, spacer, sortOptions, sortButton);
        hbox.setPadding(new Insets(10));

        return hbox;
    }

    private void listFiller() {
        Game witcher3 = new Game("The Witcher 3: Wild Hunt", "CD Projekt Red", 2015, "292030");
        witcher3.addGenre("RPG");
        witcher3.addGenre("Open World");
        witcher3.addPublisher("CD Projekt");
        witcher3.addGenre("Single-player");
        witcher3.addPlatform("PC");
        witcher3.addPlatform("PlayStation 4");
        witcher3.addPlatform("Xbox One");
        witcher3.setTotalPlaytimeHours(150);
        witcher3.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/Witcher_3_cover_art.jpg/250px-Witcher_3_cover_art.jpg");
        games.add(witcher3);

        Game lol = new Game("League of Legends", "Riot Games", 2009, "123456");
        lol.addGenre("MOBA");
        lol.addGenre("MultiPlayer");
        lol.addGenre("Co-op");
        lol.addGenre("Online");
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
        cs2.addGenre("Co-op");
        cs2.addGenre("Online");
        cs2.addPlatform("PC");
        cs2.addGenre("MultiPlayer");
        cs2.setTotalPlaytimeHours(300);
        cs2.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg");
        games.add(cs2);

        Game valorant = new Game("Valorant", "Riot Games", 2020, "345678");
        valorant.addGenre("FPS");
        valorant.addGenre("MultiPlayer");
        valorant.addGenre("Strategy");
        valorant.addGenre("Tactical Shooter");
        valorant.addPublisher("Riot Games");
        valorant.addPlatform("PC");
        valorant.addGenre("Online");
        valorant.setTotalPlaytimeHours(250);
        valorant.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-pink_color_version.svg/220px-Valorant_logo-_pink_color_version.svg.png");
        games.add(valorant);

        Game minecraft = new Game("Minecraft", "Mojang Studios", 2011, "456789");
        minecraft.addGenre("Sandbox");
        minecraft.addGenre("Adventure");
        minecraft.addGenre("Single-player");
        minecraft.addPublisher("Xbox Game Studios");
        minecraft.addPlatform("PC");
        minecraft.addPlatform("PlayStation");
        minecraft.addGenre("Online");
        minecraft.addGenre("Simulation");
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
        amongUs.addGenre("Online");
        amongUs.addPlatform("Xbox");
        amongUs.addGenre("MultiPlayer");
        amongUs.setTotalPlaytimeHours(50);
        amongUs.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Among_Us_cover_art.jpg/250px-Among_Us_cover_art.jpg");
        games.add(amongUs);

        Game pubg = new Game("PUBG: Battlegrounds", "PUBG Corporation", 2017, "678901");
        pubg.addGenre("Battle Royale");
        pubg.addGenre("Shooter");
        pubg.addPublisher("Krafton");
        pubg.addPlatform("PC");
        pubg.addPlatform("PlayStation");
        pubg.addGenre("Online");
        pubg.addPlatform("Xbox");
        pubg.addPlatform("Mobile");
        pubg.addGenre("MultiPlayer");
        pubg.setTotalPlaytimeHours(200);
        pubg.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/9/9f/Pubgbattlegrounds.png/250px-Pubgbattlegrounds.png");
        games.add(pubg);

        Game cyberpunk = new Game("Cyberpunk 2077", "CD Projekt Red", 2020, "789012");
        cyberpunk.addGenre("RPG");
        cyberpunk.addGenre("Action");
        cyberpunk.addGenre("Single-player");
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
        eldenRing.addGenre("Single-player");
        eldenRing.addPlatform("PlayStation 5");
        eldenRing.addGenre("Action");
        eldenRing.addPlatform("PlayStation 4");
        eldenRing.addPlatform("Xbox Series X/S");
        eldenRing.addPlatform("Xbox One");
        eldenRing.setTotalPlaytimeHours(120);
        eldenRing.setImagePath("https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Elden_Ring_Box_art.jpg/250px-Elden_Ring_Box_art.jpg");
        games.add(eldenRing);

        Game.Localization englishLoc = new Game.Localization("English");
        englishLoc.addTranslator("Ryan Morris");
        englishLoc.addTranslator("Emily Johnson");
        englishLoc.addDubbingArtist("Paul Bandey");
        englishLoc.addDubbingArtist("Lindsay Seidel");

        Game.Localization turkishLoc = new Game.Localization("Turkish");
        turkishLoc.addTranslator("Mehmet Can Korkut");
        turkishLoc.addTranslator("Ayşe Demir");
        turkishLoc.addDubbingArtist("Sercan Gidiş");
        turkishLoc.addDubbingArtist("Zeynep Taşkın");

        Game.Localization japaneseLoc = new Game.Localization("Japanese");
        japaneseLoc.addTranslator("Hiroshi Yamamoto");
        japaneseLoc.addDubbingArtist("Kōichi Yamadera");

        eldenRing.addLocalization(englishLoc);
        eldenRing.addLocalization(turkishLoc);
        eldenRing.addLocalization(japaneseLoc);

        Game granTurismo = new Game("Gran Turismo 7", "Polyphony Digital", 2022, "200001");
        granTurismo.addGenre("Racing");
        granTurismo.addGenre("Simulation");
        granTurismo.addGenre("Single-player");
        granTurismo.addPublisher("Sony Interactive Entertainment");
        granTurismo.addPlatform("PlayStation 5");
        granTurismo.setTotalPlaytimeHours(60);
        granTurismo.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Gran_Turismo_7_logo.svg/500px-Gran_Turismo_7_logo.svg.png");
        games.add(granTurismo);

        Game civilizationVI = new Game("Civilization VI", "Firaxis Games", 2016, "200002");
        civilizationVI.addGenre("Strategy");
        civilizationVI.addGenre("Turn-based");
        civilizationVI.addGenre("Simulation");
        civilizationVI.addGenre("Single-player");
        civilizationVI.addPublisher("2K Games");
        civilizationVI.addPlatform("PC");
        civilizationVI.addPlatform("Nintendo Switch");
        civilizationVI.setTotalPlaytimeHours(200);
        civilizationVI.setImagePath("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/289070/header.jpg?t=1740607040");
        games.add(civilizationVI);

        Game streetFighter6 = new Game("Street Fighter 6", "Capcom", 2023, "200003");
        streetFighter6.addGenre("Fighting");
        streetFighter6.addGenre("Multiplayer");
        streetFighter6.addGenre("Online");
        streetFighter6.addPublisher("Capcom");
        streetFighter6.addPlatform("PC");
        streetFighter6.addPlatform("PlayStation 5");
        streetFighter6.setTotalPlaytimeHours(40);
        streetFighter6.setImagePath("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1364780/header.jpg?t=1745814264");
        games.add(streetFighter6);

        Game portal2 = new Game("Portal 2", "Valve", 2011, "200004");
        portal2.addGenre("Puzzle");
        portal2.addGenre("Co-op");
        portal2.addGenre("Single-player");
        portal2.addPublisher("Valve");
        portal2.addPlatform("PC");
        portal2.addPlatform("Xbox 360");
        portal2.setTotalPlaytimeHours(12);
        portal2.setImagePath("https://upload.wikimedia.org/wikipedia/en/f/f9/Portal2cover.jpg");
        games.add(portal2);

        Game xcom2 = new Game("XCOM 2", "Firaxis Games", 2016, "200005");
        xcom2.addGenre("Strategy");
        xcom2.addGenre("Turn-based");
        xcom2.addGenre("Single-player");
        xcom2.addPublisher("2K Games");
        xcom2.addPlatform("PC");
        xcom2.setTotalPlaytimeHours(60);
        xcom2.setImagePath("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/268500/header.jpg?t=1646157374");
        games.add(xcom2);

        Game theSims4 = new Game("The Sims 4", "Maxis", 2014, "200006");
        theSims4.addGenre("Simulation");
        theSims4.addGenre("Single-player");
        theSims4.addPublisher("Electronic Arts");
        theSims4.addPlatform("PC");
        theSims4.addPlatform("PlayStation 4");
        theSims4.setTotalPlaytimeHours(100);
        theSims4.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Sims_4_logo.svg/500px-Sims_4_logo.svg.png");
        games.add(theSims4);

        Game tekken8 = new Game("Tekken 8", "Bandai Namco", 2024, "200007");
        tekken8.addGenre("Fighting");
        tekken8.addGenre("Multiplayer");
        tekken8.addGenre("Online");
        tekken8.addPublisher("Bandai Namco");
        tekken8.addPlatform("PC");
        tekken8.addPlatform("PlayStation 5");
        tekken8.setTotalPlaytimeHours(35);
        tekken8.setImagePath("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1778820/472b3a735c663dd75b009dcd1d3c861958302f72/header.jpg?t=1743744777");
        games.add(tekken8);

        Game assettoCorsa = new Game("Assetto Corsa Competizione", "Kunos Simulazioni", 2019, "890124");
        assettoCorsa.addGenre("Racing");
        assettoCorsa.addGenre("Simulation");
        assettoCorsa.addGenre("Multiplayer");
        assettoCorsa.addPlatform("PC");
        assettoCorsa.addPlatform("PlayStation 5");
        assettoCorsa.addPlatform("Xbox Series X/S");
        assettoCorsa.setTotalPlaytimeHours(90);
        assettoCorsa.setImagePath("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/244210/header.jpg?t=1730473196");
        games.add(assettoCorsa);

        Game.Localization enLocAC = new Game.Localization("English");
        enLocAC.addTranslator("Sarah Bennett");
        enLocAC.addDubbingArtist("James Carter");

        Game.Localization deLocAC = new Game.Localization("German");
        deLocAC.addTranslator("Lukas Schneider");
        deLocAC.addDubbingArtist("Anna Klein");

        Game.Localization trLocAC = new Game.Localization("Turkish");
        trLocAC.addTranslator("Emir Yılmaz");
        trLocAC.addDubbingArtist("Burak Aydın");

        assettoCorsa.addLocalization(enLocAC);
        assettoCorsa.addLocalization(deLocAC);
        assettoCorsa.addLocalization(trLocAC);

        Game fireEmblem = new Game("Fire Emblem: Three Houses", "Intelligent Systems", 2019, "890125");
        fireEmblem.addGenre("Strategy");
        fireEmblem.addGenre("Turn-based");
        fireEmblem.addGenre("Tactical RPG");
        fireEmblem.addPlatform("Nintendo Switch");
        fireEmblem.addPublisher("Nintendo");
        fireEmblem.setTotalPlaytimeHours(75);
        games.add(fireEmblem);

        Game.Localization enLocFE = new Game.Localization("English");
        enLocFE.addTranslator("Michael Thomas");
        enLocFE.addDubbingArtist("Erica Mendez");

        Game.Localization jpLocFE = new Game.Localization("Japanese");
        jpLocFE.addTranslator("Takeshi Nakamura");
        jpLocFE.addDubbingArtist("Saori Hayami");

        fireEmblem.addLocalization(enLocFE);
        fireEmblem.addLocalization(jpLocFE);

        Game tetrisEffect = new Game("Tetris Effect: Connected", "Monstars Inc.", 2020, "890126");
        tetrisEffect.addGenre("Puzzle");
        tetrisEffect.addGenre("Music");
        tetrisEffect.addGenre("Single-player");
        tetrisEffect.addPlatform("PC");
        tetrisEffect.addPlatform("PlayStation 4");
        tetrisEffect.addPlatform("Xbox Series X/S");
        tetrisEffect.setTotalPlaytimeHours(20);
        tetrisEffect.setImagePath("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/1003590/11d98bbb08ca8e16d015ae4a40b60920ae8dbe36/header.jpg?t=1741715084");
        games.add(tetrisEffect);

        Game.Localization enLocTE = new Game.Localization("English");
        enLocTE.addTranslator("John Harper");
        enLocTE.addDubbingArtist("Kira Buckland");

        Game.Localization trLocTE = new Game.Localization("Turkish");
        trLocTE.addDubbingArtist("Meltem Arslan");

        tetrisEffect.addLocalization(enLocTE);
        tetrisEffect.addLocalization(trLocTE);
    }

    private HBox bottomBox() {
        VBox left = leftVBox();
        VBox right = rightBox();
        HBox hbox = new HBox(25, left, right);
        hbox.setPadding(new Insets(10));
        return hbox;
    }

    private VBox rightBox() {
        HBox sortBox = createSortBox();
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
        newGame.setOnAction(e -> {

            Dialog<Game> dialog = new Dialog<>();
            dialog.setTitle("Add New Game");
            dialog.setHeaderText("Fill in the fields and press Add");

            ButtonType addBtnType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addBtnType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nameField       = new TextField();
            TextField devField        = new TextField();
            TextField yearField       = new TextField();
            TextField steamIdField    = new TextField();
            TextField playtimeField   = new TextField();
            TextField imagePathField  = new TextField();
            TextField genresField     = new TextField();
            TextField publishersField = new TextField();
            TextField platformsField  = new TextField();

            nameField.setPromptText("Name");
            devField.setPromptText("Developer");
            yearField.setPromptText("Year (e.g. 2024)");
            steamIdField.setPromptText("Steam App ID");
            playtimeField.setPromptText("Playtime hours");
            imagePathField.setPromptText("Image URL / path");
            genresField.setPromptText("Genres (comma-sep.)");
            publishersField.setPromptText("Publishers (comma-sep.)");
            platformsField.setPromptText("Platforms (comma-sep.)");

            int r = 0;
            grid.addRow(r++, new Label("Name:"),       nameField);
            grid.addRow(r++, new Label("Developer:"),  devField);
            grid.addRow(r++, new Label("Year:"),       yearField);
            grid.addRow(r++, new Label("Steam ID:"),   steamIdField);
            grid.addRow(r++, new Label("Playtime:"),   playtimeField);
            grid.addRow(r++, new Label("Image Path:"), imagePathField);
            grid.addRow(r++, new Label("Genres:"),     genresField);
            grid.addRow(r++, new Label("Publishers:"), publishersField);
            grid.addRow(r++, new Label("Platforms:"),  platformsField);

            dialog.getDialogPane().setContent(grid);

            Button addButton = (Button) dialog.getDialogPane().lookupButton(addBtnType);
            addButton.setDisable(true);

            ChangeListener<String> validator = (obs, oldVal, newVal) ->
                    addButton.setDisable(nameField.getText().trim().isEmpty() ||
                            devField.getText().trim().isEmpty());

            nameField.textProperty().addListener(validator);
            devField.textProperty().addListener(validator);

            dialog.setResultConverter(btn -> {
                if (btn == addBtnType) {
                    try {
                        int year = Integer.parseInt(yearField.getText().trim());

                        Game g = new Game(
                                nameField.getText().trim(),
                                devField.getText().trim(),
                                year,
                                steamIdField.getText().trim()
                        );

                        parseCsv(genresField.getText()).forEach(g::addGenre);
                        parseCsv(publishersField.getText()).forEach(g::addPublisher);
                        parseCsv(platformsField.getText()).forEach(g::addPlatform);

                        if (!playtimeField.getText().trim().isEmpty())
                            g.setTotalPlaytimeHours(Integer.parseInt(playtimeField.getText().trim()));

                        if (!imagePathField.getText().trim().isEmpty())
                            g.setImagePath(imagePathField.getText().trim());

                        return g;

                    } catch (NumberFormatException exNum) {
                        new Alert(Alert.AlertType.ERROR,
                                "Year and Playtime must be numeric!").showAndWait();
                    }
                }
                return null;
            });

            dialog.showAndWait().ifPresent(game -> {
                games.add(game);
                filteredGames = games;
                createGameGrid();
            });
        });
        MenuItem importJson = new MenuItem("Import JSON");
        importJson.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file","*json"));
            File file = fc.showOpenDialog(stage);
            if (file != null) {
                games = JsonHandler.importGames(file);
                createGameGrid(games);
                if (games.isEmpty()) {
                    showAlert("Error",null,"JSON file is null or invalid.");
                } else {
                    showAlert("Successful",null,"Games were imported successfully.");
                }
            }
            filteredGames= games;
        });
        MenuItem exportJson = new MenuItem("Export JSON");
        exportJson.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Export Folder");

            File selectedDirectory = directoryChooser.showDialog(stage);

            if (selectedDirectory != null) {
                File exportFile = new File(selectedDirectory, "exportedGames.json");
                JsonHandler.exportGames(games, exportFile);
                showAlert("Successful", null, "Games were exported successfully.");
            }
        });
        fileMenu.getItems().addAll(newGame, importJson, exportJson);

        Menu helpMenu = new Menu("Help");
        MenuItem userManual = new MenuItem("User Manual");
        MenuItem about = new MenuItem("About");

        about.setOnAction(e -> {
            Stage aboutStage = new Stage();
            aboutStage.setTitle("About Game Collection");

            Label appTitle = new Label("🎮 Game Collection Library");
            appTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));

            Label version = new Label("Version 1.0");
            version.setFont(Font.font("Arial", 14));

            Label developer = new Label("Developed by:\n-Deniz Karaman\n-Emre Taşkın\n-Emin Yılmaz\n-Bekir Can Türkmen");
            developer.setFont(Font.font("Arial", 14));

            Label description = new Label(
                    "This JavaFX application allows users to manage their game collection.\n" +
                            "You can add, edit, search, filter, and sort games, and export your library as a JSON file.\n" +
                            "It supports multiple platforms, genres, and even localization info for games."
            );
            description.setFont(Font.font("Arial", 13));
            description.setWrapText(true);

            Label contact = new Label("📧 Contact: \n          denizkaramankarakartal@hotmail.com\n          emretaskin04@gmail.com\n" +
                    "          emnylmz055@gmail.com\n          bekircanturkmenn@gmail.com");
            contact.setFont(Font.font("Arial", 13));

            VBox layout = new VBox(10, appTitle, version, developer, description, contact);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));
            layout.setPrefWidth(400);

            Scene scene = new Scene(layout);
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(scene);
            aboutStage.showAndWait();
        });
        userManual.setOnAction(e -> {
            Stage manualStage = new Stage();
            manualStage.setTitle("📘 Game Collection - User Manual");

            VBox contentBox = new VBox(20);
            contentBox.setPadding(new Insets(20));
            contentBox.setStyle("-fx-background-color: #f9f9f9;");
            contentBox.setAlignment(Pos.TOP_LEFT);

            Label title = new Label("📘 Game Collection Library - User Manual");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 22));
            title.setTextFill(Color.DARKBLUE);

            TitledPane overview = createSection("🗂 1. Overview", """
        This application allows users to manage a personal game collection.\s
        You can add, edit, delete, search, sort, and filter your game list.
       \s""");

            TitledPane addingGame = createSection("➕ 2. Adding a Game", """
        Go to File → New Game and fill in the game details.\s
        Required fields: Name, Developer, Year, Steam ID.
        Optional: Playtime, Genres, Platforms, Publishers, Image URL.
        \s""");

            TitledPane importExport = createSection("📤 3. Importing & Exporting", """
        Import or export your game collection as JSON files.\s
        Use File → Import JSON or File → Export JSON.
        \s""");

            TitledPane searchFilter = createSection("🔍 4. Searching & Filtering", """
        Use the search bar to find games by name, developer, or year.\s
        Use platform and genre checkboxes to refine results.
        \s""");

            TitledPane sorting = createSection("🔃 5. Sorting Games", """
        Sort your game list using the combo box:\s
        By name, year, developer, playtime, genre count, etc.
       \s""");

            TitledPane details = createSection("📝 6. Viewing & Editing Details", """
        Click a game's image to view full details.\s
        From there, you can edit or delete the selected game.
        \s""");

            Hyperlink pdfLink = new Hyperlink("📄 Click here to open the full PDF Manual");
            pdfLink.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            pdfLink.setStyle("-fx-text-fill: #0077cc; -fx-underline: true;");
            pdfLink.setOnAction(ev -> {
                try {
                    File pdf = new File(getClass().getResource("/Game_Collection_Visual_User_Manual_Complete__.pdf").toURI());
                    if (pdf.exists()) {
                        getHostServices().showDocument(pdf.toURI().toString());
                    } else {
                        showAlert("File Not Found", null, "Could not find the User Manual PDF.");
                    }
                } catch (Exception ex) {
                    showAlert("Error", null, "Failed to open PDF:\n" + ex.getMessage());
                }
            });

            Button backBtn = new Button("⬅ Back to Main Menu");
            backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
            backBtn.setStyle("-fx-background-color: #eeeeee;");
            backBtn.setOnAction(eg -> manualStage.close());

            VBox bottomBox = new VBox(10, new Separator(), pdfLink, backBtn);
            bottomBox.setAlignment(Pos.CENTER_LEFT);

            contentBox.getChildren().addAll(
                    title,
                    overview, addingGame, importExport,
                    searchFilter, sorting, details,
                    bottomBox
            );

            ScrollPane scrollPane = new ScrollPane(contentBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background: #f9f9f9;");

            Scene scene = new Scene(scrollPane, 640, 580);
            manualStage.setScene(scene);
            manualStage.show();
                });

        helpMenu.getItems().addAll(userManual, about);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private TitledPane createSection(String title, String bodyText) {
        Label body = new Label(bodyText);
        body.setWrapText(true);
        body.setFont(Font.font("Arial", 13));
        body.setStyle("-fx-text-fill: #333333;");

        VBox container = new VBox(body);
        container.setPadding(new Insets(10));
        container.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-radius: 5;");

        TitledPane pane = new TitledPane(title, container);
        pane.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        pane.setExpanded(false);
        return pane;
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

        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchButton.fire();
            }
        });

        VBox platformBox = new VBox(5);
        platformBox.setAlignment(Pos.TOP_LEFT);
        Label platformLabel = new Label("Platforms");
        platformLabel.setStyle("-fx-font-weight: bold;");
        CheckBox pcCheckBox = new CheckBox("PC");
        CheckBox psCheckBox = new CheckBox("PS");
        CheckBox xboxCheckBox = new CheckBox("Xbox");
        platformBox.getChildren().addAll(platformLabel, pcCheckBox, psCheckBox, xboxCheckBox);

        VBox featuresAndGenresBox = new VBox(5);
        featuresAndGenresBox.setAlignment(Pos.TOP_LEFT);
        Label featuresLabel = new Label("Game Features And Genres");
        featuresLabel.setStyle("-fx-font-weight: bold;");
        CheckBox multiplayerCheckBox = new CheckBox("Multiplayer");
        CheckBox singlePlayerCheckBox = new CheckBox("Single-player");
        CheckBox onlineCheckBox = new CheckBox("Online");
        CheckBox coopCheckBox = new CheckBox("Co-op");
        CheckBox openWorldCheckBox = new CheckBox("Open-world");
        featuresAndGenresBox.getChildren().addAll(featuresLabel, multiplayerCheckBox, singlePlayerCheckBox,
                onlineCheckBox, coopCheckBox, openWorldCheckBox);

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
        featuresAndGenresBox.getChildren().addAll(rpgCheckBox, turnBasedCheckBox,
                actionCheckBox, adventureCheckBox, strategyCheckBox, puzzleCheckBox,
                simulationCheckBox, fpsCheckBox, racingCheckBox, fightingCheckBox);

        searchButton.setOnAction(e -> {

            List<String> platformFilters = new ArrayList<>();
            if (pcCheckBox.isSelected())   platformFilters.add("pc");
            if (psCheckBox.isSelected())   platformFilters.add("playstation");
            if (xboxCheckBox.isSelected()) platformFilters.add("xbox");

            List<String> genreFilters = new ArrayList<>();
            if (rpgCheckBox.isSelected())         genreFilters.add("RPG");
            if (turnBasedCheckBox.isSelected())   genreFilters.add("Turn-based");
            if (actionCheckBox.isSelected())      genreFilters.add("Action");
            if (adventureCheckBox.isSelected())   genreFilters.add("Adventure");
            if (strategyCheckBox.isSelected())    genreFilters.add("Strategy");
            if (puzzleCheckBox.isSelected())      genreFilters.add("Puzzle");
            if (simulationCheckBox.isSelected())  genreFilters.add("Simulation");
            if (fpsCheckBox.isSelected())         genreFilters.add("FPS");
            if (racingCheckBox.isSelected())      genreFilters.add("Racing");
            if (fightingCheckBox.isSelected())    genreFilters.add("Fighting");

            if (multiplayerCheckBox.isSelected()) genreFilters.add("Multiplayer");
            if (singlePlayerCheckBox.isSelected()) genreFilters.add("Single-player");
            if (onlineCheckBox.isSelected())      genreFilters.add("Online");
            if (coopCheckBox.isSelected())        genreFilters.add("Co-op");
            if (openWorldCheckBox.isSelected())   genreFilters.add("Open World");

            String searchText = searchField.getText().trim().toLowerCase();

            filteredGames = games.stream()
                    .filter(game -> {
                        boolean textMatch;
                        if (searchText.isEmpty()) {
                            textMatch = true;
                        } else {
                            boolean nameMatch      = game.getName().toLowerCase().contains(searchText);
                            boolean developerMatch = game.getDeveloper().toLowerCase().contains(searchText);
                            boolean steamIdMatch   = game.getSteamAppId().equalsIgnoreCase(searchText);
                            boolean yearMatch      = false;
                            try { yearMatch = (game.getYear() == Integer.parseInt(searchText)); }
                            catch (NumberFormatException ignored) {}
                            textMatch = nameMatch || developerMatch || steamIdMatch || yearMatch;
                        }
                        boolean platformMatch =
                                platformFilters.isEmpty() ||
                                        game.getPlatforms().stream().anyMatch(p ->
                                                platformFilters.stream().anyMatch(f ->
                                                        p.toLowerCase().contains(f)));
                        boolean genreMatch =
                                genreFilters.isEmpty() ||
                                        game.getGenres().stream().anyMatch(g ->
                                                genreFilters.stream().anyMatch(f ->
                                                        g.equalsIgnoreCase(f)));

                        return textMatch && platformMatch && genreMatch;
                    })
                    .collect(Collectors.toList());

            if (filteredGames.isEmpty()) {
                showAlert("No Results", null,
                        "No games found matching your search/filters.");
            }
            createGameGrid(filteredGames);
        });

        searchBox.getChildren().addAll(titleLabel, searchArea,
                platformBox, featuresAndGenresBox);

        return searchBox;
    }

    private void createGameGrid() {
        createGameGrid(games);
    }

    private void createGameGrid(List<Game> gamesToDisplay) {

        Map<String, Game> uniqueGamesMap = new LinkedHashMap<>();
        Set<String> duplicateIds = new HashSet<>();

        for (Game game : gamesToDisplay) {
            String steamId = game.getSteamAppId();
            if (steamId != null) {
                if (uniqueGamesMap.containsKey(steamId)) {
                    duplicateIds.add(steamId);
                } else {
                    uniqueGamesMap.put(steamId, game);
                }
            }
        }

        if (!duplicateIds.isEmpty()) {
            StringBuilder warningMessage = new StringBuilder("Games with the following SteamID have been added more than once:\n");
            for (String id : duplicateIds) {
                warningMessage.append("- ").append(id).append("\n");
            }

            String explanation = " If you performed an Add operation, your game was not added.\n "
                    + "If you performed an Edit operation, one of the games with the same SteamID "
                    + "was removed from the list.";

            Label label = new Label(warningMessage.toString() + "\n\n" + explanation);
            label.setWrapText(true);
            label.setMaxWidth(400);
            label.setStyle("-fx-font-size: 13;");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Repeated Games");
            alert.setHeaderText("Games Found with Duplicate SteamIDs");
            alert.getDialogPane().setContent(label);
            alert.showAndWait();
        }


        gamesToDisplay.clear();
        gamesToDisplay.addAll(uniqueGamesMap.values());

        gridPane.getChildren().clear();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        int col = 0, row = 0;
        for (Game game : gamesToDisplay) {
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
        imageView.setFitWidth(120);
        imageView.setFitHeight(170);
        imageView.setPickOnBounds(true);
        imageView.setOnMouseClicked(e -> openGameDetail(game));

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

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

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imageView.setClip(clip);

        Label nameLabel = new Label("Game: " + game.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label developerLabel = new Label("Developer: " + game.getDeveloper());
        Label yearLabel = new Label("Release Year: " + game.getYear());
        Label steamIdLabel = new Label("Steam ID: " + game.getSteamAppId());
        Label playtimeLabel = new Label("Playtime: " + game.getTotalPlaytimeHours() + " hours");

        String platforms = String.join(", ", game.getPlatforms());
        Label platformsLabel = new Label("Platforms: " + platforms);

        String genres = String.join(", ", game.getGenres());
        Label genresLabel = new Label("Genres: " + genres);

        String publishers = String.join(", ", game.getPublishers());
        Label publishersLabel = new Label("Publishers: " + publishers);

        VBox localizationsBox = new VBox(5);
        int i =0;
        for (Game.Localization loc : game.getLocalizations()) {
            if(i==0){
                localizationsBox.getChildren().add(new Label("Localizations:"));
            }
            Label locLabel = new Label("  - " + loc.getLanguage() +
                    " (Translators: " + String.join(", ", loc.getTranslators()) +
                    ", Dubbing: " + String.join(", ", loc.getDubbingArtists()) + ")");
            localizationsBox.getChildren().add(locLabel);
            i++;
        }
        localizationsBox.setAlignment(Pos.CENTER);

        developerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        yearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        nameLabel.setTextFill(Color.DARKBLUE);
        yearLabel.setTextFill(Color.DARKGREEN);

        VBox vBox = new VBox(20, imageView,nameLabel, developerLabel, yearLabel, steamIdLabel, playtimeLabel,
                platformsLabel, genresLabel, publishersLabel, localizationsBox);

        Button editBtn   = new Button("Edit Game");
        Button deleteBtn = new Button("Delete Game");
        HBox   btnBar    = new HBox(10, editBtn, deleteBtn);
        btnBar.setAlignment(Pos.CENTER);
        vBox.getChildren().add(btnBar);

        deleteBtn.setOnAction(ev -> {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
            conf.initOwner(detailStage);
            conf.setTitle("Confirm Deletion");
            conf.setHeaderText(null);
            conf.setContentText(
                    "Delete \"" + game.getName() + "\" ?"
            );

            conf.getButtonTypes().setAll(
                    ButtonType.YES, ButtonType.CANCEL);

            conf.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.YES) {
                    games.remove(game);
                    filteredGames = games;
                    createGameGrid();
                    detailStage.close();
                }
            });
        });
        editBtn.setOnAction(ev -> {
            Dialog<Game> dialog = new Dialog<>();
            dialog.setTitle("Edit Game");
            dialog.setHeaderText("Update the fields and press Save");

            ButtonType saveBtnType =
                    new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes()
                    .addAll(saveBtnType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10); grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nameF  = new TextField(game.getName());
            TextField devF   = new TextField(game.getDeveloper());
            TextField yearF  = new TextField(String.valueOf(game.getYear()));
            TextField idF    = new TextField(game.getSteamAppId());
            TextField playF  = new TextField(
                    String.valueOf(game.getTotalPlaytimeHours()));
            TextField imgF   = new TextField(game.getImagePath());
            TextField genF   = new TextField(String.join(", ", game.getGenres()));
            TextField pubF   = new TextField(String.join(", ", game.getPublishers()));
            TextField platF  = new TextField(String.join(", ", game.getPlatforms()));

            int r = 0;
            grid.addRow(r++, new Label("Name:"),       nameF);
            grid.addRow(r++, new Label("Developer:"),  devF);
            grid.addRow(r++, new Label("Year:"),       yearF);
            grid.addRow(r++, new Label("Steam ID:"),   idF);
            grid.addRow(r++, new Label("Playtime:"),   playF);
            grid.addRow(r++, new Label("Image Path:"), imgF);
            grid.addRow(r++, new Label("Genres:"),     genF);
            grid.addRow(r++, new Label("Publishers:"), pubF);
            grid.addRow(r++, new Label("Platforms:"),  platF);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(btn -> {
                if (btn == saveBtnType) {
                    try {
                        game.setName(nameF.getText().trim());
                        game.setDeveloper(devF.getText().trim());
                        game.setYear(Integer.parseInt(yearF.getText().trim()));
                        game.setSteamAppId(idF.getText().trim());
                        game.setTotalPlaytimeHours(
                                Integer.parseInt(playF.getText().trim()));
                        game.setImagePath(imgF.getText().trim());
                        game.setGenres(parseCsv(genF.getText()));
                        game.setPublishers(parseCsv(pubF.getText()));
                        game.setPlatforms(parseCsv(platF.getText()));
                        return game;
                    } catch (NumberFormatException ex) {
                        new Alert(Alert.AlertType.ERROR,
                                "Year and Playtime must be numeric!").showAndWait();
                    }
                }
                return null;
            });

            dialog.showAndWait().ifPresent(updated -> {
                createGameGrid();

                nameLabel.setText("Game: " + updated.getName());
                developerLabel.setText("Developer: " + updated.getDeveloper());
                yearLabel.setText("Release Year: " + updated.getYear());
                steamIdLabel.setText("Steam ID: " + updated.getSteamAppId());
                playtimeLabel.setText("Playtime: "
                        + updated.getTotalPlaytimeHours() + " hours");
                imageView.setImage(new Image(updated.getImagePath(), true));

                platformsLabel.setText("Platforms: "
                        + String.join(", ", updated.getPlatforms()));
                genresLabel.setText("Genres: "
                        + String.join(", ", updated.getGenres()));
                publishersLabel.setText("Publishers: "
                        + String.join(", ", updated.getPublishers()));

                localizationsBox.getChildren().clear();
                int k = 0;
                for (Game.Localization loc : updated.getLocalizations()) {
                    if (k++ == 0) localizationsBox.getChildren()
                            .add(new Label("Localizations:"));
                    localizationsBox.getChildren().add(
                            new Label("  - " + loc.getLanguage()
                                    + " (Translators: " + String.join(", ", loc.getTranslators())
                                    + ", Dubbing: "   + String.join(", ", loc.getDubbingArtists()) + ")"));
                }
            });
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.layout();
        double prefWidth = vBox.prefWidth(-1) + vBox.getPadding().getLeft() + vBox.getPadding().getRight();
        double prefHeight = vBox.prefHeight(-1) + vBox.getPadding().getTop() + vBox.getPadding().getBottom();

        Scene scene = new Scene(vBox, prefWidth*1.3, prefHeight*1.3);
        detailStage.setTitle(game.getName());
        detailStage.setScene(scene);
        detailStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static List<String> parseCsv(String text) {
        return Arrays.stream(text.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private static void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}