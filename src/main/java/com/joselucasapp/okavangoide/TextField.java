package main.java.com.joselucasapp.okavangoide;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextField {
    public TextArea start(double minHeight, int fontSize, String fontFamily, String cssClass, Color rgb, Boolean editable){
        TextArea editor = new TextArea();
        editor.setFont(Font.font(fontFamily, fontSize));
        editor.setMinHeight(minHeight);
        editor.getStyleClass().add(cssClass);
        editor.setEditable(editable);
        editor.setBackground(new Background(new BackgroundFill(rgb, null, null)));

        return editor;
    }
}
