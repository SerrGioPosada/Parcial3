package co.edu.uniquindio.poo.parcial3.model.decorator;

public class CardiologyAppointmentDecorator extends AppointmentDecorator {
    private static final double CARDIOLOGY_COST = 50000.0;

    public CardiologyAppointmentDecorator(AppointmentComponent appointmentComponent) {
        super(appointmentComponent);
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice() + CARDIOLOGY_COST;
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription() + " + Cardiology";
    }
}
