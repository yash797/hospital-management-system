package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Doctor;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetdoctorbyidTest {


    // Retrieves a doctor by valid doctorId
    @Test
    public void test_retrieves_doctor_by_valid_doctorId() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("getDoctorById")).thenReturn("SELECT * FROM doctors WHERE doctor_id = ?");
    
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("doctor_id")).thenReturn(1);
        when(rs.getInt("person_id")).thenReturn(2);
        when(rs.getString("specialization")).thenReturn("Cardiology");
        when(rs.getString("qualification")).thenReturn("MD");
    
        // Act
        Doctor doctor = adminDAO.getDoctorById(1L);
    
        // Assert
        assertNotNull(doctor);
        assertEquals(1, doctor.getDoctorId());
        assertEquals(2, doctor.getPersonId());
        assertEquals("Cardiology", doctor.getSpecialization());
        assertEquals("MD", doctor.getQualification());
    }
}