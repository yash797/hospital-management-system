package com.hsbc.hms.models;

import java.util.List;

public class Doctor extends Person{
    private int doctorId;
    private String specialization;
    private List<Schedule> schedules;
}
