package co.edu.uniquindio.poo.parcial3.service;

import co.edu.uniquindio.poo.parcial3.model.Appointment;
import co.edu.uniquindio.poo.parcial3.model.AppointmentType;
import co.edu.uniquindio.poo.parcial3.model.AvailableAppointment;
import co.edu.uniquindio.poo.parcial3.model.ClinicConfig;
import co.edu.uniquindio.poo.parcial3.model.Doctor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class AvailableAppointmentService {
    private DoctorService doctorService;

    public AvailableAppointmentService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public List<AvailableAppointment> generateAvailableAppointments(AppointmentType appointmentType) {
        System.out.println("[DEBUG] AvailableAppointmentService - Generando citas disponibles");
        List<AvailableAppointment> available = new LinkedList<>();
        List<Doctor> doctors = doctorService.getAllDoctors();
        ClinicConfig config = ClinicConfig.getInstance();

        System.out.println("[DEBUG] Número de doctores: " + doctors.size());
        System.out.println("[DEBUG] Precio base de cita: " + config.getBaseAppointmentPrice());

        LocalDate today = LocalDate.now();
        System.out.println("[DEBUG] Fecha de hoy: " + today);
        System.out.println("[DEBUG] Generando citas para los próximos 7 días");

        for (int dayOffset = 1; dayOffset <= 7; dayOffset++) {
            LocalDate date = today.plusDays(dayOffset);
            for (Doctor doctor : doctors) {
                LocalTime startTime = LocalTime.of(8, 0);
                LocalTime endTime = LocalTime.of(17, 0);
                LocalTime current = startTime;

                int slotsForDoctorDay = 0;

                while (current.isBefore(endTime)) {
                    if (!isTimeSlotTaken(doctor, date, current)) {
                        available.add(new AvailableAppointment(
                            doctor, date, current, config.getBaseAppointmentPrice(), appointmentType
                        ));
                        slotsForDoctorDay++;
                    }
                    current = current.plusMinutes(30);
                }

                if (dayOffset == 1) {
                    System.out.println("[DEBUG] Doctor " + doctor.getName() +
                                     " - Slots generados para " + date + ": " + slotsForDoctorDay);
                }
            }
        }

        System.out.println("[DEBUG] Total de citas disponibles generadas: " + available.size());
        return available;
    }

    private boolean isTimeSlotTaken(Doctor doctor, LocalDate date, LocalTime time) {
        return doctor.getAppointments().stream()
            .anyMatch(apt -> apt.getDate().equals(date) && apt.getTime().equals(time));
    }
}
