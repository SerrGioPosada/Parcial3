package co.edu.uniquindio.poo.parcial3.model;

import co.edu.uniquindio.poo.parcial3.model.state.AppointmentState;
import co.edu.uniquindio.poo.parcial3.model.state.ScheduledState;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private double price;
    private AppointmentState state;
    private AppointmentType appointmentType;

    private Appointment(Builder builder) {
        this.id = builder.id;
        this.patient = builder.patient;
        this.doctor = builder.doctor;
        this.date = builder.date;
        this.time = builder.time;
        this.price = builder.price;
        this.state = new ScheduledState();
        this.appointmentType = builder.appointmentType;
    }

    public static class Builder {
        private String id;
        private Patient patient;
        private Doctor doctor;
        private LocalDate date;
        private LocalTime time;
        private double price;
        private AppointmentType appointmentType;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder withDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withTime(LocalTime time) {
            this.time = time;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder withAppointmentType(AppointmentType appointmentType) {
            this.appointmentType = appointmentType;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }

    public void confirm() {
        state.confirm(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void complete() {
        state.complete(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }

    public String getStateName() {
        return state.getStateName();
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }
}