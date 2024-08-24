package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private int doctorId;
    private boolean Available;

    public Schedule(long scheduleId, Doctor doctorById, String dayOfWeek, LocalTime startTime, LocalTime endTime) {
    }

    public Schedule(long l, LocalDate now, Time time, Time time1, boolean b) {
    }
}
