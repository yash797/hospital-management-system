package com.hsbc.hospitalb.dao.impl.admintest;

import com.hsbc.hospitalb.DAO.DoctorDAOImpl;
import com.hsbc.hospitalb.models.Appointment;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class GetadminbyidTest {
    // Handle SQLExceptions during query execution
    @Test
    public void test_handle_sql_exception_during_query_execution() {
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);
        Properties mockProperties = Mockito.mock(Properties.class);

        DoctorDAOImpl doctorDAO = new DoctorDAOImpl(mockConnection);
        doctorDAO.sqlQueries = mockProperties;

        try {
            Mockito.when(mockProperties.getProperty("getAppointmentsByDoctorId")).thenReturn("SELECT * FROM appointments WHERE doctor_id = ?");
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException("Database error"));

            List<Appointment> appointments = doctorDAO.getAppointmentsByDoctorId(123);

            Assertions.assertNotNull(appointments);
            Assertions.assertTrue(appointments.isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
