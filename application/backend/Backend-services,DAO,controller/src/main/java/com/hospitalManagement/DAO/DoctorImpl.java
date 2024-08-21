package com.hospitalManagement.DAO;

import com.hcbc.models.Doctor;
import com.hsbc.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorImpl implements DoctorDAO {
    @Override
    public void addDoctor(Doctor doctor) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO doctors (name, specialization) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.executeUpdate();
        }
    }
    @Override
    public Doctor getDoctorById(int id) throws SQLException {
        Doctor doctor = null;
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM doctors WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                doctor = new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("specialization"));
            }
        }
        return doctor;
    }
}
