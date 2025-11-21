package co.edu.uniquindio.poo.parcial3.model.decorator;

public class DermatologyAppointmentDecorator extends AppointmentDecorator {
    private static final double DERMATOLOGY_COST = 35000.0;

    public DermatologyAppointmentDecorator(AppointmentComponent appointmentComponent) {
        super(appointmentComponent);
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice() + DERMATOLOGY_COST;
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription() + " + Dermatology";
    }
}
