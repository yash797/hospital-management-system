package com.hospitalManagement.DAO;

import java.sql.SQLException;

public interface DoctorDAO {
    public void addDoctor(Doctor doctor) throws SQLException;
    public Doctor getDoctorById(int id) throws SQLException;
}
