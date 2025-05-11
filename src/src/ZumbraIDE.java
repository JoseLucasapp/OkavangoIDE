import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class ZumbraIDE extends Application{
    @Override
    public void start(Stage stage){
        TextArea editor = new TextArea();
        TextArea output = new TextArea();
        output.setEditable(false);
        editor.setMinSize(700, 500);

        Button runButton = new Button("Run");

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

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);

        Scene scene = new Scene(grid, 800, 600);

        stage.setTitle("OkavangoIDE");
        stage.setScene(scene);

        grid.add(editor, 0, 0, 2, 1);
        grid.add(new Label("Output: "), 5, 1);
        grid.add(runButton, 5,0);
        grid.add(output, 6, 0);

        grid.setBackground(new Background(new BackgroundFill(Color.rgb(40,42,54), null, null)));

        stage.show();
    }

}