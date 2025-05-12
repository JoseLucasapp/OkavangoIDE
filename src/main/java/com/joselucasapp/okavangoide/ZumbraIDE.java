package main.java.com.joselucasapp.okavangoide;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class ZumbraIDE extends Application{
    @Override
    public void start(Stage stage){
        SelectFile selectFile = new SelectFile();

        TextArea editor = new TextArea();
        editor.setFont(Font.font("Fira code", 16));
        editor.setMinWidth(800);
        editor.getStyleClass().add("text-area");
        editor.setBackground(new Background(new BackgroundFill(Color.rgb(68,71,90), null, null)));

        TextArea output = new TextArea();
        output.setFont(Font.font("Fira code", 16));
        output.setEditable(false);
        output.getStyleClass().add("text-area");

        Button runButton = new Button("Run");
        runButton.getStyleClass().add("run-button");
        runButton.setMinWidth(100);

        runButton.setOnAction(e ->{
            try{
                File temp = File.createTempFile("code", ".zum");
                try(FileWriter fw = new FileWriter(temp)){
                    fw.write(editor.getText());
                }

                ProcessBuilder pb = new ProcessBuilder("zumbra", temp.getAbsolutePath());
                Process proc = pb.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    result.append(line).append("\n");
                }

                output.setText(result.toString());

            }catch (IOException ex){
                output.setText("Error: " + ex.getMessage());
            }
        });

        Button openFile = new Button("File");
        openFile.getStyleClass().add("run-button");

        openFile.setOnAction(e->{
            selectFile.start(stage);
        });
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox top_bar_menu = new HBox(openFile, spacer);
        top_bar_menu.setAlignment(Pos.CENTER_LEFT);
        top_bar_menu.setPadding(new Insets(10));

        Text outputText = new Text("Output: ");
        outputText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        outputText.getStyleClass().add("output-text");
        outputText.setFill(Color.rgb(248,248,242));


        HBox layout_btn_msg = new HBox(outputText, spacer, runButton);

        layout_btn_msg.setAlignment(Pos.CENTER_LEFT);
        layout_btn_msg.setPadding(new Insets(10));

        VBox layout_infos_output = new VBox(layout_btn_msg, output);
        HBox layout = new HBox(editor);
        HBox.setMargin(layout_infos_output, new Insets(0,0,0,20));
        HBox.setHgrow(layout, Priority.ALWAYS);
        layout.getChildren().add(layout_infos_output);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        layout.setMinWidth(700);
        layout.setMinHeight(650);
        layout.getStyleClass().add("layout");

        VBox okavangoIDE = new VBox(top_bar_menu, layout);
        okavangoIDE.setMinWidth(800);
        okavangoIDE.getStyleClass().add("ide");
        Scene scene = new Scene(okavangoIDE, 1000, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/resources/css/stylesheet.css")).toExternalForm());

        stage.setTitle("OkavangoIDE");
        stage.setScene(scene);

        stage.show();
    }

}