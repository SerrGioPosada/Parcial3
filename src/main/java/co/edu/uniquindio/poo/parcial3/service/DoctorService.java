package co.edu.uniquindio.poo.parcial3.service;

import co.edu.uniquindio.poo.parcial3.model.Doctor;
import java.util.LinkedList;
import java.util.Optional;

public class DoctorService {
    private LinkedList<Doctor> doctors;

    public DoctorService() {
        this.doctors = new LinkedList<>();
    }

    public boolean addDoctor(Doctor doctor) {
        if (findDoctor(doctor.getId()).isEmpty()) {
            doctors.add(doctor);
            return true;
        }
        return false;
    }

    public Optional<Doctor> findDoctor(String id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public boolean updateDoctor(Doctor doctor) {
        Optional<Doctor> existing = findDoctor(doctor.getId());
        if (existing.isPresent()) {
            doctors.remove(existing.get());
            doctors.add(doctor);
            return true;
        }
        return false;
    }

    public boolean deleteDoctor(String id) {
        Optional<Doctor> doctor = findDoctor(id);
        if (doctor.isPresent()) {
            doctors.remove(doctor.get());
            return true;
        }
        return false;
    }

    public LinkedList<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(String id) {
        return findDoctor(id).orElse(null);
    }
}
