package com.hsbc.hospitalb.models;
//hello launde
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{
    private long patientId;
    private String Disease;
    private long addedBy;

    public Patient(long patientId, Person personId, String disease, Staff addedBy) {
    }

    public Patient(long patientId, long l, String disease, long l1) {
    }
}
