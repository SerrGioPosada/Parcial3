package co.edu.uniquindio.poo.parcial3.model.state;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public interface AppointmentState {
    void confirm(Appointment appointment);
    void cancel(Appointment appointment);
    void complete(Appointment appointment);
    String getStateName();
}