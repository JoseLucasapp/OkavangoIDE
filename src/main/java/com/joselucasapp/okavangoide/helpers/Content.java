package com.joselucasapp.okavangoide.helpers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Content {
    public HBox getTopBar(Button openFile, double screenX , double screenY){
        HBox top_bar = new HBox();
        top_bar.getChildren().add(openFile);
        top_bar.setPadding(new Insets(10));
        top_bar.setAlignment(Pos.CENTER);
        top_bar.setMinWidth(screenX);
        top_bar.setMinHeight(0.05 * screenY);

        return top_bar;
    }

    public VBox getLayout(VBox editor_box, VBox layout_infos_output, double screenX, double screenY){
        VBox layout = new VBox(editor_box);
        VBox.setVgrow(layout, Priority.ALWAYS);
        layout.getChildren().add(layout_infos_output);
        layout.setPadding(new Insets(10));
        layout.setMinWidth(0.8 * screenX);
        layout.setMinHeight(0.95 * screenY);
        layout.setStyle(
                "-fx-background-color: #130B28;"
        );

        return  layout;
    }

    public StackPane getLateralMenu(double screenX){
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
        lateral_menu.setMinWidth(0.2 * screenX);

        return  lateral_menu;
    }
}
