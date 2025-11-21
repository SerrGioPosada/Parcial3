package co.edu.uniquindio.poo.parcial3.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialty;
    private String licenseNumber;
    private List<Appointment> appointments;

    private Doctor(Builder builder) {
        super(builder);
        this.specialty = builder.specialty;
        this.licenseNumber = builder.licenseNumber;
        this.appointments = new ArrayList<>();
    }

    public static class Builder extends Person.Builder<Builder> {
        private String specialty;
        private String licenseNumber;

        public Builder withSpecialty(String specialty) {
            this.specialty = specialty;
            return this;
        }

        public Builder withLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Doctor build() {
            return new Doctor(this);
        }
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}