package com.hospitalManagement.services;

import com.hospitalManagement.DAO.AppointmentDAO;
import com.hospitalManagement.models.Appointment;

import java.sql.SQLException;
import java.util.List;

public class AppointmentService {
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    public void bookAppointment(Appointment appointment) throws SQLException {
        appointmentDAO.bookAppointment(appointment);
    }

    public List<Appointment> getAppointmentsByDoctor(int doctorId) throws SQLException {
        return appointmentDAO.getAppointmentsByDoctor(doctorId);
    }

    // Other business logic related to appointments
}
