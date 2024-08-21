package com.hospitalManagement.DAO;

import com.hsbc.models.Appointment;
import com.hsbc.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentImplementation implements AppointmentDAO {
    @Override
    public void bookAppointment(Appointment appointment) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, appointment.getAppointmentDate());
            stmt.setTime(4, appointment.getAppointmentTime());
            stmt.setString(5, appointment.getStatus());
            stmt.executeUpdate();
        }
    }
@Override
    public List<Appointment> getAppointmentsByDoctor(int doctorId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM appointments WHERE doctor_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_date"),
                        rs.getTime("appointment_time"),
                        rs.getString("status")
                );
                appointments.add(appointment);
            }
        }
        return appointments;
    }
}
