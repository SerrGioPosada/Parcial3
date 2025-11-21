package co.edu.uniquindio.poo.parcial3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexViewController implements Initializable {
    @FXML private BorderPane rootPane;
    @FXML private StackPane centerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSidebar();
        loadView("patients-view.fxml");
    }

    private void loadSidebar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial3/sidebar.fxml"));
            Parent sidebar = loader.load();
            SidebarViewController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setIndexController(this);
            }
            rootPane.setLeft(sidebar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/parcial3/" + fxmlFile));
            Parent view = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
