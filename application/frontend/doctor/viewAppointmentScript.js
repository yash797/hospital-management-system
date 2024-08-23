// document.addEventListener("DOMContentLoaded", function () {
function viewAppointmentSubmit() {
    const appointments = [
        { time: "10:30", type: "Follow Up", name: "Mansoor", date: "2024-08-16" },
        { time: "12:30", type: "Consultancy", name: "Virat", date: "2024-08-16" },
        { time: "14:30", type: "OPD", name: "Surya Kumar", date: "2024-08-17" },
        { time: "16:30", type: "Treatment", name: "Narendra", date: "2024-08-17" },
        { time: "09:00", type: "Consultancy", name: "Rohit", date: "2024-08-16" }
    ];

    const form = document.querySelector("viewAppointmentsForm");
    const appointmentList = document.querySelector(".AppointmentList");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const appointmentDate = document.getElementById("appointmentDate").value;
        const appointmentType = document.getElementById("appointmentType").value;

        const filteredAppointments = appointments.filter(appointment => {
            return (appointmentType === "All" || appointment.type.toLowerCase() === appointmentType.toLowerCase()) &&
                (!appointmentDate || appointment.date === appointmentDate);
        });

        displayAppointments(filteredAppointments);
    });

    function displayAppointments(filteredAppointments) {
        appointmentList.innerHTML = "";

        if (filteredAppointments.length === 0) {
            appointmentList.innerHTML = "<p>No appointments found.</p>";
            return;
        }

        let appointmentListRow;

        filteredAppointments.forEach((appointment, index) => {
            if (index % 2 === 0) {
                appointmentListRow = document.createElement("div");
                appointmentListRow.className = "flex w-full mb-4 justify-between";
                appointmentList.appendChild(appointmentListRow);
            }

            const appointmentCard = document.createElement("div");
            appointmentCard.className = "bg-white border border-black rounded-xl p-4 w-5/12 mb-4 box-border";

            appointmentCard.innerHTML = `
                <div class="font-semibold">${appointment.time} | ${appointment.type}</div>
                <div>${appointment.name}</div>
                <div class="text-blue-500 cursor-pointer">View</div>
            `;

            appointmentListRow.appendChild(appointmentCard);
        });
    }
};
