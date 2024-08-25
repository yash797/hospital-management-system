package com.hsbc.hospitalb.dao.impl.admintest;


import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Doctor;
import com.hsbc.hospitalb.models.Schedule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetdoctorsbyspecializationTest {


    // Returns a list of doctors with the specified specialization
    @Test
    public void test_returns_doctors_with_specified_specialization() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        String specialization = "Cardiology";
        String query = "SELECT * FROM doctors WHERE specialization = ?";
    
        when(adminDAO.sqlQueries.getProperty("getDoctorsBySpecialization")).thenReturn(query);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(adminDAO.connection.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
    
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("doctor_id")).thenReturn(1);
        when(rs.getInt("person_id")).thenReturn(101);
        when(rs.getString("specialization")).thenReturn(specialization);
        when(rs.getString("qualification")).thenReturn("MBBS");
    
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(1, new Doctor(), "Monday", LocalTime.of(9, 0), LocalTime.of(17, 0)));
    
        when(adminDAO.getSchedulesByDoctorId(1L)).thenReturn(schedules);
    
        // Act
        List<Doctor> doctors = adminDAO.getDoctorsBySpecialization(specialization);
    
        // Assert
        assertNotNull(doctors);
        assertEquals(1, doctors.size());
        assertEquals(specialization, doctors.get(0).getSpecialization());
    }

    // Specialization string is empty
    @Test
    public void test_specialization_string_is_empty() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        String specialization = "";
        String query = "SELECT * FROM doctors WHERE specialization = ?";
    
        when(adminDAO.sqlQueries.getProperty("getDoctorsBySpecialization")).thenReturn(query);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(adminDAO.connection.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
    
        when(rs.next()).thenReturn(false);
    
        // Act
        List<Doctor> doctors = adminDAO.getDoctorsBySpecialization(specialization);
    
        // Assert
        assertNotNull(doctors);
        assertTrue(doctors.isEmpty());
    }
}