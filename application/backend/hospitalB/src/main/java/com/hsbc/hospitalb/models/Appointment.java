package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private int id;                   // Unique identifier for the appointment
    private int doctorId;            // ID of the doctor associated with the appointment
    private int patientId;           // ID of the patient for whom the appointment is scheduled
    private Date appointmentDate;               // Date of the appointment
    private Time appointmentTime;          // Start time of the appointment
    private boolean status;            // End time of the appointment

    public Appointment(int id, int doctorId, int patientId, LocalDate now, Time appointmentTime, boolean status) {
    }

//    // Default constructor
//    public Appointment() {}
//
//    // Parameterized constructor
//    public Appointment(int id, int doctorId, int patientId, Date date, Time startTime, Time endTime, String medication, String recommendedTests) {
//        this.id = id;
//        this.doctorId = doctorId;
//        this.patientId = patientId;
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.medication = medication;
//        this.recommendedTests = recommendedTests;
//    }
//
//    public Appointment(long appointmentId, Patient patientId, Doctor doctorId, LocalDate appointmentDate, LocalTime appointmentTime, boolean status) {
//    }
//
//    // Getters and Setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(int doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public int getPatientId() {
//        return patientId;
//    }
//
//    public void setPatientId(int patientId) {
//        this.patientId = patientId;
//    }
//
//    public java.sql.Date getDate() {
//        return (java.sql.Date) date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public Time getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Time startTime) {
//        this.startTime = startTime;
//    }
//
//    public Time getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Time endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getMedication() {
//        return medication;
//    }
//
//    public void setMedication(String medication) {
//        this.medication = medication;
//    }
//
//    public String getRecommendedTests() {
//        return recommendedTests;
//    }
//
//    public void setRecommendedTests(String recommendedTests) {
//        this.recommendedTests = recommendedTests;
//    }
//
//    @Override
//    public String toString() {
//        return "Appointment{" +
//                "id=" + id +
//                ", doctorId=" + doctorId +
//                ", patientId=" + patientId +
//                ", date=" + date +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", medication='" + medication + '\'' +
//                ", recommendedTests='" + recommendedTests + '\'' +
//                '}';
//    }
//
//    public LocalDate getAppointmentDate() {
//        return LocalDate.now();
//    }
//
//    public String getAppointmentTime() {
//        return ;
//    }
//
//    public boolean isStatus() {
//        return sta
//    }
//
//    public long getAppointmentId() {
//        return id;
//    }
}
