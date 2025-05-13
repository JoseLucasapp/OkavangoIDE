package main.java.com.joselucasapp.okavangoide;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class TextField {
    public TextArea start(double minHeight, int fontSize, String fontFamily, Boolean editable){
        TextArea editor = new TextArea();
        editor.setFont(Font.font(fontFamily, fontSize));
        editor.setMinHeight(minHeight);
        editor.setEditable(editable);
        editor.setStyle(
            "-fx-control-inner-background: #44475a;" +
            "-fx-background-color: #282a36;" +
            "-fx-text-fill: #f8f8f2;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;"
        );
        return editor;
    }
}
