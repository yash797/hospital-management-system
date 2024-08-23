# Hospital Management System (HMS)

## Project Overview

The Hospital Management System (HMS) is a web application designed to streamline the management of hospital services. It enables efficient handling of patient details, doctor appointments, and other hospital operations. The system caters to three types of users: Admins, Hospital Staff, and Doctors, each with specific privileges and functionalities.

## Features

### User Features
- **Patient Registration**: Allows users to register new patients into the system.
- **Appointment Booking**: Enables users to book appointments with doctors on behalf of patients.
- **Doctor's Schedule**: Appointments can only be booked if the doctor's schedule is available.

### Admin Functions
- **Pre-created Admin Account**: Admin accounts are pre-created for system access.
- **Doctor Registration**: Admins can register new doctors into the system.
- **Manage Doctor's Schedule**: Admins can view, update, and cancel doctor appointments.
- **Doctor Management**: Admins can remove or update doctor details.
- **Reports**: Admins can generate various reports related to doctors, appointments, and patients.

### Doctor Functions
- **View Schedule**: Doctors can see their schedule for any specific day.
- **Suggest Medicine**: Doctors can prescribe medication to a patient.
- **Recomment Test**: Doctor can recommed diagnostic test to the patient for follow up appointment.

## User Interface Design

### General Requirements
- **Single Entry Point**: The homepage serves as the entry point with navigation to all major functionalities.

### Sections and Actions

#### Login Page
- Allows users (Admin, Doctor, Hospital Staff) to log in with valid credentials.
- Imported users are saved into the database.

#### Admin Section
- **Import Users**: Import doctors using JSON/XML files (if data is not already present).
- **Show All Doctors**: View and manage doctor schedules; default view shows the next 3 days' schedules.
- **Cancel Appointments**: View and cancel doctor's appointments for the current day.
- **Show All Patients**: Display patient information with filtering options.

#### User Section
- **Add Patient**: Add new patient details.
- **Book Appointment**: Schedule appointments with doctors.
- **View Appointments**: View appointments for specific dates and times.

#### Doctor Section
- **View Appointments**: View appointments for any specific day using the Filters.
- **Suggest Medical Tests**: Recommend necessary medical tests for patients.
- **Suggest Medicines**: Suggest medications for patients.