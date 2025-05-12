package main.java.com.joselucasapp.okavangoide;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectFile {
    public void start(Stage stage){
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("File");
        fileChooser.showOpenDialog(stage);
    }
}
