package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.sql.Time;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Schedule {
    private int scheduleId;
    private int doctorId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private boolean availability;

    public Schedule(long scheduleId, Doctor doctorById, String dayOfWeek, LocalTime startTime, LocalTime endTime) {
    }

    // Getters and setters
}