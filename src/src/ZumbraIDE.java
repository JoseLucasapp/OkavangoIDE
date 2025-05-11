import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;

public class ZumbraIDE extends Application{
    @Override
    public void start(Stage stage){
        TextArea editor = new TextArea();
        TextArea output = new TextArea();
        output.setEditable(false);

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

        VBox layout = new VBox(editor, runButton, new Label("Output: "), output);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 800, 600);

        stage.setTitle("OkavangoIDE");
        stage.setScene(scene);
        stage.show();
    }

}