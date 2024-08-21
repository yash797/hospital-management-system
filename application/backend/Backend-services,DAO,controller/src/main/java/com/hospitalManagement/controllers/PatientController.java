package com.hospitalManagement.controllers;

import com.hospitalManagement.models.Patient;
import com.hospitalManagement.services.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patient")
public class PatientController extends HttpServlet {
    private final PatientService patientService = new PatientService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            addPatient(req, resp);
        }
    }

    private void addPatient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String disease = req.getParameter("disease");
        int addedBy = Integer.parseInt(req.getParameter("addedBy"));

        Patient patient = new Patient(name, age, gender, disease, addedBy);
        try {
            patientService.addPatient(patient);
            resp.sendRedirect("pages/dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to add patient");
        }
    }
}
