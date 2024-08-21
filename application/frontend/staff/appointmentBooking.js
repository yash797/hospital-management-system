document.addEventListener("DOMContentLoaded", () => {
    const doctors = [
        { id: 1, name: "Dr. John Doe" },
        { id: 2, name: "Dr. Jane Smith" }
    ];

    const doctorSelect = document.getElementById("doctorId");
    const appointmentTimeSelect = document.getElementById("appointmentTime");

    doctors.forEach(doctor => {
        const option = document.createElement("option");
        option.value = doctor.id;
        option.textContent = doctor.name;
        doctorSelect.appendChild(option);
    });

    const fetchAvailableSlots = (doctorId, date) => {
        const schedule = JSON.parse(localStorage.getItem(`schedule-${doctorId}`)) || {};
        const bookedSlots = schedule[date] || [];
        const allSlots = ["09:00 AM", "10:00 AM", "11:00 AM", "01:00 PM", "03:00 PM"];
        return allSlots.filter(slot => !bookedSlots.includes(slot));
    };

    doctorSelect.addEventListener("change", updateAvailableSlots);
    document.getElementById("appointmentDate").addEventListener("change", updateAvailableSlots);

    function updateAvailableSlots() {
        const doctorId = doctorSelect.value;
        const date = document.getElementById("appointmentDate").value;

        if (doctorId && date) {
            const slots = fetchAvailableSlots(doctorId, date);
            appointmentTimeSelect.innerHTML = "<option value=''>Select Time</option>";
            slots.forEach(slot => {
                const option = document.createElement("option");
                option.value = slot;
                option.textContent = slot;
                appointmentTimeSelect.appendChild(option);
            });
        }
    }

    document.getElementById("appointmentForm").addEventListener("submit", event => {
        event.preventDefault();
        const patientId = document.getElementById("patientId").value;
        const doctorId = doctorSelect.value;
        const date = document.getElementById("appointmentDate").value;
        const time = appointmentTimeSelect.value;
        const reason = document.getElementById("reason").value;

        const scheduleKey = `schedule-${doctorId}`;
        const schedule = JSON.parse(localStorage.getItem(scheduleKey)) || {};
        if (!schedule[date]) schedule[date] = [];
        schedule[date].push({ patientId, time });

        localStorage.setItem(scheduleKey, JSON.stringify(schedule));
        alert("Appointment booked successfully!");

        window.location.href = `doctorSchedule.html?doctorId=${doctorId}&date=${date}`;
    });
});
