package com.hsbc.hospitalb.dao.impl;

import com.hsbc.hospitalb.DAO.AdminDAOImpl;
import com.hsbc.hospitalb.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AdminDAOImplTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private AdminDAOImpl adminDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
    }

    @Test
    void testGetAdminById() throws SQLException {
        // Arrange
        long adminId = 1L;
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("admin_id")).thenReturn(adminId);

        // Act
        Admin admin = adminDAO.getAdminById(adminId);

        // Assert
        assertNotNull(admin);
        assertEquals(adminId, admin.getAdminId());
        verify(mockPreparedStatement).setLong(1, adminId);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testRegisterDoctor() throws SQLException {
        // Arrange
        Doctor doctor = new Doctor(1, 0, "Specialization", "Qualification", new ArrayList<>());
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong(1)).thenReturn(1L);

        // Act
        adminDAO.registerDoctor(doctor);

        // Assert
        verify(mockPreparedStatement).setLong(1, doctor.getPersonId());
        verify(mockPreparedStatement).setString(2, doctor.getFullName().toString());
        verify(mockPreparedStatement).setDate(3, Date.valueOf(doctor.getDob()));
        verify(mockPreparedStatement).setLong(4, doctor.getPhoneNumber());
        verify(mockPreparedStatement).setString(5, doctor.getAddress());
        verify(mockPreparedStatement).setString(6, doctor.getPassword());
        verify(mockPreparedStatement).setString(7, doctor.getGender().name());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testGetAllDoctors() throws SQLException {
        // Arrange
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("doctor_id")).thenReturn(1);
        when(mockResultSet.getInt("person_id")).thenReturn(1);
        when(mockResultSet.getString("specialization")).thenReturn("Specialization");
        when(mockResultSet.getString("qualification")).thenReturn("Qualification");

        // Act
        List<Doctor> doctors = adminDAO.getAllDoctors();

        // Assert
        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
        Doctor doctor = doctors.get(0);
        assertEquals(1, doctor.getDoctorId());
        assertEquals("Specialization", doctor.getSpecialization());
        assertEquals("Qualification", doctor.getQualification());
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testUpdateDoctor() throws SQLException {
        // Arrange
        Doctor doctor = new Doctor(1, 1, "NewSpecialization", "NewQualification", new ArrayList<>());
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        adminDAO.updateDoctor(doctor);

        // Assert
        verify(mockPreparedStatement).setString(1, doctor.getSpecialization());
        verify(mockPreparedStatement).setString(2, doctor.getQualification());
        verify(mockPreparedStatement).setLong(3, doctor.getDoctorId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testDeleteDoctor() throws SQLException {
        // Arrange
        long doctorId = 1L;
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        adminDAO.deleteDoctor(doctorId);

        // Assert
        verify(mockPreparedStatement).setLong(1, doctorId);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testGetAllPatients() throws SQLException {
        // Arrange
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("patient_id")).thenReturn(1L);
        when(mockResultSet.getLong("person_id")).thenReturn(1L);
        when(mockResultSet.getString("disease")).thenReturn("Disease");
        when(mockResultSet.getLong("added_by")).thenReturn(1L);

        // Act
        List<Patient> patients = adminDAO.getAllPatients();

        // Assert
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        Patient patient = patients.get(0);
        assertEquals(1L, patient.getPatientId());
        assertEquals("Disease", patient.getDisease());
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testCancelAppointment() throws SQLException {
        // Arrange
        int appointmentId = 1;
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        adminDAO.cancelAppointment(appointmentId);

        // Assert
        verify(mockPreparedStatement).setInt(1, appointmentId);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testGetAppointmentsByDoctorId() throws SQLException {
        // Arrange
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("appointment_id")).thenReturn(1);
        when(mockResultSet.getInt("patient_id")).thenReturn(1);
        when(mockResultSet.getInt("doctorId")).thenReturn(1);
        when(mockResultSet.getDate("appointment_date")).thenReturn(Date.valueOf(LocalDate.now()));
        when(mockResultSet.getTime("appointment_time")).thenReturn(Time.valueOf("12:00:00"));
        when(mockResultSet.getBoolean("status")).thenReturn(true);

        // Act
        List<Appointment> appointments = adminDAO.getAppointmentsByDoctorId(1L);

        // Assert
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        Appointment appointment = appointments.get(0);
        assertEquals(1, appointment.getId());
        assertTrue(appointment.isStatus());
        verify(mockPreparedStatement).setLong(1, 1L);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testUpdateAppointment() throws SQLException {
        // Arrange
        Appointment appointment = new Appointment(1, 1, 1, LocalDate.now(), Time.valueOf("12:00:00"), true);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        adminDAO.updateAppointment(appointment);

        // Assert
        verify(mockPreparedStatement).setDate(1, Date.valueOf(String.valueOf(appointment.getAppointmentDate())));
        verify(mockPreparedStatement).setTime(2, Time.valueOf(appointment.getAppointmentTime().toLocalTime()));
        verify(mockPreparedStatement).setBoolean(3, appointment.isStatus());
        verify(mockPreparedStatement).setLong(4, appointment.getId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testGetNextThreeDaysAppointmentsByDoctorId() throws SQLException {
        // Arrange
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("appointment_id")).thenReturn(1);
        when(mockResultSet.getInt("patient_id")).thenReturn(1);
        when(mockResultSet.getInt("doctorId")).thenReturn(1);
        when(mockResultSet.getDate("appointment_date")).thenReturn(Date.valueOf(today));
        when(mockResultSet.getTime("appointment_time")).thenReturn(Time.valueOf("12:00:00"));
        when(mockResultSet.getBoolean("status")).thenReturn(true);

        // Act
        List<Appointment> appointments = adminDAO.getNextThreeDaysAppointmentsByDoctorId(1L);

        // Assert
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        Appointment appointment = appointments.get(0);
        assertEquals(1, appointment.getId());
        assertTrue(appointment.isStatus());
        verify(mockPreparedStatement).setLong(1, 1L);
        verify(mockPreparedStatement).setDate(2, Date.valueOf(today));
        verify(mockPreparedStatement).setDate(3, Date.valueOf(threeDaysLater));
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testGetAppointmentsByDate() throws SQLException {
        // Arrange
        LocalDate date = LocalDate.now();
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("appointment_id")).thenReturn(1);
        when(mockResultSet.getInt("patient_id")).thenReturn(1);
        when(mockResultSet.getInt("doctorId")).thenReturn(1);
        when(mockResultSet.getDate("appointment_date")).thenReturn(Date.valueOf(date));
        when(mockResultSet.getTime("appointment_time")).thenReturn(Time.valueOf("12:00:00"));
        when(mockResultSet.getBoolean("status")).thenReturn(true);

        // Act
        List<Appointment> appointments = adminDAO.getAppointmentsByDate(date);

        // Assert
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        Appointment appointment = appointments.get(0);
        assertEquals(1, appointment.getId());
        assertTrue(appointment.isStatus());
        verify(mockPreparedStatement).setDate(1, Date.valueOf(date));
        verify(mockPreparedStatement).executeQuery();
    }
    @Test
    void testGetPatientsByDoctorId() throws SQLException {
        // Arrange
        long doctorId = 1L;
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("patient_id")).thenReturn(1L);
        when(mockResultSet.getLong("person_id")).thenReturn(1L);
        when(mockResultSet.getString("disease")).thenReturn("Disease");
        when(mockResultSet.getLong("added_by")).thenReturn(1L);

        // Act
        List<Patient> patients = adminDAO.getPatientsByDoctorId(doctorId);

        // Assert
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        Patient patient = patients.get(0);
        assertEquals(1L, patient.getPatientId());
        assertEquals("Disease", patient.getDisease());
        verify(mockPreparedStatement).setLong(1, doctorId);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testGetDoctorsBySpecialization() throws SQLException {
        // Arrange
        String specialization = "Cardiology";
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("doctor_id")).thenReturn(1L);
        when(mockResultSet.getString("specialization")).thenReturn(specialization);

        // Act
        List<Doctor> doctors = adminDAO.getDoctorsBySpecialization(specialization);

        // Assert
        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
        Doctor doctor = doctors.get(0);
        assertEquals(specialization, doctor.getSpecialization());
        verify(mockPreparedStatement).setString(1, specialization);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testUpdateAppointmentByDate() throws SQLException {
        // Arrange
        LocalDate appointmentDate = LocalDate.now();
        Appointment updatedAppointment = new Appointment(1, 1, 1, appointmentDate, Time.valueOf("12:00:00"), true);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        adminDAO.updateAppointmentByDate(appointmentDate, updatedAppointment);

        // Assert
        verify(mockPreparedStatement).setDate(1, Date.valueOf(appointmentDate));
        verify(mockPreparedStatement).setDate(2, Date.valueOf(String.valueOf(updatedAppointment.getAppointmentDate())));
        verify(mockPreparedStatement).setTime(3, Time.valueOf(updatedAppointment.getAppointmentTime().toLocalTime()));
        verify(mockPreparedStatement).setBoolean(4, updatedAppointment.isStatus());
        verify(mockPreparedStatement).setInt(5, updatedAppointment.getId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testCancelAppointmentByDate() throws SQLException {
        // Arrange
        LocalDate appointmentDate = LocalDate.now();
        int appointmentId = 1;
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Act
        adminDAO.cancelAppointmentByDate(appointmentDate, appointmentId);

        // Assert
        verify(mockPreparedStatement).setDate(1, Date.valueOf(appointmentDate));
        verify(mockPreparedStatement).setInt(2, appointmentId);
        verify(mockPreparedStatement).executeUpdate();
    }
}