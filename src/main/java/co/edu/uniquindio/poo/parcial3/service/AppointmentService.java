package co.edu.uniquindio.poo.parcial3.service;

import co.edu.uniquindio.poo.parcial3.model.Appointment;
import java.util.LinkedList;
import java.util.Optional;

public class AppointmentService {
    private LinkedList<Appointment> appointments;

    public AppointmentService() {
        this.appointments = new LinkedList<>();
    }

    public boolean addAppointment(Appointment appointment) {
        if (findAppointment(appointment.getId()).isEmpty()) {
            appointments.add(appointment);
            appointment.getPatient().addAppointment(appointment);
            appointment.getDoctor().addAppointment(appointment);
            return true;
        }
        return false;
    }

    public Optional<Appointment> findAppointment(String id) {
        return appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public boolean deleteAppointment(String id) {
        Optional<Appointment> appointment = findAppointment(id);
        if (appointment.isPresent()) {
            appointments.remove(appointment.get());
            return true;
        }
        return false;
    }

    public LinkedList<Appointment> getAllAppointments() {
        return appointments;
    }
}
