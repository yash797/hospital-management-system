package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullName {
    private String firstName;
    private String lastName;
    private String middleName;

    public FullName(String fullName) {
    }

    public FullName(long fullNameId, String firstName, String lastName, String middleName) {
    }
}
