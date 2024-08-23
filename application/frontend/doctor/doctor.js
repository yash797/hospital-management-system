function initializePage() {
    document.addEventListener('DOMContentLoaded', function() {
        const navCheck = document.getElementById('navcheck');
        const sidebar = document.getElementById('sidebar');
        const mainContent = document.getElementById('main-content');
        const hamburger = document.getElementById('hamburger');

        if(navCheck && sidebar && mainContent && hamburger) {
            navCheck.addEventListener('change', function() {
                if (this.checked) {
                    sidebar.style.transform = 'translateX(0)';
                    mainContent.style.transform = 'translateX(5rem)'; // Sidebar width
                    mainContent.style.filter = 'blur(4.75px)';

                    // Transform hamburger to cross
                    hamburger.children[0].style.transform = 'rotate(45deg) translateY(7px)';
                    hamburger.children[1].style.opacity = '0';
                    hamburger.children[2].style.transform = 'rotate(-45deg) translateY(-7px)';
                } else {
                    sidebar.style.transform = 'translateX(-100%)';
                    mainContent.style.transform = 'translateX(0)';
                    mainContent.style.filter = 'none';

                    // Transform cross back to hamburger
                    hamburger.children[0].style.transform = 'rotate(0) translateY(0)';
                    hamburger.children[1].style.opacity = '1';
                    hamburger.children[2].style.transform = 'rotate(0) translateY(0)';
                }
            });

            document.addEventListener('click', function(event) {
                if (
                    sidebar.style.transform === 'translateX(0px)' &&
                    !sidebar.contains(event.target) &&
                    !hamburger.contains(event.target) &&
                    !navCheck.contains(event.target)
                ) {
                    navCheck.checked = false;
                    sidebar.style.transform = 'translateX(-100%)';
                    mainContent.style.transform = 'translateX(0)';
                    mainContent.style.filter = 'none';

                    hamburger.children[0].style.transform = 'rotate(0) translateY(0)';
                    hamburger.children[1].style.opacity = '1';
                    hamburger.children[2].style.transform = 'rotate(0) translateY(0)';
                }
            });

            const navItems = document.querySelectorAll('.nav-item');
            navItems.forEach((item) => {
                item.addEventListener('click', function() {
                    navItems.forEach((nav) => nav.classList.remove('nav-item-active'));
                    this.classList.add('nav-item-active');
                });
            });

            const appointments = [
                { time: "10:30", type: "Follow Up", name: "Mansoor", date: "2024-08-16" },
                { time: "12:30", type: "Consultancy", name: "Virat", date: "2024-08-16" },
                { time: "14:30", type: "OPD", name: "Surya Kumar", date: "2024-08-17" },
                { time: "16:30", type: "Treatment", name: "Narendra", date: "2024-08-17" },
                { time: "09:00", type: "Consultancy", name: "Rohit", date: "2024-08-16" }
            ];

            const form = document.querySelector("form");
            const appointmentList = document.querySelector(".AppointmentList");

            if(form && appointmentList) {
                form.addEventListener("submit", function(event) {
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
            }
        }
    });
}

// Call the function to initialize the page
initializePage();
