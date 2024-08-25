package com.hsbc.hospitalb.DAO;

import com.hsbc.hospitalb.models.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminDAO {

    /**
     * Fetches an Admin by their unique ID.
     *
     * @param adminId The unique ID of the admin.
     * @return The Admin object if found, otherwise null.
     */
    Admin getAdminById(long adminId);

    /**
     * Registers a new doctor in the system.
     *
     * @param doctor The Doctor object containing the doctor's details.
     */
    void registerDoctor(Doctor doctor);

    /**
     * Retrieves a list of all doctors.
     *
     * @return A list of all Doctor objects in the system.
     */
    List<Doctor> getAllDoctors();

    /**
     * Updates the details of an existing doctor.
     *
     * @param doctor The Doctor object containing updated details.
     */
    void updateDoctor(Doctor doctor);

    /**
     * Deletes a doctor from the system by their unique ID.
     *
     * @param doctorId The unique ID of the doctor to be deleted.
     */
    void deleteDoctor(long doctorId);

    /**
     * Retrieves a list of all patients.
     *
     * @return A list of all Patient objects in the system.
     */
    List<Patient> getAllPatients();

    /**
     * Cancels an appointment by its unique ID.
     *
     * @param appointmentId The unique ID of the appointment to be canceled.
     */
    void cancelAppointment(int appointmentId);

    /**
     * Updates the details of an existing appointment.
     *
     * @param appointment The Appointment object containing updated details.
     */
    void updateAppointment(Appointment appointment);

    /**
     * Retrieves a list of appointments for a specific doctor by their unique ID.
     *
     * @param doctorId The unique ID of the doctor.
     * @return A list of Appointment objects associated with the doctor.
     */
    List<Appointment> getAppointmentsByDoctorId(long doctorId);

    /**
     * Retrieves a list of appointments scheduled for a specific date.
     *
     * @param date The date for which appointments are to be retrieved.
     * @return A list of Appointment objects scheduled on the specified date.
     */
    List<Appointment> getAppointmentsByDate(LocalDate date);

    /**
     * Retrieves a list of patients who are associated with a specific doctor by their unique ID.
     *
     * @param doctorId The unique ID of the doctor.
     * @return A list of Patient objects associated with the doctor.
     */
    List<Patient> getPatientsByDoctorId(long doctorId);

    /**
     * Retrieves a list of doctors who specialize in a specific field.
     *
     * @param specialization The specialization of the doctors to be retrieved.
     * @return A list of Doctor objects who have the specified specialization.
     */
    List<Doctor> getDoctorsBySpecialization(String specialization);

    // New methods using only appointment date

    /**
     * Updates an appointment based on its date and other provided details.
     *
     * @param appointmentDate The date of the appointment to be updated.
     * @param updatedAppointment The Appointment object containing updated details.
     */
    void updateAppointmentByDate(LocalDate appointmentDate, Appointment updatedAppointment);

    /**
     * Cancels an appointment by its unique ID and date.
     *
     * @param appointmentDate The date of the appointment to be canceled.
     * @param appointmentId The unique ID of the appointment to be canceled.
     */
    void cancelAppointmentByDate(LocalDate appointmentDate, int appointmentId);
}
