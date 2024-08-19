package com.hsbc.hms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends Person {
    private long staffId;
    private LocalDate dateOfJoining;
}
