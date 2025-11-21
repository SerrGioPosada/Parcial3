package co.edu.uniquindio.poo.parcial3.model.factory;

import co.edu.uniquindio.poo.parcial3.model.Doctor;
import co.edu.uniquindio.poo.parcial3.model.Patient;
import co.edu.uniquindio.poo.parcial3.model.Person;
import java.time.LocalDate;

public class PersonFactory {
    public static Person createPerson(PersonType type, String id, String name, String phone, String email,
                                      LocalDate birthDate, String address, String specialty, String licenseNumber) {
        return switch (type) {
            case PATIENT -> new Patient.Builder()
                    .withId(id)
                    .withName(name)
                    .withPhone(phone)
                    .withEmail(email)
                    .withBirthDate(birthDate)
                    .withAddress(address)
                    .build();

            case DOCTOR -> new Doctor.Builder()
                    .withId(id)
                    .withName(name)
                    .withPhone(phone)
                    .withEmail(email)
                    .withSpecialty(specialty)
                    .withLicenseNumber(licenseNumber)
                    .build();
        };
    }

    public static Patient createPatient(String id, String name, String phone, String email,
                                       LocalDate birthDate, String address) {
        return new Patient.Builder()
                .withId(id)
                .withName(name)
                .withPhone(phone)
                .withEmail(email)
                .withBirthDate(birthDate)
                .withAddress(address)
                .build();
    }

    public static Doctor createDoctor(String id, String name, String phone, String email,
                                      String specialty, String licenseNumber) {
        return new Doctor.Builder()
                .withId(id)
                .withName(name)
                .withPhone(phone)
                .withEmail(email)
                .withSpecialty(specialty)
                .withLicenseNumber(licenseNumber)
                .build();
    }
}
