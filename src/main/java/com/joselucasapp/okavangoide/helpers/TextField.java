package com.joselucasapp.okavangoide.helpers;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class TextField {
    public TextArea start(double minHeight){
        TextArea editor = new TextArea();
        editor.setFont(Font.font("Fira code", 16));
        editor.setMinHeight(minHeight);
        editor.setEditable(false);
        editor.setStyle(
                "-fx-control-inner-background: #171131;" +
                        "-fx-background-color: #171131;" +
                        "-fx-text-fill: #f8f8f2;" +
                        "-fx-border-color: transparent;" +
                        "-fx-background-insets: 0;" +
                        "-fx-background-radius: 0;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-effect: none;"
        );

        return editor;
    }
}
