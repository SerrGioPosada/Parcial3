package co.edu.uniquindio.poo.parcial3.model;

public enum AppointmentType {
    GENERAL("General", "Consulta General"),
    PSYCHOLOGY("Psychology", "Psicología"),
    CARDIOLOGY("Cardiology", "Cardiología"),
    DERMATOLOGY("Dermatology", "Dermatología"),
    PEDIATRICS("Pediatrics", "Pediatría"),
    ORTHOPEDICS("Orthopedics", "Ortopedia");

    private final String name;
    private final String displayName;

    AppointmentType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
