package com.hsbc.hospitalb.DAO;

import com.hsbc.hospitalb.models.Patient;
import com.hsbc.hospitalb.models.Appointment;
import com.hsbc.hospitalb.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class StaffDAOImpl implements StaffDAO {
    private Connection connection; // Database connection object
    private Properties sqlProps; // Properties object to store SQL queries

    public StaffDAOImpl() throws SQLException {
        sqlProps = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("sql.application.properties")) {
            sqlProps.load(is); // Load SQL queries from the properties file
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connection = DatabaseConnection.getConnection(); // Establish the database connection
    }

    @Override
    public boolean addPatient(Patient patient) {
        // SQL queries for inserting data into FullName, Person, and Patient tables
        String insertFullNameSQL = sqlProps.getProperty("fullName.insert");
        String insertPersonSQL = sqlProps.getProperty("person.insert");
        String insertPatientSQL = sqlProps.getProperty("patient.insert");

        try (PreparedStatement fullNamePstmt = connection.prepareStatement(insertFullNameSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement personPstmt = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement patientPstmt = connection.prepareStatement(insertPatientSQL)) {

            // Insert into FullName table
            fullNamePstmt.setString(1, patient.getFullName().getFirstName());
            fullNamePstmt.setString(2, patient.getFullName().getLastName());
            fullNamePstmt.setString(3, patient.getFullName().getMiddleName());

            int affectedRows = fullNamePstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating full name failed, no rows affected.");
            }

            // Get the generated full_name_id
            long fullNameId;
            try (ResultSet generatedKeys = fullNamePstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fullNameId = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating full name failed, no ID obtained.");
                }
            }

            // Insert into Person table
            personPstmt.setLong(1, fullNameId);
            personPstmt.setDate(2, Date.valueOf(patient.getDob()));
            personPstmt.setLong(3, patient.getPhoneNumber());
            personPstmt.setString(4, patient.getGender().name());
            personPstmt.setString(5, patient.getAddress());
            personPstmt.setString(6, patient.getPassword());

            int affectedPersonRows = personPstmt.executeUpdate();
            if (affectedPersonRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }

            // Get the generated person_id
            long personId;
            try (ResultSet generatedKeys = personPstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    personId = generatedKeys.getLong(1);
                    patient.setPersonId(personId); // Set the personId in the Patient object
                } else {
                    throw new SQLException("Creating person failed, no ID obtained.");
                }
            }

            // Insert into Patient table
            patientPstmt.setLong(1, patient.getPersonId());
            patientPstmt.setString(2, patient.getDisease());
            patientPstmt.setLong(3, patient.getAddedBy());
            int affectedPatientRows = patientPstmt.executeUpdate();

            return affectedPatientRows > 0; // Return true if the insert was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurred
        }
    }

    @Override
    public boolean bookAppointment(Appointment appointment) {
        String sql = sqlProps.getProperty("appointment.insert"); // SQL query to insert an appointment
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, appointment.getDoctorId());
            pstmt.setInt(2, appointment.getPatientId());
            pstmt.setDate(3, Date.valueOf(String.valueOf(appointment.getAppointmentDate())));
            pstmt.setTime(4, appointment.getAppointmentTime());
            pstmt.setBoolean(5, true); // Assuming the status is set to true for a new appointment
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Return true if the insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurred
        }
    }

    @Override
    public List<Appointment> viewAppointments(LocalDate date) {
        List<Appointment> appointments = new ArrayList<>(); // List to store appointments
        String sql = sqlProps.getProperty("appointment.selectByDate"); // SQL query to select appointments by date
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setAppointmentTime(rs.getTime("appointment_time"));
                appointment.setStatus(rs.getBoolean("status"));
                appointments.add(appointment); // Add the appointment to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments; // Return the list of appointments
    }

    @Override
    public Patient getPatientById(long patientId) {
        String sql = sqlProps.getProperty("patient.selectById"); // SQL query to select a patient by ID
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getLong("patient_id"));
                patient.setPersonId(rs.getLong("person_id"));
                patient.setDisease(rs.getString("disease"));
                patient.setAddedBy(rs.getLong("added_by"));
                return patient; // Return the found patient
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no patient was found
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>(); // List to store patients
        String sql = sqlProps.getProperty("patient.selectAll"); // SQL query to select all patients
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getLong("patient_id"));
                patient.setPersonId(rs.getLong("person_id"));
                patient.setDisease(rs.getString("disease"));
                patient.setAddedBy(rs.getLong("added_by"));
                patients.add(patient); // Add the patient to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients; // Return the list of patients
    }
}
