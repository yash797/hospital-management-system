package com.hospitalManagement.DAO;

import java.sql.SQLException;

public interface PatientDAO {
    public void addPatient(Patient patient) throws SQLException;
    public Patient getPatientById(int id) throws SQLException;
}
