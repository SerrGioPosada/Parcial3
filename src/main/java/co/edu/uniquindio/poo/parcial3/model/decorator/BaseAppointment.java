package co.edu.uniquindio.poo.parcial3.model.decorator;

public class BaseAppointment implements AppointmentComponent {
    private double basePrice;

    public BaseAppointment(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public double getPrice() {
        return basePrice;
    }

    @Override
    public String getDescription() {
        return "Base Appointment";
    }
}
