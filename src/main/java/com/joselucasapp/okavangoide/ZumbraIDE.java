package com.joselucasapp.okavangoide;

import com.joselucasapp.okavangoide.helpers.Buttons;
import com.joselucasapp.okavangoide.helpers.Content;
import com.joselucasapp.okavangoide.helpers.TextField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
        com.joselucasapp.okavangoide.helpers.TextField textField = new TextField();
        Editor editorField = new Editor();
        ScreenData screenData = new ScreenData();
        Buttons buttons = new Buttons();
        Content content = new Content();
        CustomTopBar topBar = new CustomTopBar();

        double screenX = screenData.getData()[0];
        double screenY = screenData.getData()[1];

        TabPane tabEditors = new TabPane();
        tabEditors.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        tabEditors.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/highlight.css")).toExternalForm());

        tabEditors.getTabs().add(createEditorTab(editorField));

        TextArea output = textField.start(0.2 * screenY);

        Button runButton = buttons.getRunButton(tabEditors, runCode, output);
        Button openFile = buttons.getOpenFileButton();

        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox.setMargin(openFile, new Insets(0,0,0,20));

        HBox layout_btn_msg = new HBox(runButton);

        layout_btn_msg.setAlignment(Pos.CENTER_LEFT);
        layout_btn_msg.setPadding(new Insets(10, 0, 10, 0));

        VBox layout_infos_output = new VBox(layout_btn_msg, output);

        VBox editor_box = new VBox(tabEditors);
        editor_box.setMinHeight(0.63 * screenY);
        editor_box.setStyle("-fx-text-fill: #171131; -fx-font-family: Fira code; -fx-font-size: 16px; -fx-background-color: #171131;");

        VBox.setVgrow(tabEditors, Priority.ALWAYS);
        VBox layout = content.getLayout(editor_box, layout_infos_output, screenX, screenY);

        StackPane lateral_menu = content.getLateralMenu(screenX);

        openFile.setOnAction(e-> selectFile.start(stage, lateral_menu, tabEditors, editorField));
        HBox body = new HBox(lateral_menu, layout);
        HBox.setHgrow(body, Priority.ALWAYS);
        body.setPrefHeight(screenY);

        VBox okavangoIDE = new VBox(body);
        okavangoIDE.setMinWidth(screenX);
        okavangoIDE.setStyle(
                "-fx-background-color: #130B28;"
        );

        VBox root = new VBox(topBar.customTopBar(stage, screenX, screenY, openFile, runButton), okavangoIDE);
        Scene scene = new Scene(root, screenX, screenY);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> selectFile.saveFile(e, tabEditors));

        stage.setTitle("OkavangoIDE");

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png"))));
        stage.setScene(scene);
        stage.show();
    }

    private Tab createEditorTab(Editor editorField){
        CodeArea editor = editorField.start();

        editor.replaceText("Welcome to OkavangoIDE !!!");

        Tab tab = new Tab("Welcome");
        tab.setContent(editor);
        tab.setUserData(editor);
        return tab;
    }

}