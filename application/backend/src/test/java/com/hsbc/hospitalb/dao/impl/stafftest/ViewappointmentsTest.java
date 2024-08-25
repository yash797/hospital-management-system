package com.hsbc.hospitalb.dao.impl.stafftest;
import com.hsbc.hospitalb.DAO.StaffDAOImpl;
import com.hsbc.hospitalb.models.Appointment;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ViewappointmentsTest {


    // Retrieves appointments for a given date
    @Test
    public void test_retrieves_appointments_for_given_date() {
        StaffDAOImpl staffDAO = Mockito.mock(StaffDAOImpl.class);
        LocalDate date = LocalDate.of(2023, 10, 10);
        List<Appointment> expectedAppointments = new ArrayList<>();
        Appointment appointment = new Appointment(1, 1, 1, Date.valueOf(date), Time.valueOf("10:00:00"), true);
        expectedAppointments.add(appointment);
    
        Mockito.when(staffDAO.viewAppointments(date)).thenReturn(expectedAppointments);
    
        List<Appointment> actualAppointments = staffDAO.viewAppointments(date);
    
        Assertions.assertEquals(expectedAppointments.size(), actualAppointments.size());
        Assertions.assertEquals(expectedAppointments.get(0).getAppointmentId(), actualAppointments.get(0).getAppointmentId());
    }
}