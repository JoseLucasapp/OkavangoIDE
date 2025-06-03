package com.joselucasapp.okavangoide.helpers;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class ResizeWindow {
    private static final int BORDER = 6;
    private static Cursor resizeCursor = Cursor.DEFAULT;
    private static boolean isDragging = false;

    public static void makeResizable(Stage stage, Scene scene) {
        scene.setOnMouseMoved(event -> {
            if (isDragging) return;

            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();

            if (x < BORDER && y < BORDER) {
                resizeCursor = Cursor.NW_RESIZE;
            } else if (x > width - BORDER && y < BORDER) {
                resizeCursor = Cursor.NE_RESIZE;
            } else if (x < BORDER && y > height - BORDER) {
                resizeCursor = Cursor.SW_RESIZE;
            } else if (x > width - BORDER && y > height - BORDER) {
                resizeCursor = Cursor.SE_RESIZE;
            } else if (x < BORDER) {
                resizeCursor = Cursor.W_RESIZE;
            } else if (x > width - BORDER) {
                resizeCursor = Cursor.E_RESIZE;
            } else if (y < BORDER) {
                resizeCursor = Cursor.N_RESIZE;
            } else if (y > height - BORDER) {
                resizeCursor = Cursor.S_RESIZE;
            } else {
                resizeCursor = Cursor.DEFAULT;
            }

            scene.setCursor(resizeCursor);
        });

        scene.setOnMousePressed(event -> isDragging = scene.getCursor() != Cursor.DEFAULT);

        scene.setOnMouseReleased(event -> isDragging = false);

        scene.setOnMouseDragged(event -> {
            if (!isDragging) return;

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double mouseX = event.getScreenX();
            double mouseY = event.getScreenY();

            switch (resizeCursor.toString()) {
                case "E_RESIZE":
                    double newWidthE = mouseX - stage.getX();
                    if (newWidthE > stage.getMinWidth() && newWidthE < screenBounds.getWidth()) {
                        stage.setWidth(newWidthE);
                    }
                    break;

                case "SE_RESIZE":
                    double newWidthSE = mouseX - stage.getX();
                    double newHeightSE = mouseY - stage.getY();
                    if (newWidthSE > stage.getMinWidth()) {
                        stage.setWidth(newWidthSE);
                    }
                    if (newHeightSE > stage.getMinHeight()) {
                        stage.setHeight(newHeightSE);
                    }
                    break;

                case "NE_RESIZE":
                    double newWidthNE = mouseX - stage.getX();
                    double newHeightNE = stage.getY() + stage.getHeight() - mouseY;
                    if (newWidthNE > stage.getMinWidth()) {
                        stage.setWidth(newWidthNE);
                    }
                    if (newHeightNE > stage.getMinHeight()) {
                        stage.setY(mouseY);
                        stage.setHeight(newHeightNE);
                    }
                    break;
            }
        });
    }
}
