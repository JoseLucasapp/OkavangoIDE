package com.joselucasapp.okavangoide;

import javafx.scene.control.TextArea;
import org.fxmisc.richtext.CodeArea;

import java.io.*;

public class RunCode {
    public void start(CodeArea editor, TextArea output, File workingDirectory) {
        try {
            File mainFile = new File(workingDirectory, "okavango_temp.zum");
            try (FileWriter fw = new FileWriter(mainFile)) {
                fw.write(editor.getText());
            }

            ProcessBuilder pb = new ProcessBuilder("zumbra", "okavango_temp.zum");
            pb.directory(workingDirectory);

            Process proc = pb.start();
            proc.waitFor();

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            StringBuilder result = new StringBuilder();
            String line;

            while ((line = stdOut.readLine()) != null) {
                result.append(line).append("\n");
            }

            while ((line = stdErr.readLine()) != null) {
                result.append("[err] ").append(line).append("\n");
            }

            output.setText(result.toString());
            mainFile.delete();

        } catch (IOException | InterruptedException ex) {
            output.setText("Error trying to execute: " + ex.getMessage());
        }
    }
}

