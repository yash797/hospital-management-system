package com.hospitalManagement.DAO;

import com.hospitalManagement.models.Patient;
import com.hospitalManagement.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientImpl implements PatientDAO {
    @Override
    public void addPatient(Patient patient) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO patients (name, age, gender, disease, added_by) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getDisease());
            stmt.setInt(5, patient.getAddedBy());
            stmt.executeUpdate();
        }
    }
    @Override
    public Patient getPatientById(int id) throws SQLException {
        Patient patient = null;
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM patients WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("gender"),
                        rs.getString("disease"), rs.getInt("added_by"));
            }
        }
        return patient;
    }
}
