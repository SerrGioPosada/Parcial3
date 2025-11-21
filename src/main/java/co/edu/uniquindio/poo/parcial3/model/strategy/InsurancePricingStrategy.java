package co.edu.uniquindio.poo.parcial3.model.strategy;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public class InsurancePricingStrategy implements PricingStrategy {
    private static final double INSURANCE_COVERAGE = 0.50;

    @Override
    public double calculateDiscount(Appointment appointment) {
        return appointment.getPrice() * INSURANCE_COVERAGE;
    }

    @Override
    public String getStrategyName() {
        return "Insurance Coverage (50%)";
    }
}
