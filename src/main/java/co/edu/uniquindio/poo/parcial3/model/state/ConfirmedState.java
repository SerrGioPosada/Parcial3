package co.edu.uniquindio.poo.parcial3.model.state;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public class ConfirmedState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setState(new CancelledState());
    }

    @Override
    public void complete(Appointment appointment) {
        appointment.setState(new CompletedState());
    }

    @Override
    public String getStateName() {
        return "Confirmed";
    }
}
