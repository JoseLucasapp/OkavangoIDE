package com.joselucasapp.okavangoide.helpers;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Content {

    public VBox getLayout(VBox editor_box, VBox layout_infos_output, Stage stage ){
        VBox layout = new VBox(editor_box);
        VBox.setVgrow(layout, Priority.ALWAYS);
        layout.getChildren().add(layout_infos_output);
        layout.setPadding(new Insets(10));
        layout.prefWidthProperty().bind(stage.widthProperty().multiply(0.8));
        layout.prefHeightProperty().bind(stage.heightProperty().multiply(0.95));
        layout.setStyle(
                "-fx-background-color: #130B28;"
        );

        return  layout;
    }

    public StackPane getLateralMenu(Stage stage){
        StackPane lateral_menu = new StackPane();
        VBox.setVgrow(lateral_menu, Priority.ALWAYS);
        lateral_menu.setStyle("""
            -fx-background-color: #130B28;
            -fx-border-color: transparent;
            -fx-padding: 0;
        """);
        lateral_menu.setMinHeight(0);
        lateral_menu.setMaxHeight(Double.MAX_VALUE);
        lateral_menu.getStyleClass().add("lateral-menu");
        lateral_menu.prefWidthProperty().bind(stage.widthProperty().multiply(0.2));


        return  lateral_menu;
    }
}
