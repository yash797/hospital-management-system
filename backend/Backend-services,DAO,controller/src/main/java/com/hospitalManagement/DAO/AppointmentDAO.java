package com.hospitalManagement.DAO;

import java.sql.SQLException;

public interface AppointmentDAO {
    public void bookAppointment(Appointment appointment) throws SQLException;

    public List<Appointment> getAppointmentsByDoctor(int doctorId) throws SQLException;

}
