package com.hsbc.hospitalb.DAO;

import com.hsbc.hospitalb.models.Appointment;
import com.hsbc.hospitalb.models.Schedule;
import com.hsbc.hospitalb.models.Prescription;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The DoctorDAOImpl class provides implementations for the DoctorDAO interface.
 * This class interacts with the database to perform operations related to
 * doctor schedules, appointments, and prescriptions.
 */
public class DoctorDAOImpl implements DoctorDAO {

    private Connection connection;
    public Properties sqlQueries;

    /**
     * Constructor that initializes the connection and loads SQL queries from a properties file.
     *
     * @param connection The database connection to be used by this DAO.
     */
    public DoctorDAOImpl(Connection connection) {
        this.connection = connection;
        this.sqlQueries = new Properties();
        loadSQLQueries();
    }

    /**
     * Loads the SQL queries from the properties file.
     */
    private void loadSQLQueries() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("sql.application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find properties.txt");
                return;
            }
            sqlQueries.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the schedule for a doctor in the database.
     *
     * @param schedule The Schedule object containing the updated schedule details.
     */
    @Override
    public void updateSchedule(Schedule schedule) {
        String sql = sqlQueries.getProperty("updateSchedule");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(schedule.getDate().getTime()));
            statement.setTime(2, schedule.getStartTime());
            statement.setTime(3, schedule.getEndTime());
            statement.setBoolean(4, schedule.isAvailability());
            statement.setInt(5, schedule.getDoctorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of appointments for a specific doctor.
     *
     * @param doctorId The ID of the doctor whose appointments are to be retrieved.
     * @return A list of Appointment objects associated with the specified doctor.
     */
    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        String sql = sqlQueries.getProperty("getAppointmentsByDoctorId");
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setAppointmentTime(rs.getTime("appointment_time"));
                appointment.setStatus(rs.getBoolean("status"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**
     * Retrieves a specific appointment by its ID.
     *
     * @param appointmentId The ID of the appointment to be retrieved.
     * @return The Appointment object if found, otherwise null.
     */
    @Override
    public Appointment getAppointmentById(int appointmentId) {
        String sql = sqlQueries.getProperty("getAppointmentById");
        Appointment appointment = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setAppointmentTime(rs.getTime("appointment_time"));
                appointment.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     * Updates an existing appointment in the database.
     *
     * @param appointment The Appointment object containing the updated details.
     */
    @Override
    public void updateAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        if (appointment.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Appointment date cannot be null");
        }

        if (appointment.getAppointmentTime() == null) {
            throw new IllegalArgumentException("Appointment time cannot be null");
        }

        String sql = sqlQueries.getProperty("updateAppointment");
        if (sql == null) {
            throw new IllegalStateException("SQL query for updateAppointment not found");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            statement.setTime(2, appointment.getAppointmentTime());
            statement.setBoolean(3, appointment.isStatus());
            statement.setInt(4, appointment.getAppointmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating appointment", e);
        }
    }

    /**
     * Creates a new prescription in the database.
     *
     * @param prescription The Prescription object containing the prescription details.
     */
    @Override
    public void createPrescription(Prescription prescription) {
        String sql = sqlQueries.getProperty("createPrescription");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescription.getFrequency());
            statement.setInt(2, prescription.getDays());
            statement.setInt(3, prescription.getPatientId());
            statement.setInt(4, prescription.getDoctorId());
            statement.setInt(5, prescription.getMedicineId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
