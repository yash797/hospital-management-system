package com.hsbc.hospitalb.dao.impl.admintest;

import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.FullName;
import com.hsbc.hospitalb.models.Gender;
import com.hsbc.hospitalb.models.Person;
import com.hsbc.hospitalb.models.Staff;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetstaffbyidTest {


    // Retrieves Staff object when valid staffId is provided
    @Test
    public void test_retrieves_staff_object_with_valid_staff_id() throws SQLException {
        // Arrange
        long staffId = 1L;
        long personId = 2L;
        Staff expectedStaff = new Staff(staffId, new Person(personId, new FullName(1L, "John", "Doe", "M"), LocalDate.of(1990, 1, 1), 1234567890L, Gender.MALE, "123 Street", "password"));
    
        Properties sqlQueries = mock(Properties.class);
        when(sqlQueries.getProperty("getStaffById")).thenReturn("SELECT * FROM staff WHERE staff_id = ?");
    
        Connection connection = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getLong("staff_id")).thenReturn(staffId);
        when(rs.getLong("person_id")).thenReturn(personId);
    
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.sqlQueries = sqlQueries;
        adminDAO.connection = connection;
    
        // Act
        Staff actualStaff = adminDAO.getStaffById(staffId);
    
        // Assert
        assertEquals(expectedStaff, actualStaff);
    }
}