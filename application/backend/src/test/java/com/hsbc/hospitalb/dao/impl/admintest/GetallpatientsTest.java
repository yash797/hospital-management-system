package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import com.hsbc.hospitalb.models.*;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetallpatientsTest {


    // Retrieves all patients from the database successfully
    // Retrieves all patients from the database successfully
    @Test
    public void retrieves_all_patients_successfully() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        when(adminDAO.sqlQueries.getProperty("getAllPatients")).thenReturn("SELECT * FROM patients");
        when(adminDAO.connection.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery("SELECT * FROM patients")).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false); // Simulating multiple patients

        // Act
        List<Patient> patients = adminDAO.getAllPatients();

        // Assert
        assertFalse(patients.isEmpty());
        assertEquals(2, patients.size()); // Assuming 2 patients are retrieved
    }
}