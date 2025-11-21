package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.Appointment;
import co.edu.uniquindio.poo.parcial3.model.Clinic;
import co.edu.uniquindio.poo.parcial3.model.Doctor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DoctorsViewController {
    @FXML private ComboBox<Doctor> doctorComboBox;
    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment, String> idColumn;
    @FXML private TableColumn<Appointment, String> patientColumn;
    @FXML private TableColumn<Appointment, LocalDate> dateColumn;
    @FXML private TableColumn<Appointment, LocalTime> timeColumn;
    @FXML private TableColumn<Appointment, Double> priceColumn;
    @FXML private TableColumn<Appointment, String> stateColumn;

    private Clinic clinic;

    @FXML
    public void initialize() {
        clinic = Clinic.getInstance();
        setupTableColumns();
        loadDoctors();
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getId()));

        patientColumn.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPatient().getName()));

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

    private void loadDoctors() {
        doctorComboBox.setItems(FXCollections.observableArrayList(
            clinic.getDoctorService().getAllDoctors()
        ));
        doctorComboBox.setConverter(new javafx.util.StringConverter<Doctor>() {
            @Override
            public String toString(Doctor doctor) {
                return doctor != null ? doctor.getName() + " - " + doctor.getSpecialty() : "";
            }

            @Override
            public Doctor fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleLoadAppointments() {
        System.out.println("\n[DEBUG] ===== CARGANDO CITAS DEL DOCTOR =====");

        Doctor selected = doctorComboBox.getValue();
        if (selected == null) {
            System.err.println("[ERROR] No se ha seleccionado ningún doctor");
            showAlert("Advertencia", "Por favor seleccione un doctor", Alert.AlertType.WARNING);
            return;
        }

        System.out.println("[DEBUG] Doctor seleccionado: " + selected.getName());
        List<Appointment> doctorAppointments = selected.getAppointments();
        System.out.println("[DEBUG] Número de citas del doctor: " + doctorAppointments.size());

        if (!doctorAppointments.isEmpty()) {
            System.out.println("[DEBUG] Primeras citas:");
            for (int i = 0; i < Math.min(3, doctorAppointments.size()); i++) {
                Appointment apt = doctorAppointments.get(i);
                System.out.println("  " + (i+1) + ". Paciente: " + apt.getPatient().getName() +
                                 ", Fecha: " + apt.getDate() +
                                 ", Hora: " + apt.getTime() +
                                 ", Estado: " + apt.getStateName());
            }
        }

        appointmentsTable.setItems(FXCollections.observableArrayList(doctorAppointments));
        appointmentsTable.refresh();
        System.out.println("[DEBUG] Items cargados en tabla: " + appointmentsTable.getItems().size());

        if (doctorAppointments.isEmpty()) {
            System.out.println("[INFO] No hay citas agendadas");
            showAlert("Información", "No hay citas agendadas para este doctor", Alert.AlertType.INFORMATION);
        } else {
            System.out.println("[SUCCESS] Citas cargadas correctamente");
        }

        System.out.println("[DEBUG] ===== FIN CARGA CITAS DOCTOR =====\n");
    }

    @FXML
    private void handleStartAppointment() {
        Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Advertencia", "Por favor seleccione una cita", Alert.AlertType.WARNING);
            return;
        }

        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/poo/parcial3/appointment-details-dialog.fxml"));
            javafx.scene.layout.VBox page = loader.load();

            javafx.stage.Stage dialogStage = new javafx.stage.Stage();
            dialogStage.setTitle("Iniciar Cita - " + selected.getPatient().getName());
            dialogStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
            dialogStage.initOwner(appointmentsTable.getScene().getWindow());
            javafx.scene.Scene scene = new javafx.scene.Scene(page);
            dialogStage.setScene(scene);

            AppointmentDetailsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAppointment(selected);

            selected.confirm();

            dialogStage.showAndWait();

            if (controller.isFinished()) {
                appointmentsTable.refresh();
                showAlert("Éxito", "Cita completada exitosamente", Alert.AlertType.INFORMATION);
            } else {
                appointmentsTable.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir la ventana de detalles: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCompleteAppointment() {
        Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Advertencia", "Por favor seleccione una cita", Alert.AlertType.WARNING);
            return;
        }

        selected.complete();
        appointmentsTable.refresh();
        showAlert("Éxito", "Cita completada", Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
