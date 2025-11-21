package co.edu.uniquindio.poo.parcial3.model.state;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public class CancelledState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
    }

    @Override
    public void cancel(Appointment appointment) {
    }

    @Override
    public void complete(Appointment appointment) {
    }

    @Override
    public String getStateName() {
        return "Cancelled";
    }
}
