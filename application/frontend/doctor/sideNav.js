// sideNav.js

document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('.side-nav a');
    
    navLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const pageUrl = link.getAttribute('href');
            fetchPageContent(pageUrl);
        });
    });
});

function fetchPageContent(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.querySelector('.content').innerHTML = html;
            viewAppointmentSubmit();
        })
        .catch(error => console.error('Error fetching page content:', error));
}


function doctorSection() {
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

// document.addEventListener("DOMContentLoaded", function () {
    function viewAppointmentSubmit() {
        const appointments = [
            { time: "10:30", type: "Follow Up", name: "Mansoor", date: "2024-08-16" },
            { time: "12:30", type: "Consultancy", name: "Virat", date: "2024-08-16" },
            { time: "14:30", type: "OPD", name: "Surya Kumar", date: "2024-08-17" },
            { time: "16:30", type: "Treatment", name: "Narendra", date: "2024-08-17" },
            { time: "09:00", type: "Consultancy", name: "Rohit", date: "2024-08-16" }
        ];
    
        const form = document.querySelector("form");
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

// Medication history array
const medicationHistory = [];

function viewMedication() {
    document.getElementById('addMedicine').addEventListener('click', addMedicine);
}

function addMedicine() {
    // Fetch values
    const medicineName = document.getElementById('medicineName').value.trim();
    const dosage = document.getElementById('dosage').value.trim();
    const frequency = document.getElementById('frequency').value.trim();
    const duration = document.getElementById('duration').value.trim();
    const instructions = document.getElementById('instructions').value.trim();

    // Clear warnings
    document.getElementById('warnings').textContent = '';

    // Validate input
    if (!medicineName || !dosage || !frequency || !duration) {
        document.getElementById('warnings').textContent = 'All fields except Additional Instructions are required.';
        return;
    }

    // Check for duplicate medication
    const isDuplicate = medicationHistory.some(
        (med) => med.medicineName === medicineName && med.dosage === dosage
    );

    if (isDuplicate) {
        document.getElementById('warnings').textContent = 'This medication has already been added.';
        return;
    }

    // Create medication object
    const medication = {
        medicineName,
        dosage,
        frequency,
        duration,
        instructions,
    };

    // Add to history
    medicationHistory.push(medication);

    // Clear form
    document.getElementById('prescriptionForm').reset();

    // Render history
    renderMedicationHistory();
}

function renderMedicationHistory() {
    const historyList = document.getElementById('historyList');
    historyList.innerHTML = '';

    medicationHistory.forEach((med, index) => {
        const medDiv = document.createElement('div');
        medDiv.className = 'flex justify-between items-center p-2 border-b last:border-b-0';

        const medDetails = document.createElement('div');
        medDetails.innerHTML = `<strong>${med.medicineName}</strong> (${med.dosage}), ${med.frequency}, for ${med.duration}<br>${med.instructions}`;

        const removeButton = document.createElement('button');
        removeButton.className = 'text-red-500 hover:text-red-700';
        removeButton.textContent = 'Remove';
        removeButton.addEventListener('click', () => {
            medicationHistory.splice(index, 1);
            renderMedicationHistory();
        });

        medDiv.appendChild(medDetails);
        medDiv.appendChild(removeButton);
        historyList.appendChild(medDiv);
    });
}

function addTest() {
    const selectTest = document.getElementById("selectTest");
    const testList = document.getElementById("testList");

    if (selectTest.value !== "Select a Test" && selectTest.value !== "") {
        const testItem = document.createElement("div");
        testItem.className = "p-2 bg-gray-100 rounded-lg shadow-md flex justify-between items-center";
        testItem.innerHTML = `
        <span>${selectTest.options[selectTest.selectedIndex].text}</span>
            <img src="../assets/cancel.svg" onclick="removeTest(this)" class="w-6 h-6 cursor-pointer">
        `;

        testList.appendChild(testItem);
        selectTest.selectedIndex = 0;
    }
}

function removeTest(element) {
    element.parentElement.remove();
}








