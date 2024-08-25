package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import com.hsbc.hospitalb.models.Appointment;
import org.junit.Test;
import java.sql.*;
import java.util.Properties;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
public class UpdateappointmentTest {


    // Successfully updates an appointment with valid data
    @Test
    public void test_update_appointment_success() throws SQLException {
        // Arrange
        Appointment appointment = new Appointment(1, 123, 456, Date.valueOf("2023-10-10"), Time.valueOf("10:00:00"), true);
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("updateAppointment")).thenReturn("UPDATE appointments SET appointmentDate=?, appointmentTime=?, status=? WHERE appointmentId=?");
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);

        // Act
        adminDAO.updateAppointment(appointment);

        // Assert
        verify(stmt).setDate(1, Date.valueOf("2023-10-10"));
        verify(stmt).setTime(2, Time.valueOf("10:00:00"));
        verify(stmt).setBoolean(3, true);
        verify(stmt).setLong(4, 1);
        verify(stmt).executeUpdate();
    }

    // Handles SQLException during query execution
    @Test
    public void test_update_appointment_sql_exception() throws SQLException {
        // Arrange
        Appointment appointment = new Appointment(1, 123, 456, Date.valueOf("2023-10-10"), Time.valueOf("10:00:00"), true);
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("updateAppointment")).thenReturn("UPDATE appointments SET appointmentDate=?, appointmentTime=?, status=? WHERE appointmentId=?");
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
        doThrow(new SQLException()).when(stmt).executeUpdate();

    }
}