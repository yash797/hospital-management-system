package com.hsbc.hospitalb.DAO;

import com.hsbc.hospitalb.models.Patient;
import com.hsbc.hospitalb.models.Appointment;
import java.util.List;
import java.time.LocalDate;

public interface StaffDAO {

    /**
     * Adds a new patient to the system.
     *
     * @param patient The Patient object containing the patient's details.
     * @return true if the patient was successfully added, false otherwise.
     */
    boolean addPatient(Patient patient);

    /**
     * Books a new appointment for a patient.
     *
     * @param appointment The Appointment object containing the appointment details.
     * @return true if the appointment was successfully booked, false otherwise.
     */
    boolean bookAppointment(Appointment appointment);

    /**
     * Retrieves a list of appointments scheduled for a specific date.
     *
     * @param date The date for which to retrieve appointments.
     * @return A list of Appointment objects scheduled on the specified date.
     */
    List<Appointment> viewAppointments(LocalDate date);

    /**
     * Retrieves a patient by their unique ID.
     *
     * @param patientId The unique ID of the patient to retrieve.
     * @return The Patient object if found, or null if no patient with the specified ID exists.
     */
    Patient getPatientById(long patientId);

    /**
     * Retrieves a list of all patients in the system.
     *
     * @return A list of all Patient objects in the system.
     */
    List<Patient> getAllPatients();
}
