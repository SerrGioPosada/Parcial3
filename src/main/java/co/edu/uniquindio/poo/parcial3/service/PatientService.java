package co.edu.uniquindio.poo.parcial3.service;

import co.edu.uniquindio.poo.parcial3.model.Patient;
import java.util.LinkedList;
import java.util.Optional;

public class PatientService {
    private LinkedList<Patient> patients;

    public PatientService() {
        this.patients = new LinkedList<>();
    }

    public boolean addPatient(Patient patient) {
        if (findPatient(patient.getId()).isEmpty()) {
            patients.add(patient);
            return true;
        }
        return false;
    }

    public Optional<Patient> findPatient(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public boolean updatePatient(Patient patient) {
        Optional<Patient> existing = findPatient(patient.getId());
        if (existing.isPresent()) {
            patients.remove(existing.get());
            patients.add(patient);
            return true;
        }
        return false;
    }

    public boolean deletePatient(String id) {
        Optional<Patient> patient = findPatient(id);
        if (patient.isPresent()) {
            patients.remove(patient.get());
            return true;
        }
        return false;
    }

    public LinkedList<Patient> getAllPatients() {
        return patients;
    }

    public Patient getPatientById(String id) {
        return findPatient(id).orElse(null);
    }
}
