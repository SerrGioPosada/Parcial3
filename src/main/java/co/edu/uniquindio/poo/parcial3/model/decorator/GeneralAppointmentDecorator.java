package co.edu.uniquindio.poo.parcial3.model.decorator;

public class GeneralAppointmentDecorator extends AppointmentDecorator {
    private static final double GENERAL_COST = 0.0;

    public GeneralAppointmentDecorator(AppointmentComponent appointmentComponent) {
        super(appointmentComponent);
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice() + GENERAL_COST;
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription() + " + General";
    }
}
