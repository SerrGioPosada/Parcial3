package co.edu.uniquindio.poo.parcial3.model.adapter;

import co.edu.uniquindio.poo.parcial3.model.*;
import co.edu.uniquindio.poo.parcial3.service.DoctorService;
import co.edu.uniquindio.poo.parcial3.service.PatientService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentAdapter {
    private PatientService patientService;
    private DoctorService doctorService;

    public AppointmentAdapter(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public Appointment adaptExternalAppointment(ExternalAppointmentData externalData) {
        Patient patient = patientService.getPatientById(externalData.getPatientId());
        Doctor doctor = doctorService.getDoctorById(externalData.getDoctorId());

        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Invalid patient or doctor ID");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate date = LocalDate.parse(externalData.getAppointmentDate(), dateFormatter);
        LocalTime time = LocalTime.parse(externalData.getAppointmentTime(), timeFormatter);

        AppointmentType appointmentType = mapCategoryToType(externalData.getCategory());

        return new Appointment.Builder()
                .withId(externalData.getAppointmentCode())
                .withPatient(patient)
                .withDoctor(doctor)
                .withDate(date)
                .withTime(time)
                .withPrice(externalData.getCost())
                .withAppointmentType(appointmentType)
                .build();
    }

    private AppointmentType mapCategoryToType(String category) {
        return switch (category.toUpperCase()) {
            case "GEN", "GENERAL" -> AppointmentType.GENERAL;
            case "PSY", "PSYCHOLOGY" -> AppointmentType.PSYCHOLOGY;
            case "CAR", "CARDIOLOGY" -> AppointmentType.CARDIOLOGY;
            case "DER", "DERMATOLOGY" -> AppointmentType.DERMATOLOGY;
            case "PED", "PEDIATRICS" -> AppointmentType.PEDIATRICS;
            case "ORT", "ORTHOPEDICS" -> AppointmentType.ORTHOPEDICS;
            default -> AppointmentType.GENERAL;
        };
    }
}
