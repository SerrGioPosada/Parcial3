package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AppointmentDetailsDialogController {
    @FXML private Label patientLabel;
    @FXML private Label doctorLabel;
    @FXML private Label dateLabel;
    @FXML private Label timeLabel;
    @FXML private Label priceLabel;
    @FXML private Label typeLabel;
    @FXML private TextArea diagnosticoField;
    @FXML private TextArea tratamientoField;
    @FXML private TextArea observacionesField;

    private Appointment appointment;
    private Stage dialogStage;
    private boolean finished = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        loadAppointmentData();
    }

    private void loadAppointmentData() {
        if (appointment != null) {
            patientLabel.setText(appointment.getPatient().getName());
            doctorLabel.setText(appointment.getDoctor().getName());
            dateLabel.setText(appointment.getDate().toString());
            timeLabel.setText(appointment.getTime().toString());
            priceLabel.setText(String.format("$%.0f", appointment.getPrice()));
            typeLabel.setText(appointment.getAppointmentType() != null ?
                appointment.getAppointmentType().getDisplayName() : "N/A");
        }
    }

    @FXML
    private void handleFinish() {
        String diagnostico = diagnosticoField.getText().trim();
        String tratamiento = tratamientoField.getText().trim();
        String observaciones = observacionesField.getText().trim();

        if (diagnostico.isEmpty()) {
            showAlert("Advertencia", "Por favor ingrese el diagnóstico");
            return;
        }

        if (tratamiento.isEmpty()) {
            showAlert("Advertencia", "Por favor ingrese el tratamiento");
            return;
        }

        System.out.println("[DEBUG] Cita finalizada:");
        System.out.println("  - Diagnóstico: " + diagnostico);
        System.out.println("  - Tratamiento: " + tratamiento);
        System.out.println("  - Observaciones: " + observaciones);

        appointment.complete();
        finished = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isFinished() {
        return finished;
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
