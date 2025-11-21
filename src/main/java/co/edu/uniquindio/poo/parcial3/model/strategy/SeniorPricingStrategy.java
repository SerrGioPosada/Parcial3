package co.edu.uniquindio.poo.parcial3.model.strategy;

import co.edu.uniquindio.poo.parcial3.model.Appointment;
import java.time.LocalDate;
import java.time.Period;

public class SeniorPricingStrategy implements PricingStrategy {
    private static final int SENIOR_AGE = 65;
    private static final double DISCOUNT_PERCENTAGE = 0.20;

    @Override
    public double calculateDiscount(Appointment appointment) {
        if (appointment.getPatient().getBirthDate() != null) {
            int age = Period.between(appointment.getPatient().getBirthDate(), LocalDate.now()).getYears();
            if (age >= SENIOR_AGE) {
                return appointment.getPrice() * DISCOUNT_PERCENTAGE;
            }
        }
        return 0.0;
    }

    @Override
    public String getStrategyName() {
        return "Senior Discount (20%)";
    }
}
