package main.java.com.joselucasapp.okavangoide;

import javafx.scene.control.TextArea;

import java.io.*;

public class RunCode {
    public void start(TextArea editor, TextArea output){
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
    }
}
