package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Appointment;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class GetappointmentsbydateTest {
    // Retrieves appointments for a given date
    @Test
    public void test_retrieves_appointments_for_given_date() throws SQLException {
        LocalDate date = LocalDate.of(2023, 10, 10);
        List<Appointment> expectedAppointments = new ArrayList<>();
        expectedAppointments.add(new Appointment(1, 101, 201, Date.valueOf(date), Time.valueOf("10:00:00"), true));
        expectedAppointments.add(new Appointment(2, 102, 202, Date.valueOf(date), Time.valueOf("11:00:00"), false));

        Properties sqlQueries = mock(Properties.class);
        when(sqlQueries.getProperty("getAppointmentsByDate")).thenReturn("SELECT * FROM appointments WHERE appointment_date = ?");

        Connection connection = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("appointment_id")).thenReturn(1, 2);
        when(rs.getInt("patient_id")).thenReturn(101, 102);
        when(rs.getInt("doctorId")).thenReturn(201, 202);
        when(rs.getDate("appointment_date")).thenReturn(Date.valueOf(date));
        when(rs.getTime("appointment_time")).thenReturn(Time.valueOf("10:00:00"), Time.valueOf("11:00:00"));
        when(rs.getBoolean("status")).thenReturn(true, false);

        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.sqlQueries = sqlQueries;
        adminDAO.connection = connection;

        List<Appointment> actualAppointments = adminDAO.getAppointmentsByDate(date);

        assertEquals(expectedAppointments, actualAppointments);
    }
}