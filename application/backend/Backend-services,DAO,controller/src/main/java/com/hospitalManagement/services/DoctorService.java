package com.hospitalManagement.services;

import com.hospital.dao.DoctorDAO;
import com.hospital.models.Doctor;

import java.sql.SQLException;

public class DoctorService {
    private final DoctorDAO doctorDAO = new DoctorDAO();

    public void addDoctor(Doctor doctor) throws SQLException {
        doctorDAO.addDoctor(doctor);
    }

    // Other business logic related to doctors
}
