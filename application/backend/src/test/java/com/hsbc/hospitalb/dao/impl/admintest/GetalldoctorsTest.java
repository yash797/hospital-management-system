package com.hsbc.hospitalb.dao.impl.admintest;
import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import com.hsbc.hospitalb.models.Doctor;
import com.hsbc.hospitalb.models.Schedule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;

public class GetalldoctorsTest {


    // Retrieves all doctors from the database successfully
    @Test
    public void test_retrieves_all_doctors_successfully() throws SQLException {
        // Arrange
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        Connection mockConnection = Mockito.mock(Connection.class);
        Statement mockStatement = Mockito.mock(Statement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        Properties mockProperties = Mockito.mock(Properties.class);
    
        adminDAO.connection = mockConnection;
        adminDAO.sqlQueries = mockProperties;
    
        String query = "SELECT * FROM doctors";
        Mockito.when(mockProperties.getProperty("getAllDoctors")).thenReturn(query);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(query)).thenReturn(mockResultSet);
    
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getInt("doctorId")).thenReturn(1);
        Mockito.when(mockResultSet.getInt("personId")).thenReturn(1);
        Mockito.when(mockResultSet.getString("specialization")).thenReturn("Cardiology");
        Mockito.when(mockResultSet.getString("qualification")).thenReturn("MD");
    
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(1, new Doctor(), "Monday", LocalTime.of(9, 0), LocalTime.of(17, 0)));
        Mockito.when(adminDAO.getSchedulesByDoctorId(1)).thenReturn(schedules);

        // Act
        List<Doctor> doctors = adminDAO.getAllDoctors();
    
        // Assert
        Assertions.assertNotNull(doctors);
        Assertions.assertEquals(1, doctors.size());
        Assertions.assertEquals(1, doctors.get(0).getDoctorId());
    }
}