document.addEventListener("DOMContentLoaded", () => {
    const doctors = [
        { id: 1, name: "Dr. John Doe", specialty: "Cardiology" },
        { id: 2, name: "Dr. Jane Smith", specialty: "Dermatology" }
    ];

    const doctorDropdown = document.getElementById("doctorDropdown");
    const scheduleDateInput = document.getElementById("scheduleDate");
    const checkScheduleBtn = document.getElementById("checkSchedule");
    const doctorDetailsDiv = document.getElementById("doctorDetails");
    const doctorScheduleDiv = document.getElementById("doctorSchedule");
    const addAppointmentBtn = document.getElementById("addAppointmentBtn");

    // Populate the dropdown with doctor names
    doctors.forEach(doctor => {
        const option = document.createElement("option");
        option.value = doctor.id;
        option.textContent = doctor.name;
        doctorDropdown.appendChild(option);
    });

    // Handle check schedule button click
    checkScheduleBtn.addEventListener("click", () => {
        const doctorId = doctorDropdown.value;
        const selectedDate = scheduleDateInput.value;

        if (doctorId && selectedDate) {
            const doctor = doctors.find(doc => doc.id == doctorId);
            const schedule = JSON.parse(localStorage.getItem(`schedule-${doctorId}`)) || {};
            const appointments = schedule[selectedDate] || [];

            doctorDetailsDiv.innerHTML = `
                <h3>${doctor.name} - ${doctor.specialty}</h3>
                <p>Date: ${selectedDate}</p>
                <p>Appointments Scheduled: ${appointments.length}</p>
            `;

            if (appointments.length > 0) {
                const list = document.createElement("ul");
                appointments.forEach(appointment => {
                    const listItem = document.createElement("li");
                    listItem.textContent = `Time: ${appointment.time}, Patient ID: ${appointment.patientId}`;
                    list.appendChild(listItem);
                });
                doctorScheduleDiv.innerHTML = "";
                doctorScheduleDiv.appendChild(list);
            } else {
                doctorScheduleDiv.innerHTML = "<p>No appointments for this date.</p>";
            }

            const availableSlots = ["09:00 AM", "10:00 AM", "11:00 AM", "01:00 PM", "03:00 PM"];
            const bookedSlots = appointments.map(appointment => appointment.time);
            const freeSlots = availableSlots.filter(slot => !bookedSlots.includes(slot));

            if (freeSlots.length > 0) {
                addAppointmentBtn.style.display = "block";
                addAppointmentBtn.onclick = () => {
                    window.location.href = `appointmentBooking.html?doctorId=${doctorId}&date=${selectedDate}`;
                };
            } else {
                addAppointmentBtn.style.display = "none";
                alert("No available slots for this date.");
            }
        } else {
            alert("Please select a doctor and date.");
        }
    });
});
