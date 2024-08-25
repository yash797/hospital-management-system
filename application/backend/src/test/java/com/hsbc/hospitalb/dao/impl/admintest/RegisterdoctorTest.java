package com.hsbc.hospitalb.dao.impl.admintest;

import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Doctor;
import com.hsbc.hospitalb.models.FullName;
import com.hsbc.hospitalb.models.Gender;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RegisterdoctorTest {


    // Successfully registers a new doctor with all valid details
    @Test
    public void test_register_doctor_success() throws SQLException {
        // Arrange
        Properties sqlQueries = new Properties();
        sqlQueries.setProperty("insertPerson", "INSERT INTO Person ...");
        sqlQueries.setProperty("registerDoctor", "INSERT INTO Doctor ...");

        Connection connection = mock(Connection.class);
        PreparedStatement personStmt = mock(PreparedStatement.class);
        PreparedStatement doctorStmt = mock(PreparedStatement.class);
        ResultSet generatedKeys = mock(ResultSet.class);

        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(personStmt);
        when(connection.prepareStatement(anyString())).thenReturn(doctorStmt);
        when(personStmt.getGeneratedKeys()).thenReturn(generatedKeys);
        when(generatedKeys.next()).thenReturn(true);
        when(generatedKeys.getLong(1)).thenReturn(1L);
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.sqlQueries = sqlQueries;
        adminDAO.connection = connection;

        Doctor doctor = new Doctor(1, 0, "Cardiology", "MD", new ArrayList<>());
        doctor.setFullName(new FullName("John", "Doe", "A"));
        doctor.setDob(LocalDate.of(1980, 1, 1));
        doctor.setPhoneNumber(1234567890L);
        doctor.setAddress("123 Main St");
        doctor.setPassword("password");
        doctor.setGender(Gender.MALE);

        // Act
        adminDAO.registerDoctor(doctor);

        // Assert
        verify(personStmt).executeUpdate();
        verify(doctorStmt).executeUpdate();
    }
}