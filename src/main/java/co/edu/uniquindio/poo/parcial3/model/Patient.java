package co.edu.uniquindio.poo.parcial3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private LocalDate birthDate;
    private String address;
    private List<Appointment> appointments;

    private Patient(Builder builder) {
        super(builder);
        this.birthDate = builder.birthDate;
        this.address = builder.address;
        this.appointments = new ArrayList<>();
    }

    public static class Builder extends Person.Builder<Builder> {
        private LocalDate birthDate;
        private String address;

        public Builder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Patient build() {
            return new Patient(this);
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}