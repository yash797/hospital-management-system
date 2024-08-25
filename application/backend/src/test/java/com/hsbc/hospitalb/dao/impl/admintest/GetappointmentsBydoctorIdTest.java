package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import com.hsbc.hospitalb.models.Appointment;
import org.junit.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetappointmentsBydoctorIdTest {


    // Retrieves appointments for a given doctor ID
    @Test
    public void test_retrieves_appointments_for_given_doctor_id() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("getAppointmentByDoctorId")).thenReturn("SELECT * FROM appointments WHERE doctor_id = ?");

        List<Appointment> expectedAppointments = new ArrayList<>();
        expectedAppointments.add(new Appointment(1, 1, 1, Date.valueOf("2023-10-10"), Time.valueOf("10:00:00"), true));

        try {
            PreparedStatement stmt = mock(PreparedStatement.class);
            ResultSet rs = mock(ResultSet.class);

            when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
            when(stmt.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("appointment_id")).thenReturn(1);
            when(rs.getInt("patient_id")).thenReturn(1);
            when(rs.getInt("doctorId")).thenReturn(1);
            when(rs.getDate("appointment_date")).thenReturn(Date.valueOf("2023-10-10"));
            when(rs.getTime("appointment_time")).thenReturn(Time.valueOf("10:00:00"));
            when(rs.getBoolean("status")).thenReturn(true);

            List<Appointment> actualAppointments = adminDAO.getAppointmentsByDoctorId(1L);

            assertEquals(expectedAppointments, actualAppointments);
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }

    // Handles SQLException during query execution
    @Test
    public void test_handles_sql_exception_during_query_execution() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("getAppointmentByDoctorId")).thenReturn("SELECT * FROM appointments WHERE doctor_id = ?");

        try {
            when(adminDAO.connection.prepareStatement(anyString())).thenThrow(new SQLException());

            List<Appointment> actualAppointments = adminDAO.getAppointmentsByDoctorId(1L);

            assertTrue(actualAppointments.isEmpty());
        } catch (SQLException e) {
            fail("SQLException should be handled within the method");
        }
    }
}
