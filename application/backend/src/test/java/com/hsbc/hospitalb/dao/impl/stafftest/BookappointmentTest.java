package com.hsbc.hospitalb.dao.impl.stafftest;

import com.hsbc.hospitalb.DAO.StaffDAOImpl;
import com.hsbc.hospitalb.models.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookappointmentTest {

    private StaffDAOImpl mockStaffDAO;

    @BeforeEach
    void setUp() {
        // Initialize the mock DAO
        mockStaffDAO = mock(StaffDAOImpl.class);
    }

    // Successfully book an appointment with valid appointment details
    @Test
    public void test_successful_appointment_booking() throws SQLException {
        // Arrange
        Appointment appointment = new Appointment(1, 1, 1, Date.valueOf("2023-10-10"), Time.valueOf("10:00:00"), true);
        when(mockStaffDAO.bookAppointment(any(Appointment.class))).thenReturn(true);

        // Act
        boolean result = mockStaffDAO.bookAppointment(appointment);

        // Assert
        assertTrue(result);
    }
}
