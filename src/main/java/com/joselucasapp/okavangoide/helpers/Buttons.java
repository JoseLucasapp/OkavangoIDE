package com.joselucasapp.okavangoide.helpers;

import com.joselucasapp.okavangoide.RunCode;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.util.function.Supplier;

public class Buttons {
    public Button getRunButton(TabPane tabEditors, RunCode runCode, TextArea output, Supplier<File> rootFolderSupplier ) {
        Button runButton = new Button("Run");


        runButton.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-padding: 10;" +
                        "-fx-background-color: #171131;" +
                        "-fx-text-fill: #f8f8f2;" +
                        "-fx-cursor: hand;" +
                        "-fx-underline: false;"
        );
        runButton.setOnMouseEntered(e -> runButton.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-padding: 10;" +
                        "-fx-background-color: #171131;" +
                        "-fx-text-fill: #f8f8f2;" +
                        "-fx-cursor: hand;" +
                        "-fx-underline: true;"
        ));
        runButton.setOnMouseExited(e -> runButton.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-padding: 10;" +
                        "-fx-background-color: #171131;" +
                        "-fx-text-fill: #f8f8f2;" +
                        "-fx-cursor: hand;" +
                        "-fx-underline: false;"
        ));

        runButton.setOnAction(e -> {
            Tab selectedTab = tabEditors.getSelectionModel().getSelectedItem();
            if (selectedTab != null) {
                Object userData = selectedTab.getUserData();

                if (userData instanceof CodeArea editor) {
                    File workingDir = rootFolderSupplier.get();
                    if (workingDir != null) {
                        runCode.start(editor, output, workingDir);
                    } else {
                        output.setText("⚠ No selected folders.");
                    }
                } else {
                    output.setText("⚠ This tab has not a valid editor.");
                }
            }
        });

        return runButton;
    }

    public Button getOpenFileButton(){
        Button openFile = new Button("File");
        openFile.setStyle(
                "-fx-font-size: 14px;"+
                        "-fx-background-color: #171131;"+
                        "-fx-text-fill: #f8f8f2;"+
                        "-fx-cursor: hand;"
        );

        openFile.setOnMouseEntered(e -> openFile.setStyle(
                "-fx-font-size: 14px;"+
                        "-fx-background-color: #171131;"+
                        "-fx-text-fill: #f8f8f2;"+
                        "-fx-cursor: hand;" +
                        "-fx-underline: true;"
        ));
        openFile.setOnMouseExited(e -> openFile.setStyle(
                "-fx-font-size: 14px;"+
                        "-fx-background-color: #171131;"+
                        "-fx-text-fill: #f8f8f2;"+
                        "-fx-cursor: hand;" +
                        "-fx-underline: false;"
        ));

        return  openFile;
    }

}
