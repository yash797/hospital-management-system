package com.hsbc.hospitalb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Person {
    private long adminId;

    public <T> Admin(long adminId, long personId, FullName fullName, T dateOfBirth, long phoneNumber, Gender gender, String address, String password) {
    }
}
