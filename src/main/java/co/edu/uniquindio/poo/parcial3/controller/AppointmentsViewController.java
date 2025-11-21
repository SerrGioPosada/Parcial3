package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class AppointmentsViewController {
    @FXML private TextField searchField;
    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment, String> idColumn;
    @FXML private TableColumn<Appointment, String> patientColumn;
    @FXML private TableColumn<Appointment, String> doctorColumn;
    @FXML private TableColumn<Appointment, LocalDate> dateColumn;
    @FXML private TableColumn<Appointment, LocalTime> timeColumn;
    @FXML private TableColumn<Appointment, Double> priceColumn;
    @FXML private TableColumn<Appointment, String> stateColumn;

    private Clinic clinic;

    @FXML
    public void initialize() {
        clinic = Clinic.getInstance();
        setupTableColumns();
        loadAppointments();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));

        patientColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPatient().getName()));

        doctorColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDoctor().getName()));

        dateColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getDate()));

        timeColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTime()));

        priceColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPrice()));
        priceColumn.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<Appointment, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : String.format("$%.0f", item));
                }
            };
        });

        stateColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStateName()));
    }

    private void loadAppointments() {
        appointmentsTable.setItems(FXCollections.observableArrayList(
            clinic.getAppointmentService().getAllAppointments()));
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().toLowerCase();
        if (query.isEmpty()) {
            loadAppointments();
            return;
        }
        appointmentsTable.setItems(FXCollections.observableArrayList(
            clinic.getAppointmentService().getAllAppointments().stream()
                .filter(a -> a.getId().toLowerCase().contains(query) ||
                           a.getPatient().getName().toLowerCase().contains(query))
                .toList()
        ));
    }

    @FXML
    private void handleConfirm() {
        Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Advertencia", "Seleccione una cita", Alert.AlertType.WARNING);
            return;
        }
        selected.confirm();
        appointmentsTable.refresh();
        showAlert("Éxito", "Cita confirmada", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleComplete() {
        Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Advertencia", "Seleccione una cita", Alert.AlertType.WARNING);
            return;
        }
        selected.complete();
        appointmentsTable.refresh();
        showAlert("Éxito", "Cita completada", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleCancel() {
        Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Advertencia", "Seleccione una cita", Alert.AlertType.WARNING);
            return;
        }
        selected.cancel();
        appointmentsTable.refresh();
        showAlert("Éxito", "Cita cancelada", Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
