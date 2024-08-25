package com.hsbc.hospitalb.dao.impl.admintest;


import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Appointment;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UpdateappointmentbydateTest {


    // Successfully updates an appointment when valid data is provided
    @Test
    public void test_successful_update() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("updateAppointmentByDate")).thenReturn("UPDATE appointments SET patient_id=?, doctor_id=?, appointment_time=?, status=? WHERE appointment_date=?");
    
        LocalDate appointmentDate = LocalDate.of(2023, 10, 10);
        Appointment updatedAppointment = new Appointment(1, 2, 3, Date.valueOf(appointmentDate), Time.valueOf("10:00:00"), true);
    
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
    
        // Act
        adminDAO.updateAppointmentByDate(appointmentDate, updatedAppointment);
    
        // Assert
        verify(stmt).setLong(1, updatedAppointment.getPatientId());
        verify(stmt).setLong(2, updatedAppointment.getDoctorId());
        verify(stmt).setTime(3, Time.valueOf(updatedAppointment.getAppointmentTime().toLocalTime()));
        verify(stmt).setBoolean(4, updatedAppointment.isStatus());
        verify(stmt).setDate(5, Date.valueOf(appointmentDate));
        verify(stmt).executeUpdate();
    }

    // Handles SQLException during the execution of the prepared statement
    @Test
    public void test_sql_exception_handling() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("updateAppointmentByDate")).thenReturn("UPDATE appointments SET patient_id=?, doctor_id=?, appointment_time=?, status=? WHERE appointment_date=?");
    
        LocalDate appointmentDate = LocalDate.of(2023, 10, 10);
        Appointment updatedAppointment = new Appointment(1, 2, 3, Date.valueOf(appointmentDate), Time.valueOf("10:00:00"), true);
    
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
        doThrow(new SQLException()).when(stmt).executeUpdate();
    
        // Act & Assert
        assertDoesNotThrow(() -> adminDAO.updateAppointmentByDate(appointmentDate, updatedAppointment));
    }
}