package co.edu.uniquindio.poo.parcial3.model.decorator;

public class PsychologyAppointmentDecorator extends AppointmentDecorator {
    private static final double PSYCHOLOGY_COST = 20000.0;

    public PsychologyAppointmentDecorator(AppointmentComponent appointmentComponent) {
        super(appointmentComponent);
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice() + PSYCHOLOGY_COST;
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription() + " + Psychology";
    }
}
