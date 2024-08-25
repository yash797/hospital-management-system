package com.hsbc.hospitalb.DAO;

import com.hsbc.hospitalb.models.Appointment;
import com.hsbc.hospitalb.models.Schedule;
import com.hsbc.hospitalb.models.Prescription;
import java.util.List;

/**
 * The DoctorDAO interface defines the methods for interacting with the
 * doctor-related data in the database. It includes operations for
 * managing schedules, appointments, and prescriptions.
 */
public interface DoctorDAO {

    /**
     * Updates the schedule of a doctor in the database.
     *
     * @param schedule The Schedule object containing the updated schedule details.
     */
    void updateSchedule(Schedule schedule);

    /**
     * Retrieves a list of appointments for a specific doctor.
     *
     * @param doctorId The ID of the doctor whose appointments are to be retrieved.
     * @return A list of Appointment objects associated with the specified doctor.
     */
    List<Appointment> getAppointmentsByDoctorId(int doctorId);

    /**
     * Retrieves an appointment by its unique ID.
     *
     * @param appointmentId The ID of the appointment to be retrieved.
     * @return The Appointment object if found, otherwise null.
     */
    Appointment getAppointmentById(int appointmentId);

    /**
     * Updates an existing appointment in the database.
     *
     * @param appointment The Appointment object containing the updated details.
     */
    void updateAppointment(Appointment appointment);

    /**
     * Creates a new prescription in the database.
     *
     * @param prescription The Prescription object containing the prescription details.
     */
    void createPrescription(Prescription prescription);
}
