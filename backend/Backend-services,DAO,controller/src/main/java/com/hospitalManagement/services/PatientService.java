package com.hospitalManagement.services;

import com.hospital.dao.PatientDAO;
import com.hospital.models.Patient;

import java.sql.SQLException;

public class PatientService {
    private final PatientDAO patientDAO = new PatientDAO();

    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    // Other business logic related to patients
}
