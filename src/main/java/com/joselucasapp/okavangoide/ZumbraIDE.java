package main.java.com.joselucasapp.okavangoide;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.java.com.joselucasapp.okavangoide.helpers.ScreenData;

public class ZumbraIDE extends Application{
    @Override
    public void start(Stage stage){
        SelectFile selectFile = new SelectFile();
        RunCode runCode = new RunCode();
        Region spacer = new Region();
        TextField textField = new TextField();
        ScreenData screenData = new ScreenData();
        double screenX = screenData.getData()[0];
        double screenY = screenData.getData()[1];

        TextArea editor = textField.start(0.63 * screenY,16, "Fira code", true);
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

        runButton.setOnAction(e -> runCode.start(editor, output));

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

        VBox layout = new VBox(editor);
        VBox.setVgrow(layout, Priority.ALWAYS);
        layout.getChildren().add(layout_infos_output);
        layout.setPadding(new Insets(10));
        layout.setMinWidth(0.8 * screenX);
        layout.setMinHeight(0.95 * screenY);
        layout.setStyle(
            "-fx-background-color: #282a36;"
        );

        VBox lateral_menu = new VBox();
        lateral_menu.getStyleClass().add("lateral-menu");
        lateral_menu.setMinWidth(0.2 * screenX);
        lateral_menu.setMinHeight(screenY - 100);

        openFile.setOnAction(e-> selectFile.start(stage, lateral_menu, editor));
        HBox body = new HBox(lateral_menu, layout);

        VBox okavangoIDE = new VBox(top_bar_menu, body);
        okavangoIDE.setMinWidth(screenX);
        okavangoIDE.setStyle(
                "-fx-background-color: #282a36;"
        );
        Scene scene = new Scene(okavangoIDE, screenX, screenY);

        stage.setTitle("OkavangoIDE");
        stage.setScene(scene);
        stage.show();
    }

}