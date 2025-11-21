package co.edu.uniquindio.poo.parcial3.model.strategy;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public class NormalPricingStrategy implements PricingStrategy {
    @Override
    public double calculateDiscount(Appointment appointment) {
        return 0.0;
    }

    @Override
    public String getStrategyName() {
        return "Normal Pricing";
    }
}
