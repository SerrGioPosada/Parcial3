package co.edu.uniquindio.poo.parcial3.model.decorator;

public class OrthopedicsAppointmentDecorator extends AppointmentDecorator {
    private static final double ORTHOPEDICS_COST = 40000.0;

    public OrthopedicsAppointmentDecorator(AppointmentComponent appointmentComponent) {
        super(appointmentComponent);
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice() + ORTHOPEDICS_COST;
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription() + " + Orthopedics";
    }
}
