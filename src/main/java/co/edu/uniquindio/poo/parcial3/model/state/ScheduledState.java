package co.edu.uniquindio.poo.parcial3.model.state;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public class ScheduledState implements AppointmentState {
    @Override
    public void confirm(Appointment appointment) {
        appointment.setState(new ConfirmedState());
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setState(new CancelledState());
    }

    @Override
    public void complete(Appointment appointment) {
    }

    @Override
    public String getStateName() {
        return "Scheduled";
    }
}
