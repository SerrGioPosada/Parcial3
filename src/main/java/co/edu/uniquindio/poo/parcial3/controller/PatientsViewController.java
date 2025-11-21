package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.*;
import co.edu.uniquindio.poo.parcial3.service.AvailableAppointmentService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

public class PatientsViewController {
    @FXML private ComboBox<Patient> patientComboBox;
    @FXML private ChoiceBox<AppointmentType> appointmentTypeChoiceBox;
    @FXML private DatePicker datePicker;
    @FXML private Spinner<Integer> startHourSpinner;
    @FXML private Spinner<Integer> startMinuteSpinner;
    @FXML private Spinner<Integer> endHourSpinner;
    @FXML private Spinner<Integer> endMinuteSpinner;
    @FXML private TableView<AvailableAppointment> availableAppointmentsTable;
    @FXML private TableColumn<AvailableAppointment, String> doctorColumn;
    @FXML private TableColumn<AvailableAppointment, String> specialtyColumn;
    @FXML private TableColumn<AvailableAppointment, LocalDate> dateColumn;
    @FXML private TableColumn<AvailableAppointment, LocalTime> timeColumn;
    @FXML private TableColumn<AvailableAppointment, Double> priceColumn;
    @FXML private Button agendarButton;

    private Clinic clinic;

    @FXML
    public void initialize() {
        System.out.println("[DEBUG] Inicializando PatientsViewController");
        clinic = Clinic.getInstance();
        System.out.println("[DEBUG] Clínica cargada: " + clinic.getName());
        setupTableColumns();
        setupTimeSpinners();
        loadPatients();
        loadAppointmentTypes();
        setupAgendarButtonListener();
        System.out.println("[DEBUG] PatientsViewController inicializado correctamente");
    }

    private void setupTableColumns() {
        System.out.println("[DEBUG] Configurando columnas de la tabla de pacientes");

        doctorColumn.setCellValueFactory(cellData -> {
            String doctorName = cellData.getValue().getDoctorName();
            System.out.println("[DEBUG] Doctor: " + doctorName);
            return new javafx.beans.property.SimpleStringProperty(doctorName);
        });
        doctorColumn.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<AvailableAppointment, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                        System.out.println("[DEBUG] Celda Doctor actualizada: " + item);
                    }
                }
            };
        });

        specialtyColumn.setCellValueFactory(cellData -> {
            String specialty = cellData.getValue().getSpecialty();
            return new javafx.beans.property.SimpleStringProperty(specialty);
        });
        specialtyColumn.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<AvailableAppointment, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item);
                }
            };
        });

        dateColumn.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getDate();
            return new javafx.beans.property.SimpleObjectProperty<>(date);
        });
        dateColumn.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<AvailableAppointment, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.toString());
                }
            };
        });

        timeColumn.setCellValueFactory(cellData -> {
            LocalTime time = cellData.getValue().getTime();
            return new javafx.beans.property.SimpleObjectProperty<>(time);
        });
        timeColumn.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<AvailableAppointment, LocalTime>() {
                @Override
                protected void updateItem(LocalTime item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.toString());
                }
            };
        });

        priceColumn.setCellValueFactory(cellData -> {
            Double price = cellData.getValue().getPrice();
            return new javafx.beans.property.SimpleObjectProperty<>(price);
        });
        priceColumn.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<AvailableAppointment, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : String.format("$%.0f", item));
                }
            };
        });

        System.out.println("[DEBUG] Columnas configuradas correctamente");
    }

    private void setupTimeSpinners() {
        System.out.println("[DEBUG] Configurando Spinners de tiempo");

        SpinnerValueFactory<Integer> startHourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8, 1);
        startHourSpinner.setValueFactory(startHourFactory);
        startHourSpinner.setEditable(false);

        SpinnerValueFactory<Integer> startMinuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15);
        startMinuteSpinner.setValueFactory(startMinuteFactory);
        startMinuteSpinner.setEditable(false);

        SpinnerValueFactory<Integer> endHourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 17, 1);
        endHourSpinner.setValueFactory(endHourFactory);
        endHourSpinner.setEditable(false);

        SpinnerValueFactory<Integer> endMinuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15);
        endMinuteSpinner.setValueFactory(endMinuteFactory);
        endMinuteSpinner.setEditable(false);

        startHourFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            startHourSpinner.getEditor().setText(String.format("%02d", newVal));
        });

        startMinuteFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            startMinuteSpinner.getEditor().setText(String.format("%02d", newVal));
        });

        endHourFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            endHourSpinner.getEditor().setText(String.format("%02d", newVal));
        });

        endMinuteFactory.valueProperty().addListener((obs, oldVal, newVal) -> {
            endMinuteSpinner.getEditor().setText(String.format("%02d", newVal));
        });

        startHourSpinner.getEditor().setText(String.format("%02d", startHourSpinner.getValue()));
        startMinuteSpinner.getEditor().setText(String.format("%02d", startMinuteSpinner.getValue()));
        endHourSpinner.getEditor().setText(String.format("%02d", endHourSpinner.getValue()));
        endMinuteSpinner.getEditor().setText(String.format("%02d", endMinuteSpinner.getValue()));

        System.out.println("[DEBUG] Spinners configurados correctamente");
    }

    private void loadPatients() {
        System.out.println("[DEBUG] Cargando pacientes en ComboBox");
        List<Patient> patients = clinic.getPatientService().getAllPatients();
        System.out.println("[DEBUG] Total de pacientes: " + patients.size());

        patientComboBox.setItems(FXCollections.observableArrayList(patients));
        patientComboBox.setConverter(new javafx.util.StringConverter<Patient>() {
            @Override
            public String toString(Patient patient) {
                return patient != null ? patient.getName() : "";
            }

            @Override
            public Patient fromString(String string) {
                return null;
            }
        });

        System.out.println("[DEBUG] Pacientes cargados correctamente");
    }

    private void loadAppointmentTypes() {
        System.out.println("[DEBUG] Cargando tipos de cita en ChoiceBox");
        appointmentTypeChoiceBox.setItems(FXCollections.observableArrayList(AppointmentType.values()));
        appointmentTypeChoiceBox.setValue(AppointmentType.GENERAL);
        System.out.println("[DEBUG] Tipos de cita cargados correctamente");
    }

    private void setupAgendarButtonListener() {
        System.out.println("[DEBUG] Configurando listener del botón Agendar");
        availableAppointmentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                agendarButton.setDisable(false);
                System.out.println("[DEBUG] Cita seleccionada - Botón Agendar habilitado");
            } else {
                agendarButton.setDisable(true);
                System.out.println("[DEBUG] Ninguna cita seleccionada - Botón Agendar deshabilitado");
            }
        });
        System.out.println("[DEBUG] Listener del botón Agendar configurado correctamente");
    }

    @FXML
    private void handleLoadAvailableAppointments() {
        System.out.println("\n[DEBUG] ===== INICIANDO CARGA DE CITAS DISPONIBLES =====");

        Patient selected = patientComboBox.getValue();
        if (selected == null) {
            System.err.println("[ERROR] No se ha seleccionado ningún paciente");
            showAlert("Advertencia", "Por favor seleccione un paciente", Alert.AlertType.WARNING);
            return;
        }
        System.out.println("[DEBUG] Paciente seleccionado: " + selected.getName());

        AppointmentType selectedType = appointmentTypeChoiceBox.getValue();
        if (selectedType == null) {
            System.err.println("[ERROR] No se ha seleccionado ningún tipo de cita");
            showAlert("Advertencia", "Por favor seleccione un tipo de cita", Alert.AlertType.WARNING);
            return;
        }
        System.out.println("[DEBUG] Tipo de cita seleccionado: " + selectedType.getDisplayName());

        LocalDate filterDate = datePicker.getValue();
        Integer startHour = startHourSpinner.getValue();
        Integer startMinute = startMinuteSpinner.getValue();
        Integer endHour = endHourSpinner.getValue();
        Integer endMinute = endMinuteSpinner.getValue();

        System.out.println("[DEBUG] Filtros:");
        System.out.println("  - Fecha: " + filterDate);
        System.out.println("  - Hora inicio: " + startHour + ":" + startMinute);
        System.out.println("  - Hora fin: " + endHour + ":" + endMinute);

        if (filterDate == null) {
            System.err.println("[ERROR] No se especificó una fecha");
            showAlert("Advertencia", "Debe especificar una fecha para buscar citas", Alert.AlertType.WARNING);
            return;
        }

        try {
            LocalTime filterStartTime = LocalTime.of(startHour, startMinute);
            LocalTime filterEndTime = LocalTime.of(endHour, endMinute);

            System.out.println("[DEBUG] Hora inicio parseada: " + filterStartTime);
            System.out.println("[DEBUG] Hora fin parseada: " + filterEndTime);

            if (filterStartTime.isAfter(filterEndTime) || filterStartTime.equals(filterEndTime)) {
                System.err.println("[ERROR] Hora inicio es posterior o igual a hora fin");
                showAlert("Error", "La hora de inicio debe ser anterior a la hora de fin", Alert.AlertType.ERROR);
                return;
            }

            System.out.println("[DEBUG] Generando citas disponibles...");
            AvailableAppointmentService service = clinic.getAvailableAppointmentService();
            List<AvailableAppointment> available = service.generateAvailableAppointments(selectedType);
            System.out.println("[DEBUG] Total de citas generadas: " + available.size());

            final LocalTime finalStartTime = filterStartTime;
            final LocalTime finalEndTime = filterEndTime;

            System.out.println("[DEBUG] Filtrando citas...");
            List<AvailableAppointment> filtered = available.stream()
                .filter(apt -> {
                    if (filterDate != null && !apt.getDate().equals(filterDate)) {
                        return false;
                    }
                    if (finalStartTime != null && apt.getTime().isBefore(finalStartTime)) {
                        return false;
                    }
                    if (finalEndTime != null && apt.getTime().isAfter(finalEndTime)) {
                        return false;
                    }
                    return true;
                })
                .toList();

            System.out.println("[DEBUG] Citas después del filtro: " + filtered.size());

            if (!filtered.isEmpty()) {
                System.out.println("[DEBUG] Primeras 3 citas filtradas:");
                for (int i = 0; i < Math.min(3, filtered.size()); i++) {
                    AvailableAppointment apt = filtered.get(i);
                    System.out.println("  " + (i+1) + ". Doctor: " + apt.getDoctorName() +
                                     ", Fecha: " + apt.getDate() +
                                     ", Hora: " + apt.getTime() +
                                     ", Tipo: " + apt.getAppointmentType().getDisplayName() +
                                     ", Precio: " + apt.getPrice());
                }
            }

            System.out.println("[DEBUG] Cargando datos en la tabla...");
            availableAppointmentsTable.setItems(FXCollections.observableArrayList(filtered));
            System.out.println("[DEBUG] Items en tabla: " + availableAppointmentsTable.getItems().size());

            availableAppointmentsTable.refresh();
            System.out.println("[DEBUG] Tabla refrescada");

            if (filtered.isEmpty()) {
                System.out.println("[INFO] No hay citas disponibles con los filtros");
                showAlert("Información", "No hay citas disponibles con los filtros seleccionados", Alert.AlertType.INFORMATION);
            } else {
                System.out.println("[SUCCESS] Se cargaron " + filtered.size() + " citas en la tabla");
            }

            System.out.println("[DEBUG] ===== FIN CARGA DE CITAS =====\n");
        } catch (Exception e) {
            System.err.println("[ERROR CRÍTICO] Error al cargar citas:");
            e.printStackTrace();
            showAlert("Error", "Error al cargar citas: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleScheduleAppointment() {
        Patient patient = patientComboBox.getValue();
        AvailableAppointment selected = availableAppointmentsTable.getSelectionModel().getSelectedItem();

        if (patient == null) {
            showAlert("Advertencia", "Por favor seleccione un paciente", Alert.AlertType.WARNING);
            return;
        }

        if (selected == null) {
            showAlert("Advertencia", "Por favor seleccione una cita", Alert.AlertType.WARNING);
            return;
        }

        String appointmentId = "APT-" + UUID.randomUUID().toString().substring(0, 8);
        Appointment appointment = new Appointment.Builder()
            .withId(appointmentId)
            .withPatient(patient)
            .withDoctor(selected.getDoctor())
            .withDate(selected.getDate())
            .withTime(selected.getTime())
            .withPrice(selected.getPrice())
            .withAppointmentType(selected.getAppointmentType())
            .build();

        if (clinic.getAppointmentService().addAppointment(appointment)) {
            showAlert("Éxito", "Cita agendada exitosamente", Alert.AlertType.INFORMATION);
            handleLoadAvailableAppointments();
        } else {
            showAlert("Error", "No se pudo agendar la cita", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
