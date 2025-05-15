package com.joselucasapp.okavangoide;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomTopBar {
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean maximized = false;

    public HBox customTopBar(Stage stage, double screenX, double screenY, Button openFile, Button runCode){
        stage.initStyle(StageStyle.UNDECORATED);

        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #171131; -fx-padding: 8;");
        titleBar.setSpacing(10);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setPrefHeight(0.2 * screenY);

        Label title = new Label("OkavangoIDE");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button minimizeBtn = new Button("−");
        Button closeBtn = new Button("✕");

        SVGPath maximizeIcon = new SVGPath();
        maximizeIcon.setContent("M4 4 H20 V20 H4 Z"); // exemplo de quadrado
        maximizeIcon.setFill(Color.WHITE);
        maximizeIcon.setScaleX(0.7);
        maximizeIcon.setScaleY(0.7);

        Button maximizeBtn = new Button();
        maximizeBtn.setGraphic(maximizeIcon);
        maximizeBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

        for (Button btn : new Button[]{minimizeBtn, closeBtn}) {
            btn.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-cursor: hand;"
            );
            btn.setPrefWidth(32);
        }


        minimizeBtn.setOnAction(e -> stage.setIconified(true));
        maximizeBtn.setOnAction(e->{
            if (maximized){
                stage.setWidth(screenX);
                stage.setHeight(screenY);
                stage.centerOnScreen();
            }else{
                stage.setWidth(0.5 * screenX);
                stage.setHeight(0.5 * screenY);
            }

            maximized = !maximized;
        });
        closeBtn.setOnAction(e -> stage.close());

        titleBar.setOnMousePressed((MouseEvent e)->{
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        titleBar.setOnMouseDragged((MouseEvent e)->{
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });

        HBox icons = new HBox(minimizeBtn, maximizeBtn, closeBtn);
        icons.setAlignment(Pos.CENTER);
        icons.setPrefHeight(0.2 * screenY);
        icons.setSpacing(6);

        titleBar.getChildren().addAll(title, openFile, runCode, spacer, icons);

        return titleBar;
    }
}
