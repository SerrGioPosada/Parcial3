package co.edu.uniquindio.poo.parcial3.model.strategy;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public interface PricingStrategy {
    double calculateDiscount(Appointment appointment);
    String getStrategyName();
}
