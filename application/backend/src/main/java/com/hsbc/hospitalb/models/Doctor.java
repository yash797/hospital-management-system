package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Person{
    private int doctorId;
    private long personId;
    private String specialization;
    private List<Schedule> schedules;
    private String qualification;

    public Doctor(int doctorId, int personId) {
    }

    public Doctor(int doctorId, int personId, String specialization, String qualification, List<Schedule> doctorId1) {
    }

    public Doctor(int doctorId, int personId, String specialization, String qualification) {
    }
}
