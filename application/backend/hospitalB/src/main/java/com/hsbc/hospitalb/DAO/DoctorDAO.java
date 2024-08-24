package com.hsbc.hospitalb.DAO;
import com.hsbc.hospitalb.models.Appointment;
import com.hsbc.hospitalb.models.Schedule;
import java.util.List;

public interface DoctorDAO {

    // Method to update an existing schedule for a doctor
    void updateSchedule(Schedule schedule);

    // Method to retrieve all appointments for a specific doctor by doctorId
    List<Appointment> getAppointmentsByDoctorId(int doctorId);

    // Method to retrieve a specific appointment by its ID
    Appointment getAppointmentById(int appointmentId);

    // Method to update an appointment with medication and recommended tests
    void updateAppointment(Appointment appointment);

    // Method to suggest medication for a specific appointment
    void suggestMedication(int appointmentId, String medication);

    // Method to recommend a test for a specific appointment
    void recommendTest(int appointmentId, String test);
}

