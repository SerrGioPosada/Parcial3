package co.edu.uniquindio.poo.parcial3.model.decorator;

import co.edu.uniquindio.poo.parcial3.model.AppointmentType;

public class AppointmentDecoratorFactory {
    public static AppointmentComponent createAppointment(AppointmentType type, double basePrice) {
        AppointmentComponent appointment = new BaseAppointment(basePrice);

        return switch (type) {
            case GENERAL -> new GeneralAppointmentDecorator(appointment);
            case PSYCHOLOGY -> new PsychologyAppointmentDecorator(appointment);
            case CARDIOLOGY -> new CardiologyAppointmentDecorator(appointment);
            case DERMATOLOGY -> new DermatologyAppointmentDecorator(appointment);
            case PEDIATRICS -> new PediatricsAppointmentDecorator(appointment);
            case ORTHOPEDICS -> new OrthopedicsAppointmentDecorator(appointment);
        };
    }
}
