package com.hsbc.hospitalb.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    protected long personId;
    protected FullName fullName;
    protected LocalDate dob;
    protected long phoneNumber;
    protected Gender gender;
    protected String address;
    protected  String password;
}
