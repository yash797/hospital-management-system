package com.hsbc.hospitalb.dao.impl.admintest;

import com.hsbc.hospitalb.DAO.AdminDAOImpl;

import com.hsbc.hospitalb.models.Person;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetpersonbyidTest {
    // Retrieves a person by valid ID
    @Test
    public void test_retrieves_person_by_valid_id() throws SQLException {
        // Arrange
        long personId = 1L;
        String query = "SELECT * FROM persons WHERE person_id = ?";
        Properties sqlQueries = new Properties();
        sqlQueries.setProperty("getPersonById", query);
    
        Connection connection = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(connection.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getLong("person_id")).thenReturn(personId);
        when(rs.getLong("full_name_id")).thenReturn(1L);
        when(rs.getString("first_name")).thenReturn("John");
        when(rs.getString("last_name")).thenReturn("Doe");
        when(rs.getString("middle_name")).thenReturn("M");
        when(rs.getDate("dob")).thenReturn(Date.valueOf(LocalDate.of(1990, 1, 1)));
        when(rs.getLong("phone_number")).thenReturn(1234567890L);
        when(rs.getString("gender")).thenReturn("MALE");
        when(rs.getString("address")).thenReturn("123 Main St");
        when(rs.getString("password")).thenReturn("password123");
    
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.sqlQueries = sqlQueries;
        adminDAO.connection = connection;
    
        // Act
        Person result = adminDAO.getPersonById(personId);
    
        // Assert
        assertNotNull(result);
        assertEquals(personId, result.getPersonId());
    }

    // Person ID does not exist in the database
    @Test
    public void test_person_id_does_not_exist() throws SQLException {
        // Arrange
        long personId = 999L;
        String query = "SELECT * FROM persons WHERE person_id = ?";
        Properties sqlQueries = new Properties();
        sqlQueries.setProperty("getPersonById", query);
    
        Connection connection = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
    
        when(connection.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
    
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        adminDAO.sqlQueries = sqlQueries;
        adminDAO.connection = connection;
    
        // Act
        Person result = adminDAO.getPersonById(personId);
    
        // Assert
        assertNull(result);
    }
}