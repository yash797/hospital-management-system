// admin-functions.js
//show-patients

// Handle file upload and import users
function importUsers() {
  const fileInput = document.getElementById("fileInput").files[0];
  if (!fileInput) {
    alert("Please select a file to upload.");
    return;
  }

  const fileReader = new FileReader();
  fileReader.onload = function (event) {
    const fileContent = event.target.result;
    // Simulate file processing
    if (fileInput.name.endsWith(".json")) {
      try {
        const data = JSON.parse(fileContent);
        console.log("JSON Data:", data);
        // Process JSON data
        alert("JSON file processed successfully.");
      } catch (error) {
        alert("Invalid JSON file.");
      }
    } else if (fileInput.name.endsWith(".xml")) {
      // XML processing can be added here
      alert("XML file processing is not yet implemented.");
    } else {
      alert("Unsupported file type. Please upload a JSON or XML file.");
    }
  };

  fileReader.readAsText(fileInput);
}

// Handle appointment retrieval and display
function viewAppointments() {
  const date = document.getElementById("appointmentDate").value;
  if (!date) {
    alert("Please select a date.");
    return;
  }

  // Simulate fetching appointments from server or database
  console.log("Fetching appointments for date:", date);
  const appointments = [
    { id: "A001", patient: "Jane Doe", doctor: "Dr. Smith", time: "11:00 AM" },
    {
      id: "A002",
      patient: "Mark Henry",
      doctor: "Dr. Kohli",
      time: "12:00 AM",
    },
    { id: "A003", patient: "Undertaker", doctor: "Dr. Kane", time: "01:00 PM" },
    {
      id: "A004",
      patient: "John Cena",
      doctor: "Dr. Sharma",
      time: "09:00 AM",
    },
    {
      id: "A005",
      patient: "Randy Orton",
      doctor: "Dr. Root",
      time: "02:00 PM",
    },
    // Add more dummy data or fetch from server
  ];

  const appointmentResults = document.getElementById("appointmentResults");
  if (appointments.length > 0) {
    let rows = "";
    appointments.forEach((appointment) => {
      rows += `
                <tr class="text-center text-[#444444]">
                    <td class="py-2 px-4 border-b text-[#444444]">${appointment.id}</td>
                    <td class="py-2 px-4 border-b text-[#444444]">${appointment.patient}</td>
                    <td class="py-2 px-4 border-b text-[#444444]">${appointment.doctor}</td>
                    <td class="py-2 px-4 border-b text-[#444444]">${appointment.time}</td>
                    <td class="py-2 px-4 border-b">
                        <button class="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded-md">Cancel</button>
                    </td>
                </tr>
            `;
    });
    appointmentResults.innerHTML = `
            <h3 class="text-lg font-semibold mb-2">Appointments</h3>
            <table class="min-w-full bg-white border border-gray-300">
                <thead>
                    <tr class="">
                        <th class="py-2 px-4 border-b font-medium text-[#888888]">Appointment ID</th>
                        <th class="py-2 px-4 border-b font-medium text-[#888888]">Patient Name</th>
                        <th class="py-2 px-4 border-b font-medium text-[#888888]">Doctor Name</th>
                        <th class="py-2 px-4 border-b font-medium text-[#888888]">Time</th>
                        <th class="py-2 px-4 border-b font-medium text-[#888888]">Actions</th>
                    </tr>
                </thead>
                <tbody>${rows}</tbody>
            </table>
        `;
  } else {
    appointmentResults.innerHTML =
      "<p>No appointments available for the selected date.</p>";
  }
}

function filterPatients() {
  const filter = document.getElementById("searchPatient").value.toLowerCase();
  const rows = document.querySelectorAll("#patientTableBody tr");
  rows.forEach((row) => {
    const name = row.querySelector("td:nth-child(2)").textContent.toLowerCase();
    row.style.display = name.includes(filter) ? "" : "none";
  });
}

function toggleDoctorFields() {
  const roleSelect = document.getElementById("role");
  const doctorFields = document.getElementById("doctorFields");
  if (roleSelect.value === "Doctor") {
    doctorFields.style.display = "flex";
  } else {
    doctorFields.style.display = "none";
  }
}

function generateReport() {
  const reportType = document.getElementById("reportType").value;
  loadContent(reportType);
}

function loadContent(file) {
  fetch(file)
    .then((response) => response.text())
    .then((data) => {
      document.getElementById("reportResults").innerHTML = data;
      // if (file === "staffUtilizationReport.json") {
      //   staffUtilizationReport();
      // }
    })
    .catch((error) => console.error("Error loading content:", error));
}
function generateStaffUtilizationReport() {
  const Staffbutton = document.getElementById("generateStaff");
  Staffbutton.disabled = true;

  // Update the button's class
  Staffbutton.className =
    "bg-[#006B82] text-white px-4 py-2 rounded-md cursor-not-allowed";

  // Fetch data from staffUtilizationReport.json
  fetch("staffUtilizationReport.json")
    .then((response) => response.json())
    .then((data) => {
      const timePeriods = data.timePeriods;
      const staffUtilization = data.staffUtilization;

      // Define colors for each staff member
      const colors = [
        "rgba(255, 99, 132, 0.6)", // Red
        "rgba(54, 162, 235, 0.6)", // Blue
        "rgba(255, 206, 86, 0.6)", // Yellow
        "rgba(75, 192, 192, 0.6)", // Green
        "rgba(153, 102, 255, 0.6)", // Purple
      ];

      // Prepare datasets for Registered Patients
      const registeredPatientsData = staffUtilization.map((staff, index) => ({
        label: staff.name,
        data: timePeriods.map(
          (period) => staff.activities[period].registerPatients
        ),
        backgroundColor: colors[index],
        borderColor: colors[index],
        borderWidth: 1,
      }));

      // Prepare datasets for Booked Appointments
      const bookAppointmentsData = staffUtilization.map((staff, index) => ({
        label: staff.name,
        data: timePeriods.map(
          (period) => staff.activities[period].bookAppointments
        ),
        backgroundColor: colors[index],
        borderColor: colors[index],
        borderWidth: 1,
      }));

      // Create the Registered Patients Chart
      const ctxRegisteredPatients = document
        .getElementById("registeredPatientsChart")
        .getContext("2d");
      new Chart(ctxRegisteredPatients, {
        type: "bar",
        data: {
          labels: timePeriods,
          datasets: registeredPatientsData,
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "top",
              labels: {
                boxWidth: 15,
                padding: 10,
              },
            },
            tooltip: {
              callbacks: {
                label: function (context) {
                  let label = context.dataset.label || "";
                  if (label) {
                    label += ": ";
                  }
                  if (context.parsed.y !== null) {
                    label += context.parsed.y + " patients";
                  }
                  return label;
                },
              },
            },
          },
          scales: {
            x: {
              title: {
                display: true,
                text: "Time Period",
              },
            },
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: "Number of Registered Patients",
              },
            },
          },
        },
      });

      // Create the Booked Appointments Chart
      const ctxBookAppointments = document
        .getElementById("bookAppointmentsChart")
        .getContext("2d");
      new Chart(ctxBookAppointments, {
        type: "bar",
        data: {
          labels: timePeriods,
          datasets: bookAppointmentsData,
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "top",
              labels: {
                boxWidth: 15,
                padding: 10,
              },
            },
            tooltip: {
              callbacks: {
                label: function (context) {
                  let label = context.dataset.label || "";
                  if (label) {
                    label += ": ";
                  }
                  if (context.parsed.y !== null) {
                    label += context.parsed.y + " appointments";
                  }
                  return label;
                },
              },
            },
          },
          scales: {
            x: {
              title: {
                display: true,
                text: "Time Period",
              },
            },
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: "Number of Booked Appointments",
              },
            },
          },
        },
      });
    })
    .catch((error) => console.error("Error fetching data:", error));
}

function generatePatientStatistics() {
  const Staffbutton = document.getElementById("generateStaff");
  Staffbutton.disabled = true;

  // Update the button's class
  Staffbutton.className =
    "bg-[#006B82] text-white px-4 py-2 rounded-md cursor-not-allowed";
  fetch("patientStatistics.json")
    .then((response) => response.json())
    .then((data) => {
      const diagnosisTypes = data.patientStatistics.map(
        (item) => item.diagnosisType
      );
      const patientCounts = data.patientStatistics.map(
        (item) => item.patientCount
      );

      const ctx = document.getElementById("chart").getContext("2d");
      new Chart(ctx, {
        type: "pie",
        data: {
          labels: diagnosisTypes,
          datasets: [
            {
              label: "Diagnosis Types",
              backgroundColor: [
                "#FF6384",
                "#36A2EB",
                "#FFCE56",
                "#4BC0C0",
                "#9966FF",
              ],
              data: patientCounts,
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              position: "top",
              labels: {
                color: "#333",
              },
            },
            tooltip: {
              callbacks: {
                label: function (tooltipItem) {
                  const total = tooltipItem.chart.data.datasets[0].data.reduce(
                    (acc, val) => acc + val,
                    0
                  );
                  const currentValue = tooltipItem.raw;
                  const percentage = ((currentValue / total) * 100).toFixed(2);
                  return `${tooltipItem.label}: ${currentValue} patients (${percentage}%)`;
                },
              },
            },
            title: {
              display: true,
              text: "Patient Distribution by Diagnosis Type",
              font: {
                size: 16,
              },
            },
          },
        },
      });
    })
    .catch((error) =>
      console.error("Error loading patient statistics:", error)
    );
}

function generateDoctorAppointment() {
  // Fetch data from docReport.json
  fetch("docReport.json")
    .then((response) => response.json())
    .then((data) => {
      const ctx = document.getElementById("appointmentChart").getContext("2d");

      // Define a set of blue shades
      const blueShades = [
        "rgba(54, 162, 235, 0.6)", // Light Blue
        "rgba(75, 192, 192, 0.6)", // Soft Blue
        "rgba(153, 102, 255, 0.6)", // Medium Blue
        "rgba(0, 123, 255, 0.6)", // Strong Blue
        "rgba(0, 0, 255, 0.6)", // Deep Blue
      ];

      const chartData = {
        labels: data.days,
        datasets: data.appointments.map((appointment, index) => {
          return {
            label: appointment.doctor,
            data: data.days.map(
              (day) => appointment.appointments_count[day] || 0
            ),
            backgroundColor: blueShades[index % blueShades.length], // Cycle through the shades
          };
        }),
      };

      const appointmentChart = new Chart(ctx, {
        type: "bar",
        data: chartData,
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
              max: 12, // Set the maximum limit for the y-axis
            },
          },
          plugins: {
            legend: {
              position: "top",
            },
            tooltip: {
              callbacks: {
                label: function (context) {
                  let label = context.dataset.label || "";
                  if (label) {
                    label += ": ";
                  }
                  if (context.parsed.y !== null) {
                    label += context.parsed.y + " appointments";
                  }
                  return label;
                },
              },
            },
          },
        },
      });
    })
    .catch((error) => console.error("Error fetching data:", error));
}
