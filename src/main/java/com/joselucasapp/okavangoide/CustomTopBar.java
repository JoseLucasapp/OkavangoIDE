package com.joselucasapp.okavangoide;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomTopBar {
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean maximized = false;

    public HBox customTopBar(Stage stage, double screenX, double screenY, Button openFile){
        stage.initStyle(StageStyle.UNDECORATED);

        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: #171131; -fx-padding: 8; -fx-alignment: center-left;");
        titleBar.setSpacing(10);

        Label title = new Label("OkavangoIDE");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button minimizeBtn = new Button("−");
        Button maximizeBtn = new Button("□");
        Button closeBtn = new Button("✕");

        for (Button btn : new Button[]{minimizeBtn, maximizeBtn, closeBtn}) {
            btn.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 13px;" +
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

        titleBar.getChildren().addAll(title, openFile, spacer, minimizeBtn, maximizeBtn, closeBtn);

        return titleBar;
    }
}
