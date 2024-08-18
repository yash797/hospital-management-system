package com.hsbc.hms.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    protected FullName fullName;
    protected LocalDate dob;
    protected String phoneNumber;
    protected Gender gender;
    protected Address address;
    protected Contact contact;
    protected String username;
    protected  String password;




}
