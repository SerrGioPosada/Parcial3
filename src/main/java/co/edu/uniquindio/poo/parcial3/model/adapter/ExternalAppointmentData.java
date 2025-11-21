package co.edu.uniquindio.poo.parcial3.model.adapter;

public class ExternalAppointmentData {
    private String appointmentCode;
    private String patientId;
    private String doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private double cost;
    private String category;

    public ExternalAppointmentData(String appointmentCode, String patientId, String doctorId,
                                   String appointmentDate, String appointmentTime, double cost, String category) {
        this.appointmentCode = appointmentCode;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.cost = cost;
        this.category = category;
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public double getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }
}
