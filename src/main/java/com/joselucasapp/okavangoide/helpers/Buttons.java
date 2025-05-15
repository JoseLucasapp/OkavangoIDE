package com.joselucasapp.okavangoide.helpers;

import com.joselucasapp.okavangoide.RunCode;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import org.fxmisc.richtext.CodeArea;

public class Buttons {
    public Button getRunButton(TabPane tabEditors, RunCode runCode, TextArea output) {
        Button runButton = new Button("Run");
        runButton.setStyle(
                "-fx-font-size: 16px;"+
                        "-fx-padding: 10;"+
                        "-fx-background-color: #130B28;"+
                        "-fx-text-fill: #f8f8f2;"+
                        "-fx-border-color: #44475a;"+
                        "-fx-border-width: 1px;"+
                        "-fx-border-style: solid;"+
                        "-fx-cursor: hand;"
        );
        runButton.setMinWidth(100);

        runButton.setOnAction(e -> {
            Tab selectedTab = tabEditors.getSelectionModel().getSelectedItem();
            if(selectedTab != null){
                CodeArea editor = (CodeArea) selectedTab.getUserData();
                runCode.start(editor, output);
            }

        });
        return runButton;
    }

    public Button getOpenFileButton(){
        Button openFile = new Button("File");
        openFile.setStyle(
                "-fx-font-size: 16px;"+
                        "-fx-background-color: #130B28;"+
                        "-fx-text-fill: #f8f8f2;"+
                        "-fx-cursor: hand;"
        );

        return  openFile;
    }

}
