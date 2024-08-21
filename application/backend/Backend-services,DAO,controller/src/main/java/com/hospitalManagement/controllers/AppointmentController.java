package com.hospitalManagement.controllers;

import com.hospitalManagement..models.Appointment;
import com.hospitalManagement..services.AppointmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/appointment")
public class AppointmentController extends HttpServlet {
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("book".equals(action)) {
            bookAppointment(req, resp);
        }
    }

    private void bookAppointment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int patientId = Integer.parseInt(req.getParameter("patientId"));
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        Date appointmentDate = Date.valueOf(req.getParameter("appointmentDate"));
        Time appointmentTime = Time.valueOf(req.getParameter("appointmentTime"));
        String status = req.getParameter("status");

        Appointment appointment = new Appointment(patientId, doctorId, appointmentDate, appointmentTime, status);
        try {
            appointmentService.bookAppointment(appointment);
            resp.sendRedirect("pages/dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to book appointment");
        }
    }
}
