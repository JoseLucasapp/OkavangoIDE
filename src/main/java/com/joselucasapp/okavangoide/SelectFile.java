package com.joselucasapp.okavangoide;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SelectFile {
    private TreeView<File> treeView = new TreeView<>();

    public void start(Stage stage, VBox lateralMenu, CodeArea editor) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Zumbra folder");

        File rootFolder = directoryChooser.showDialog(stage);
        if (rootFolder != null && rootFolder.isDirectory()) {
            TreeItem<File> rootItem = createNode(rootFolder);
            this.treeView = new TreeView<>(rootItem);

            this.treeView.setCellFactory(tv -> new TreeCell<>(){
                @Override
                protected void updateItem(File file, boolean empty){
                    super.updateItem(file, empty);

                    if(empty || file == null){
                        setText(null);
                        setContextMenu(null);
                    }else{
                        setText(file.getName());

                        MenuItem newFileItem = new MenuItem("New File");

                        newFileItem.setOnAction(e->{
                            TextInputDialog dialog = new TextInputDialog("newFile.zum");
                            dialog.setTitle("Create new File");
                            dialog.setHeaderText("Create new file on: "+ file.getName());
                            dialog.setContentText("File name: ");

                            dialog.showAndWait().ifPresent(fileName ->{
                                File newFile = new File(file, fileName);
                                try{
                                    if(newFile.createNewFile()){
                                        TreeItem<File> newItem = createNode(newFile);
                                        getTreeItem().getChildren().add(newItem);
                                        getTreeItem().setExpanded(true);
                                    }
                                }catch (IOException ex){
                                    ex.printStackTrace();
                                }
                            });
                        });

                        ContextMenu contextMenu = new ContextMenu();

                        if(file.isDirectory()){
                            contextMenu.getItems().addAll(newFileItem);
                        }

                        setContextMenu(contextMenu);
                    }
                }
            });

            treeView.setOnMouseClicked(event ->{
                TreeItem<File> selected = this.treeView.getSelectionModel().getSelectedItem();
                if (selected != null && selected.getValue().isFile()){
                    try{
                        String content = Files.readString(selected.getValue().toPath());
                        editor.replaceText(content);
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

    public void saveFile(KeyEvent event, CodeArea editor){
        if(event.isControlDown() && event.getCode() == KeyCode.S){
            TreeItem<File> selectedItem = this.treeView.getSelectionModel().getSelectedItem();
            if(selectedItem != null && selectedItem.getValue().isFile()){
                try{
                    Files.writeString(selectedItem.getValue().toPath(), editor.getText());
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
            event.consume();
        }
    }
}

