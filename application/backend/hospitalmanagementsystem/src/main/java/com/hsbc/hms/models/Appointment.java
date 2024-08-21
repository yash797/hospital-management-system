package com.hsbc.hms.models;

import java.sql.Time;
import java.time.LocalDate;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;;
    private LocalDate appointmentDate;
    private Time time;
}
