package co.edu.uniquindio.poo.parcial3.model;

import co.edu.uniquindio.poo.parcial3.model.decorator.AppointmentComponent;
import co.edu.uniquindio.poo.parcial3.model.decorator.AppointmentDecoratorFactory;
import java.time.LocalDate;
import java.time.LocalTime;

public class AvailableAppointment {
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private double basePrice;
    private AppointmentType appointmentType;

    public AvailableAppointment(Doctor doctor, LocalDate date, LocalTime time, double basePrice, AppointmentType appointmentType) {
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.basePrice = basePrice;
        this.appointmentType = appointmentType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDoctorName() {
        return doctor.getName();
    }

    public String getSpecialty() {
        return doctor.getSpecialty();
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getPrice() {
        AppointmentComponent decoratedAppointment = AppointmentDecoratorFactory.createAppointment(appointmentType, basePrice);
        return decoratedAppointment.getPrice();
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
}
