package co.edu.uniquindio.poo.parcial3.controller;

import javafx.fxml.FXML;

public class SidebarViewController {
    private IndexViewController indexController;

    public void setIndexController(IndexViewController indexController) {
        this.indexController = indexController;
    }

    @FXML
    private void loadPatients() {
        if (indexController != null) {
            indexController.loadView("patients-view.fxml");
        }
    }

    @FXML
    private void loadDoctors() {
        if (indexController != null) {
            indexController.loadView("doctors-view.fxml");
        }
    }

    @FXML
    private void loadAppointments() {
        if (indexController != null) {
            indexController.loadView("appointments-view.fxml");
        }
    }
}
