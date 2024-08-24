//package com.hsbc.hospitalb.DAO;
//import com.hsbc.hospitalb.DAO.DoctorDAO;
//import com.hsbc.hospitalb.models.Appointment;
//import com.hsbc.hospitalb.models.Schedule;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//public class DoctorDAOImpl implements DoctorDAO {
//
//    private Connection connection;
//    private Properties sqlQueries;
//
//    // Constructor to initialize the connection and load SQL queries
//    public DoctorDAOImpl(Connection connection) {
//        this.connection = connection;
//        this.sqlQueries = new Properties();
//        loadSQLQueries();
//    }
//
//    private void loadSQLQueries() {
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties.txt")) {
//            if (input == null) {
//                System.out.println("Sorry, unable to find application.properties.txt");
//                return;
//            }
//            sqlQueries.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updateSchedule(Schedule schedule) {
//        String sql = sqlQueries.getProperty("updateSchedule");
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setDate(1, new LocalDate(schedule.getDate()));
//            statement.setTime(2, schedule.getStartTime());
//            statement.setTime(3, schedule.getEndTime());
//            statement.setInt(4, schedule.getDoctorId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
//        String sql = sqlQueries.getProperty("getAppointmentsByDoctorId");
//        List<Appointment> appointments = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, doctorId);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Appointment appointment = new Appointment();
//                appointment.setId(rs.getInt("id"));
//                appointment.setDoctorId(rs.getInt("doctorId"));
//                appointment.setPatientId(rs.getInt("patientId"));
//                appointment.setAppointmentDate(rs.getDate("date"));
//                appointment.setAppointmentTime(rs.getTime("startTime"));
//                appointment.setEndTime(rs.getTime("endTime"));
//                appointment.setMedication(rs.getString("medication"));
//                appointment.setRecommendedTests(rs.getString("recommendedTests"));
//                appointments.add(appointment);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return appointments;
//    }
//
//    @Override
//    public Appointment getAppointmentById(int appointmentId) {
//        String sql = sqlQueries.getProperty("getAppointmentById");
//        Appointment appointment = null;
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, appointmentId);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                appointment = new Appointment();
//                appointment.setId(rs.getInt("id"));
//                appointment.setDoctorId(rs.getInt("doctorId"));
//                appointment.setPatientId(rs.getInt("patientId"));
//                appointment.setDate(rs.getDate("date"));
//                appointment.setStartTime(rs.getTime("startTime"));
//                appointment.setEndTime(rs.getTime("endTime"));
//                appointment.setMedication(rs.getString("medication"));
//                appointment.setRecommendedTests(rs.getString("recommendedTests"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return appointment;
//    }
//
//    @Override
//    public void updateAppointment(Appointment appointment) {
//        String sql = sqlQueries.getProperty("updateAppointment");
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, appointment.getMedication());
//            statement.setString(2, appointment.getRecommendedTests());
//            statement.setInt(3, appointment.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void suggestMedication(int appointmentId, String medication) {
//        String sql = sqlQueries.getProperty("suggestMedication");
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, medication);
//            statement.setInt(2, appointmentId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void recommendTest(int appointmentId, String test) {
//        String sql = sqlQueries.getProperty("recommendTest");
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, test);
//            statement.setInt(2, appointmentId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
