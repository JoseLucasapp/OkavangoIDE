package com.joselucasapp.okavangoide;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class SelectFile {
    private TreeView<File> treeView = new TreeView<>();

    public void start(Stage stage, StackPane lateralMenu, TabPane tabPane, Editor editor) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Zumbra folder");

        File rootFolder = directoryChooser.showDialog(stage);
        if (rootFolder != null && rootFolder.isDirectory()) {
            TreeItem<File> rootItem = createNode(rootFolder);
            this.treeView = new TreeView<>(rootItem);
            this.treeView.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/highlight.css")).toExternalForm());
            this.treeView.setStyle(
                    "-fx-background-color: #130B28;" +
                            "-fx-control-inner-background: #130B28;" +
                            "-fx-font-family: 'Fira Code';" +
                            "-fx-text-fill: #f8f8f2;"
            );
            treeView.setMaxHeight(Double.MAX_VALUE);
            treeView.setPrefHeight(Double.MAX_VALUE);
            treeView.setMinHeight(0);


            this.treeView.setCellFactory(tv -> new TreeCell<>(){
                @Override
                protected void updateItem(File file, boolean empty){
                    super.updateItem(file, empty);

                    if(empty || file == null){
                        setText(null);
                        setContextMenu(null);
                    }else{
                        String icon = file.isDirectory() ? "📁 " : "📄 ";
                        setText(icon + file.getName());

                        setStyle("-fx-background-color: #130B28; -fx-text-fill: #f8f8f2; -fx-font-family: 'Fira Code'; -fx-font-size: 14px;");

                        MenuItem newFileItem = createFileMenuItem(file);
                        MenuItem newFolderItem = createFolderMenuItem(file);
                        MenuItem renameItem = renameMenuItem(file);
                        MenuItem deleteItem = deleteItem(file);

                        ContextMenu contextMenu = new ContextMenu();
                        contextMenu.setStyle("-fx-background-color: #1a102d; -fx-border-color: #44475a;");

                        if(file.isDirectory()){
                            contextMenu.getItems().addAll(newFileItem, newFolderItem, renameItem, deleteItem);
                        }else{
                            contextMenu.getItems().addAll(renameItem, deleteItem);
                        }

                        setContextMenu(contextMenu);
                    }
                }

                private MenuItem createFileMenuItem(File file) {
                    MenuItem newFileItem = new MenuItem("New File");
                    newFileItem.setStyle("-fx-text-fill: #f8f8f2; -fx-font-family: 'Fira Code'; -fx-background-color: transparent;");

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
                    return newFileItem;
                }

                private MenuItem createFolderMenuItem(File file){
                    MenuItem newFolderItem = new MenuItem("New Folder");
                    newFolderItem.setStyle("-fx-text-fill: #f8f8f2; -fx-font-family: 'Fira Code'; -fx-background-color: transparent;");

                    newFolderItem.setOnAction(e->{
                        TextInputDialog dialog = new TextInputDialog("New folder");

                        dialog.setTitle("Create new folder");
                        dialog.setHeaderText("Create new folder on: "+ file.getName());
                        dialog.setContentText("Folder name: ");

                        dialog.showAndWait().ifPresent(folderName->{
                            File newDir = new File(file, folderName);

                            if(newDir.mkdir()){
                                TreeItem<File> newItem = createNode(newDir);
                                getTreeItem().getChildren().add(newItem);
                                getTreeItem().setExpanded(true);
                            }
                        });
                    });

                    return newFolderItem;
                }

                private MenuItem renameMenuItem(File file){
                    MenuItem renameItem = new MenuItem("Rename");
                    renameItem.setStyle("-fx-text-fill: #f8f8f2; -fx-font-family: 'Fira Code'; -fx-background-color: transparent;");

                    renameItem.setOnAction(e->{
                        TextInputDialog dialog = new TextInputDialog("Rename");

                        dialog.setTitle("Rename");
                        dialog.setHeaderText("Rename: "+ file.getName());
                        dialog.setContentText("New title: ");

                        dialog.showAndWait().ifPresent(newName->{
                            File renamed = new File(file.getParentFile(), newName);
                            if(file.renameTo(renamed)){
                                getTreeItem().setValue(renamed);
                                setText(renamed.getName());
                            }
                        });
                    });

                    return renameItem;
                }

                private MenuItem deleteItem(File file){
                    MenuItem deleteItem = new MenuItem("Delete");
                    deleteItem.setStyle("-fx-text-fill: #f8f8f2; -fx-font-family: 'Fira Code'; -fx-background-color: transparent;");

                    deleteItem.setOnAction(e->{
                        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                        confirm.setTitle("Rename");
                        confirm.setHeaderText("Rename: "+ file.getName());
                        confirm.setContentText("New title: ");

                        confirm.showAndWait().ifPresent(response->{
                            if(response == ButtonType.OK){
                                deleteRecursively(file);
                                getTreeItem().getParent().getChildren().remove(getTreeItem());
                            }
                        });
                    });
                    return  deleteItem;
                }


            });

            treeView.setOnMouseClicked(event ->{
                TreeItem<File> selected = this.treeView.getSelectionModel().getSelectedItem();
                if (selected != null && selected.getValue().isFile()){
                    try{
                        String content = Files.readString(selected.getValue().toPath());
                        CodeArea newEditor = editor.start();
                        newEditor.replaceText(content);

                        Tab tab = new Tab(selected.getValue().getName());
                        tab.setContent(newEditor);
                        tab.setUserData(newEditor);

                        tabPane.getTabs().add(tab);

                        tabPane.getSelectionModel().select(tab);
                    }catch (Exception ex){
                        System.out.println("Failed to load:"+ ex.getMessage());
                    }
                }
            });

            treeView.setMaxHeight(Double.MAX_VALUE);
            treeView.setMinHeight(0);
            StackPane.setAlignment(treeView, Pos.TOP_LEFT);
            StackPane.setMargin(treeView, new Insets(0));

            lateralMenu.getChildren().clear();
            lateralMenu.getChildren().add(treeView);

            lateralMenu.setStyle("-fx-background-color: #130B28;");

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

    public void saveFile(KeyEvent event, TabPane tabPane){
        if(event.isControlDown() && event.getCode() == KeyCode.S){
            TreeItem<File> selectedItem = this.treeView.getSelectionModel().getSelectedItem();
            if(selectedItem != null && selectedItem.getValue().isFile()){
                Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
                if(selectedTab != null){
                    CodeArea editor = (CodeArea) selectedTab.getUserData();
                    try{
                        Files.writeString(selectedItem.getValue().toPath(), editor.getText());
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }

            }
            event.consume();
        }
    }

    private void deleteRecursively(File file){
        if(file.isDirectory()){
            File[] contents = file.listFiles();
            if(contents != null){
                for (File f : contents){
                    deleteRecursively(f);
                }
            }
        }
        file.delete();
    }
}

