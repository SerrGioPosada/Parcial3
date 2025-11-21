package co.edu.uniquindio.poo.parcial3.model.strategy;

import co.edu.uniquindio.poo.parcial3.model.Appointment;

public class PricingContext {
    private PricingStrategy pricingStrategy;

    public PricingContext(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateFinalPrice(Appointment appointment) {
        double discount = pricingStrategy.calculateDiscount(appointment);
        return appointment.getPrice() - discount;
    }

    public double getDiscount(Appointment appointment) {
        return pricingStrategy.calculateDiscount(appointment);
    }

    public String getStrategyName() {
        return pricingStrategy.getStrategyName();
    }
}
