package co.edu.uniquindio.poo.parcial3.model;

public class ClinicConfig {
    private static ClinicConfig instance;
    private String clinicName;
    private double baseAppointmentPrice;

    private ClinicConfig() {
        this.clinicName = "Medical Clinic";
        this.baseAppointmentPrice = 50000.0;
    }

    public static ClinicConfig getInstance() {
        if (instance == null) {
            instance = new ClinicConfig();
        }
        return instance;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public double getBaseAppointmentPrice() {
        return baseAppointmentPrice;
    }

    public void setBaseAppointmentPrice(double baseAppointmentPrice) {
        this.baseAppointmentPrice = baseAppointmentPrice;
    }
}
