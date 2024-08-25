package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import com.hsbc.hospitalb.models.Doctor;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class DeletedoctorTest {


    // Successfully deletes a doctor when a valid doctorId is provided
    @Test
    public void test_delete_doctor_success() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("deleteDoctor")).thenReturn("DELETE FROM doctors WHERE id = ?");
    
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
    
        adminDAO.deleteDoctor(1L);
    
        verify(stmt).setLong(1, 1L);
        verify(stmt).executeUpdate();
    }

    // Handles SQL exceptions gracefully without crashing
    @Test
    public void test_delete_doctor_sql_exception() throws SQLException {
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("deleteDoctor")).thenReturn("DELETE FROM doctors WHERE id = ?");
    
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(adminDAO.connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));
    
        assertDoesNotThrow(() -> adminDAO.deleteDoctor(1L));
    }
}