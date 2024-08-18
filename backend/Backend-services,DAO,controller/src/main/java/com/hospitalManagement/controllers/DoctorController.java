package com.hospitalManagement.controllers;

import com.hospitalManagement..models.Doctor;
import com.hospitalManagement..services.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor")
public class DoctorController extends HttpServlet {
    private final DoctorService doctorService = new DoctorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            addDoctor(req, resp);
        }
    }

    private void addDoctor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String specialization = req.getParameter("specialization");

        Doctor doctor = new Doctor(0, name, specialization);
        try {
            doctorService.addDoctor(doctor);
            resp.sendRedirect("pages/dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to add doctor");
        }
    }
}
