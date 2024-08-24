package com.hsbc.hospitalb.DAO;
import com.hsbc.hospitalb.models.Patient;
import com.hsbc.hospitalb.models.Appointment;
import java.util.List;
import java.time.LocalDate;

public interface StaffDAO {
    boolean addPatient(Patient patient);
    boolean bookAppointment(Appointment appointment);
    List<Appointment> viewAppointments(LocalDate date);
    Patient getPatientById(long patientId);
    List<Patient> getAllPatients();
}
