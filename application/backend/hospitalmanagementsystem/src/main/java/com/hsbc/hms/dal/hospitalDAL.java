package com.hsbc.hms.dal;
public interface hospitalDAL {
    public void addDoctor(Doctor doctor) throws SQLException;
    public Doctor getDoctorById(int id) throws SQLException;
    public void addPatient(Patient patient) throws SQLException;
    public Patient getPatientById(int id) throws SQLException;
    public void bookAppointment(Appointment appointment) throws SQLException;
    public List<Appointment> getAppointmentsByDoctor(int doctorId) throws SQLException;
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
    }
