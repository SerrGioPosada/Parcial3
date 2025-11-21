package co.edu.uniquindio.poo.parcial3.model.decorator;

public class PediatricsAppointmentDecorator extends AppointmentDecorator {
    private static final double PEDIATRICS_COST = 25000.0;

    public PediatricsAppointmentDecorator(AppointmentComponent appointmentComponent) {
        super(appointmentComponent);
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice() + PEDIATRICS_COST;
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription() + " + Pediatrics";
    }
}
