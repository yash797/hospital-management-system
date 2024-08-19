package com.hsbc.hms.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{
    private long patientId;
    private String Disease;
    private Staff addedBy;
}
