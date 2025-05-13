package main.java.com.joselucasapp.okavangoide;

import javafx.scene.control.TextArea;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Files;

public class SelectFile {
    public void start(Stage stage, VBox lateralMenu, TextArea editor) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Zumbra folder");

        File rootFolder = directoryChooser.showDialog(stage);
        if (rootFolder != null && rootFolder.isDirectory()) {
            TreeItem<File> rootItem = createNode(rootFolder);
            TreeView<File> treeView = new TreeView<>(rootItem);

            treeView.setCellFactory(tv -> new TreeCell<>(){
                @Override
                protected void updateItem(File file, boolean empty){
                    super.updateItem(file, empty);

                    if(empty || file == null){
                        setText(null);
                    }else{
                        setText(file.getName());
                    }
                }
            });

            treeView.setOnMouseClicked(event ->{
                TreeItem<File> selected = treeView.getSelectionModel().getSelectedItem();
                if (selected != null && selected.getValue().isFile()){
                    try{
                        String content = Files.readString(selected.getValue().toPath());
                        editor.setText(content);
                    }catch (Exception ex){
                        System.out.println("Failed to load:"+ ex.getMessage());
                    }
                }
            });

            lateralMenu.getChildren().clear();
            lateralMenu.getChildren().add(treeView);
        }
    }

    private TreeItem<File> createNode (final File f){
        TreeItem<File> node = new TreeItem<>(f);

        if(f.isDirectory()){
            File[] files = f.listFiles();
            if (files != null){
                for(File file : files){
                    node.getChildren().add(createNode(file));
                }
            }
        }

        return node;
    }
}

