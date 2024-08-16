# Hospital Management System

## Project Scope

The Hospital Management System (HMS) is a web application designed to streamline the management of hospital services such as patient details, doctor appointments, and more. The system will be used by Admins, Doctors, and Users (hospital staff).

## Description

### User Features

- **Patient Registration:** Users can register new patients into the system.
- **Appointment Booking:** Users can book appointments with doctors on behalf of patients.
- **Doctor's Schedule:** Appointments can only be booked if the doctor's schedule is available.
- **Admin Functions:**
  - **Pre-created Admin Account:** Admin accounts are created beforehand.
  - **Doctor Registration:** Admins register doctors into the system.
  - **Manage Doctor's Schedule:** Admins can view, update, and cancel doctor appointments.
  - **Doctor Management:** Admins can remove or update doctor's details.
  - **Reports:** Admins can pull various reports related to doctors, appointments, and patients.

### Doctor Functions

- **Schedule Setup:** Doctors must set up their schedule every 3 days.
- **Update Schedule:** Doctors can update their schedule and mark unavailability.
- **Manage Appointments:** Doctors can view, cancel appointments, suggest medications, and recommend tests.

## User Interface Design

### General Requirements

- **Single Entry Point:** Home page with navigation to all major functionalities.

### Pages and Actions

1. **Login Page:**
   - Allows users (Admin, Doctor, User) to log in with valid credentials.
   - Imported users should be saved into the database.

2. **Admin Main Page:**
   - **Import Users:** Import doctors using JSON/XML files (only if data is not already present).
   - **Show All Doctors:** View and manage doctor schedules; display next 3 days' schedules by default.
   - **Cancel Appointments:** View and cancel doctor's appointments for the current day.
   - **Show All Patients:** Display patient information with filtering options.

3. **User Main Page:**
   - **Add Patient:** Add new patient details.
   - **Book Appointment:** Schedule appointments with doctors.
   - **View Appointments:** View appointments for specific dates and times.

4. **Doctor Main Page:**
   - **Add Schedule:** Set up schedule for the next 3 days with date and time details.
   - **View Appointments:** View and manage appointments for the current day and next 3 days.
   - **Suggest Medical Tests:** Recommend necessary medical tests for patients.
   - **Suggest Medicines:** Suggest medications for patients.

## Test Cases

- **JUnit Tests:** Write JUnit test cases for all application scenarios.

## Guidelines

1. **Team Division:**
   - **UI Team:** Responsible for designing and developing the user interface using HTML, CSS, and JavaScript. Develop all pages with dummy data.
   - **Back End Team:** Responsible for developing the business logic, DAO, and database.
     ![WhatsApp Image 2024-08-13 at 19 18 30_1a68e5a2](https://github.com/user-attachments/assets/864d3dc2-e307-4a76-a323-005c0bc37196)


2. **Architecture:**
   - Use layered architecture with loose coupling.

3. **Aspect-Oriented Programming:** Implement functional requirements using Aspect-Oriented Programming (AOP).

4. **Validation:** Ensure all user inputs are validated with proper error handling.

5. **Layout Consistency:** Use a consistent layout with a header, footer, and sidebar (navigation links) across all pages.

6. **Optional:** Explore CSS frameworks to enhance the visual appeal of the UI.
