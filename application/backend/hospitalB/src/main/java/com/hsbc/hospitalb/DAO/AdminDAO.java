package com.hsbc.hospitalb.DAO;
import com.hsbc.hospitalb.models.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminDAO {
    Admin getAdminById(long adminId);

    void registerDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    void updateDoctor(Doctor doctor);

    void deleteDoctor(long doctorId);

    List<Patient> getAllPatients();

    void cancelAppointment(int appointmentId);

    void updateAppointment(Appointment appointment);

    List<Appointment> getAppointmentsByDoctorId(long doctorId);

    List<Appointment> getNextThreeDaysAppointmentsByDoctorId(long doctorId);

    List<Appointment> getAppointmentsByDate(LocalDate date);

    List<Patient> getPatientsByDoctorId(long doctorId);

    List<Doctor> getDoctorsBySpecialization(String specialization);

    // New methods using only appointment date
    void updateAppointmentByDate(LocalDate appointmentDate, Appointment updatedAppointment);

    void cancelAppointmentByDate(LocalDate appointmentDate, int appointmentId);
}

