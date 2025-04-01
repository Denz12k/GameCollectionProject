package com.example.gamecollection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

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

        Menu viewMenu = new Menu("View");
        MenuItem listView = new MenuItem("List View");
        MenuItem cardView = new MenuItem("Card View");
        viewMenu.getItems().addAll(listView, cardView);

        Menu helpMenu = new Menu("Help");
        MenuItem userManual = new MenuItem("User Manual");
        MenuItem about = new MenuItem("About");
        helpMenu.getItems().addAll(userManual, about);

        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(menuBar);

        root.setTopAnchor(menuBar, 0.0);
        root.setLeftAnchor(menuBar, 0.0);
        root.setRightAnchor(menuBar, 0.0);

        Scene scene = new Scene(root,900,600);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setTitle("Game Collection Library");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}