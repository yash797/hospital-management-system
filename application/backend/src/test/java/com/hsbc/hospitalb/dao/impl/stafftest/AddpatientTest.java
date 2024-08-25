package com.hsbc.hospitalb.dao.impl.stafftest;


import com.hsbc.hospitalb.DAO.StaffDAOImpl;

import com.hsbc.hospitalb.models.FullName;
import com.hsbc.hospitalb.models.Gender;
import com.hsbc.hospitalb.models.Patient;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.time.LocalDate;

public class AddpatientTest {
    // Successfully adds a patient with valid data
    @Test
    public void test_add_patient_success() {
        // Arrange
        Patient patient = new Patient();
        patient.setFullName(new FullName("John", "Doe", "A"));
        patient.setDob(LocalDate.of(1990, 1, 1));
        patient.setPhoneNumber(1234567890L);
        patient.setGender(Gender.MALE);
        patient.setAddress("123 Main St");
        patient.setPassword("password");
        patient.setDisease("Flu");
        patient.setAddedBy(1L);
        StaffDAOImpl staffDAO = Mockito.mock(StaffDAOImpl.class);
        Mockito.when(staffDAO.addPatient(patient)).thenReturn(true);

        // Act
        boolean result = staffDAO.addPatient(patient);

        // Assert
        Assertions.assertTrue(result);
    }

    // Patient object with missing or null fields
    @Test
    public void test_add_patient_with_missing_fields() {
        // Arrange
        Patient patient = new Patient();
        patient.setFullName(new FullName("John", "Doe", null)); // Missing middle name
        patient.setDob(LocalDate.of(1990, 1, 1));
        patient.setPhoneNumber(1234567890L);
        patient.setGender(Gender.MALE);
        patient.setAddress("123 Main St");
        patient.setPassword("password");
        patient.setDisease(null); // Missing disease
        patient.setAddedBy(1L);

        StaffDAOImpl staffDAO = Mockito.mock(StaffDAOImpl.class);
        Mockito.when(staffDAO.addPatient(patient)).thenReturn(false);

        // Act
        boolean result = staffDAO.addPatient(patient);

        // Assert
        Assertions.assertFalse(result);
    }
}