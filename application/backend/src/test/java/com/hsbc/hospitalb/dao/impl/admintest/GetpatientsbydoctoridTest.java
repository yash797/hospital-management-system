package com.hsbc.hospitalb.dao.impl.admintest;

import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetpatientsbydoctoridTest {


    // Retrieves a list of patients for a given doctor ID
    // Testing the retrieval of patients by doctor ID
    // Replicates the original test function 'test_retrieves_patients_by_doctor_id_error'
    @Test
    public void test_retrieves_patients_by_doctor_id_error_replica() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.connection = mock(Connection.class);
        adminDAO.sqlQueries = mock(Properties.class);
        when(adminDAO.sqlQueries.getProperty("getPatientsByDoctorId")).thenReturn("SELECT * FROM patients WHERE doctor_id = ?");

        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(adminDAO.connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("patient_id")).thenReturn(1L);
        when(rs.getLong("person_id")).thenReturn(1L);
        when(rs.getString("disease")).thenReturn("Flu");
        when(rs.getLong("added_by")).thenReturn(1L);

        Person person = new Person(1L, new FullName(1L, "John", "Doe", "M"), LocalDate.of(1990, 1, 1), 1234567890L, Gender.MALE, "123 Street", "password");
        Staff staff = new Staff(1L, person);

        // Mocking internal methods
        when(adminDAO.getPersonById(1L)).thenReturn(person);
        when(adminDAO.getStaffById(1L)).thenReturn(staff);

        // Act
        List<Patient> patients = adminDAO.getPatientsByDoctorId(1L);

        // Assert
        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals(1L, patients.get(0).getPatientId());
    }
}