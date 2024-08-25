package com.hsbc.hospitalb.dao.impl.admintest;


import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Doctor;
import com.hsbc.hospitalb.models.Schedule;
import org.junit.Test;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetschedulesbydoctoridTest {


    // Retrieves schedules for a given doctor ID
    @Test
    public void test_retrieves_schedules_for_given_doctor_id() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("getSchedulesByDoctorId")).thenReturn("SELECT * FROM schedules WHERE doctor_id = ?");
    
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
    
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("schedule_id")).thenReturn(1L);
        when(rs.getString("day_of_week")).thenReturn("Monday");
        when(rs.getTime("start_time")).thenReturn(Time.valueOf("09:00:00"));
        when(rs.getTime("end_time")).thenReturn(Time.valueOf("17:00:00"));
    
        Doctor doctor = new Doctor(1, 1, "Cardiology", "MD");
        when(adminDAO.getDoctorById(1L)).thenReturn(doctor);
    
        // Act
        List<Schedule> schedules = adminDAO.getSchedulesByDoctorId(1L);
    
        // Assert
        assertNotNull(schedules);
        assertEquals(1, schedules.size());
        assertEquals(1L, schedules.get(0).getScheduleId());
    }
}