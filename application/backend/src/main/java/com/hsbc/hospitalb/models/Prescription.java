package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    private int frequency;
    private int days;
    private int patientId;
    private int doctorId;
    private int medicineId;

    // Getters and setters
}
