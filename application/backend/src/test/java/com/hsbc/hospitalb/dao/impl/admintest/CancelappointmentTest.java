package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CancelappointmentTest {


    // Successfully cancels an appointment when a valid appointmentId is provided
    @Test
    public void test_cancel_appointment_success() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("cancelAppointment")).thenReturn("DELETE FROM appointments WHERE id = ?");
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);

        adminDAO.cancelAppointment(1);

        verify(stmt).setInt(1, 1);
        verify(stmt).executeUpdate();
    }

    // Handles SQLException gracefully when the database connection is lost
    @Test
    public void test_cancel_appointment_sql_exception() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("cancelAppointment")).thenReturn("DELETE FROM appointments WHERE id = ?");
        when(adminDAO.connection.prepareStatement(anyString())).thenThrow(new SQLException("Connection lost"));

        adminDAO.cancelAppointment(1);

        // No exception should be thrown, and the stack trace should be printed
    }
}
