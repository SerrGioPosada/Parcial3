package co.edu.uniquindio.poo.parcial3.model;

import co.edu.uniquindio.poo.parcial3.service.AppointmentService;
import co.edu.uniquindio.poo.parcial3.service.AvailableAppointmentService;
import co.edu.uniquindio.poo.parcial3.service.DoctorService;
import co.edu.uniquindio.poo.parcial3.service.PatientService;
import java.time.LocalDate;

public class Clinic {
    private static Clinic instance;
    private String name;
    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private AvailableAppointmentService availableAppointmentService;
    private ClinicConfig config;

    private Clinic() {
        this.config = ClinicConfig.getInstance();
        this.name = config.getClinicName();
        this.patientService = new PatientService();
        this.doctorService = new DoctorService();
        this.appointmentService = new AppointmentService();
        this.availableAppointmentService = new AvailableAppointmentService(doctorService);
        loadInitialData();
    }

    public static Clinic getInstance() {
        if (instance == null) {
            instance = new Clinic();
        }
        return instance;
    }

    private void loadInitialData() {
        Patient p1 = new Patient.Builder()
                .withId("P001")
                .withName("Juan Perez")
                .withPhone("3001234567")
                .withEmail("juan@email.com")
                .withBirthDate(LocalDate.of(1990, 5, 15))
                .withAddress("Calle 10 #20-30")
                .build();

        Patient p2 = new Patient.Builder()
                .withId("P002")
                .withName("Maria Garcia")
                .withPhone("3009876543")
                .withEmail("maria@email.com")
                .withBirthDate(LocalDate.of(1985, 8, 20))
                .withAddress("Carrera 5 #15-25")
                .build();

        Doctor d1 = new Doctor.Builder()
                .withId("D001")
                .withName("Dr. Carlos Lopez")
                .withPhone("3101234567")
                .withEmail("carlos@clinic.com")
                .withSpecialty("Cardiology")
                .withLicenseNumber("LIC-001")
                .build();

        Doctor d2 = new Doctor.Builder()
                .withId("D002")
                .withName("Dra. Ana Martinez")
                .withPhone("3109876543")
                .withEmail("ana@clinic.com")
                .withSpecialty("Pediatrics")
                .withLicenseNumber("LIC-002")
                .build();

        Doctor d3 = new Doctor.Builder()
                .withId("D003")
                .withName("Dr. Roberto Silva")
                .withPhone("3201234567")
                .withEmail("roberto@clinic.com")
                .withSpecialty("General Medicine")
                .withLicenseNumber("LIC-003")
                .build();

        patientService.addPatient(p1);
        patientService.addPatient(p2);
        doctorService.addDoctor(d1);
        doctorService.addDoctor(d2);
        doctorService.addDoctor(d3);
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public AvailableAppointmentService getAvailableAppointmentService() {
        return availableAppointmentService;
    }

    public String getName() {
        return name;
    }
}
