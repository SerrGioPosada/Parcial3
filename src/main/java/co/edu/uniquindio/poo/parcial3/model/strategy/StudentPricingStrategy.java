package co.edu.uniquindio.poo.parcial3.model.strategy;

import co.edu.uniquindio.poo.parcial3.model.Appointment;
import java.time.LocalDate;
import java.time.Period;

public class StudentPricingStrategy implements PricingStrategy {
    private static final int MAX_STUDENT_AGE = 26;
    private static final double DISCOUNT_PERCENTAGE = 0.15;

    @Override
    public double calculateDiscount(Appointment appointment) {
        if (appointment.getPatient().getBirthDate() != null) {
            int age = Period.between(appointment.getPatient().getBirthDate(), LocalDate.now()).getYears();
            if (age <= MAX_STUDENT_AGE) {
                return appointment.getPrice() * DISCOUNT_PERCENTAGE;
            }
        }
        return 0.0;
    }

    @Override
    public String getStrategyName() {
        return "Student Discount (15%)";
    }
}
