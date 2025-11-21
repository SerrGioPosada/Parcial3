package co.edu.uniquindio.poo.parcial3.model.decorator;

public abstract class AppointmentDecorator implements AppointmentComponent {
    protected AppointmentComponent appointmentComponent;

    public AppointmentDecorator(AppointmentComponent appointmentComponent) {
        this.appointmentComponent = appointmentComponent;
    }

    @Override
    public double getPrice() {
        return appointmentComponent.getPrice();
    }

    @Override
    public String getDescription() {
        return appointmentComponent.getDescription();
    }
}
