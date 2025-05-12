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
        TextArea editor = new TextArea();
        TextArea output = new TextArea();
        output.setEditable(false);
        editor.getStyleClass().add("editor-canva");

        editor.setBackground(new Background(new BackgroundFill(Color.rgb(68,71,90), null, null)));

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

        Text outputText = new Text("Output: ");
        outputText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox layout_btn_msg = new HBox(outputText, spacer, runButton);

        layout_btn_msg.setAlignment(Pos.CENTER_LEFT);
        layout_btn_msg.setPadding(new Insets(10));

        VBox layout_infos_output = new VBox(layout_btn_msg, output);
        HBox layout = new HBox(editor, layout_infos_output);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        layout.setMinWidth(800);
        layout.getStyleClass().add("layout");
        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/stylesheet.css")).toExternalForm());
        stage.setTitle("OkavangoIDE");
        stage.setScene(scene);

        stage.show();
    }

}