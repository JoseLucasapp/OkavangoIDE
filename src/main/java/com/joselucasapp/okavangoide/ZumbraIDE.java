package com.joselucasapp.okavangoide;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.joselucasapp.okavangoide.helpers.ScreenData;
import org.fxmisc.richtext.CodeArea;

import java.util.Objects;

public class ZumbraIDE extends Application{
    @Override
    public void start(Stage stage){
        SelectFile selectFile = new SelectFile();
        RunCode runCode = new RunCode();
        Region spacer = new Region();
        TextField textField = new TextField();
        Editor editorField = new Editor();
        ScreenData screenData = new ScreenData();
        double screenX = screenData.getData()[0];
        double screenY = screenData.getData()[1];

        TabPane tabEditors = new TabPane();
        tabEditors.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        tabEditors.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/highlight.css")).toExternalForm());

        tabEditors.getTabs().add(createEditorTab("Welcome", "Welcome to OkavangoIDE !!!", editorField));

        TextArea output = textField.start(0.2 * screenY, 16, "Fira code", false);

        Button runButton = new Button("Run");
        runButton.setStyle(
            "-fx-font-size: 16px;"+
            "-fx-padding: 10;"+
            "-fx-background-color: #282a36;"+
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

        Button openFile = new Button("File");
        openFile.setStyle(
            "-fx-font-size: 16px;"+
            "-fx-background-color: #282a36;"+
            "-fx-text-fill: #f8f8f2;"+
            "-fx-cursor: hand;"
        );


        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox.setMargin(openFile, new Insets(0,0,0,20));

        HBox top_bar_menu = new HBox();
        top_bar_menu.getChildren().add(openFile);
        top_bar_menu.setPadding(new Insets(10));
        top_bar_menu.setAlignment(Pos.CENTER);
        top_bar_menu.setMinWidth(screenX);
        top_bar_menu.setMinHeight(0.05 * screenY);

        HBox layout_btn_msg = new HBox(runButton);

        layout_btn_msg.setAlignment(Pos.CENTER_LEFT);
        layout_btn_msg.setPadding(new Insets(10, 0, 10, 0));

        VBox layout_infos_output = new VBox(layout_btn_msg, output);

        VBox editor_box = new VBox(tabEditors);
        editor_box.setMinHeight(0.63 * screenY);


        VBox.setVgrow(tabEditors, Priority.ALWAYS);
        VBox layout = new VBox(editor_box);
        VBox.setVgrow(layout, Priority.ALWAYS);
        layout.getChildren().add(layout_infos_output);
        layout.setPadding(new Insets(10));
        layout.setMinWidth(0.8 * screenX);
        layout.setMinHeight(0.95 * screenY);
        layout.setStyle(
            "-fx-background-color: #282a36;"
        );


        StackPane lateral_menu = new StackPane();
        VBox.setVgrow(lateral_menu, Priority.ALWAYS);
        lateral_menu.setStyle("""
            -fx-background-color: #282a36;
            -fx-border-color: transparent;
            -fx-padding: 0;
        """);
        lateral_menu.setMinHeight(0);
        lateral_menu.setMaxHeight(Double.MAX_VALUE);
        lateral_menu.getStyleClass().add("lateral-menu");
        lateral_menu.setMinWidth(0.2 * screenX);

        openFile.setOnAction(e-> selectFile.start(stage, lateral_menu, tabEditors, editorField));
        HBox body = new HBox(lateral_menu, layout);

        VBox okavangoIDE = new VBox(top_bar_menu, body);
        okavangoIDE.setMinWidth(screenX);
        okavangoIDE.setStyle(
                "-fx-background-color: #282a36;"
        );
        Scene scene = new Scene(okavangoIDE, screenX, screenY);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> selectFile.saveFile(e, tabEditors));


        stage.setTitle("OkavangoIDE");
        stage.setScene(scene);
        stage.show();
    }

    private Tab createEditorTab(String fileName, String content, Editor editorField){
        CodeArea editor = editorField.start();

        editor.replaceText(content);

        Tab tab = new Tab(fileName);
        tab.setContent(editor);
        tab.setUserData(editor);
        return tab;
    }

}