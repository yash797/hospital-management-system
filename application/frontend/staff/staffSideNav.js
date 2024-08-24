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

          // Initialize forms only if they exist on the loaded page
          if (url.includes('patientRegistration.html')) {
              document.getElementById('registerButton').addEventListener('click', registerPatient);
          }
          if (url.includes('doctorSchedule.html')) {
              initSchedule();
          }
      })
      .catch(error => console.error('Error fetching page content:', error));
}


function registerPatient() {
    const firstName = document.getElementById('firstName').value;
    const middleName = document.getElementById('middleName').value
    const lastName = document.getElementById('lastName').value;
    const gender = document.getElementById('gender').value;   

    const email = document.getElementById('email').value;
    const mobile = document.getElementById('mobile').value;   

    const address = document.getElementById('address').value;
    const dob = document.getElementById('dob').value;   


    if (!firstName || !lastName || !email || !mobile || !address || !dob) {
      alert('Please fill in all fields.');
      return;
    }

    // Generate a new patient ID
    const patientID = 'P' + Math.floor(Math.random() * 1000000);

    // Create patient object
    const patient = {
      patientID,
      firstName,
      middleName,
      lastName,
      gender,
      email,
      mobile,
      address,
      dob
    };

    // Save patient to localStorage
    localStorage.setItem('registeredPatient', JSON.stringify(patient));

    // Update patient ID in doctor schedule page (if open)
    if (window.opener && window.opener.updatePatientID) {
      window.opener.updatePatientID(patientID);
      window.close();
    } else {
      alert(`Patient registered successfully! ID: ${patientID}`);
      document.getElementById('registrationForm').reset();
    }
  }

  function initSchedule() {
    const doctorName = document.getElementById('doctorName').value;
    const appointmentDate = document.getElementById('appointmentDate').value;
    if (doctorName && appointmentDate) {
        checkSchedule();
    }
}
const maxAppointments = 10;
function getPatientData() {
  return JSON.parse(localStorage.getItem('registeredPatient')) || { patientID: 'P000', firstName: 'John', lastName: 'Doe' };
}
function getAppointmentList() {
  return JSON.parse(localStorage.getItem('appointmentList')) || [];
}

function saveAppointmentList(appointments) {
  localStorage.setItem('appointmentList', JSON.stringify(appointments));
}


function checkSchedule() {
  const doctorName = document.getElementById('doctorName').value;
  const appointmentDate = document.getElementById('appointmentDate').value;

  if (!doctorName || !appointmentDate) {
      alert('Please select doctor and date.');
      return;
  }

  const patient = getPatientData();
  document.getElementById('patientID').value = patient.patientID;
  document.getElementById('patientName').value = `${patient.firstName} ${patient.middleName} ${patient.lastName}`;

  const appointmentList = getAppointmentList();
  const doctorAppointments = appointmentList.filter(
      app => app.doctorName === doctorName && app.date === appointmentDate
  );

  // Display schedule details
  document.getElementById('scheduleDetails').innerHTML = `
      <h3 class="text-xl font-semibold">Doctor: ${doctorName}</h3>
      <p>Date: ${appointmentDate}</p>
      <p>Specialization: General</p>
      <h4 class="text-lg font-semibold mt-4">Appointments:</h4>
      <ul id="appointmentsList" class="list-disc pl-5">
          ${doctorAppointments.length
              ? doctorAppointments
                  .map(
                      app => `
                          <li>Patient ID: ${app.patientId}, Name: ${app.patientName}, Time Slot: ${app.timeSlot}, Reason: ${app.reason}</li>`
                  )
                  .join('')
              : '<li>No appointments booked.</li>'
          }
      </ul>
  `;

  updateTimeSlots(doctorAppointments);

  document.getElementById('appointmentForm').classList.toggle(
      'hidden',
      doctorAppointments.length >= maxAppointments
  );
  document.getElementById('selectedDoctorName').value = doctorName;
  document.getElementById('selectedDate').value = appointmentDate;
}

function updateTimeSlots(bookedAppointments) {
  const timeSlotSelect = document.getElementById('timeSlot');
  timeSlotSelect.innerHTML = '';
  const startTime = 9; // Starting at 9 AM
  const endTime = 17; // Ending at 5 PM
  const bookedSlots = bookedAppointments.map(app => app.timeSlot);

  for (let hour = startTime; hour < endTime; hour++) {
      const start = `${hour}:00`;
      const end = `${hour}:30`;
      const timeString = `${start} - ${end}`;

      if (!bookedSlots.includes(timeString)) {
          const option = document.createElement('option');
          option.value = timeString;
          option.text = timeString;
          timeSlotSelect.appendChild(option);
      }
  }
  document.getElementById('endTimeDisplay').textContent = '';
}

function bookAppointment() {
  const patientID = document.getElementById('patientID').value;
  const doctorName = document.getElementById('selectedDoctorName').value;
  const appointmentDate = document.getElementById('selectedDate').value;
  const timeSlot = document.getElementById('timeSlot').value;
  const reason = document.getElementById('appointmentReason').value;

  if (!patientID || !doctorName || !appointmentDate || !timeSlot || !reason) {
      alert('Please fill in all fields.');
      return;
  }

  const appointmentList = getAppointmentList();
  if (appointmentList.some(app => app.doctorName === doctorName && app.date === appointmentDate && app.timeSlot === timeSlot)) {
      alert('This time slot is already booked.');
      return;
  }

  appointmentList.push({
      patientId: patientID,
      doctorName: doctorName,
      date: appointmentDate,
      timeSlot: timeSlot,
      reason: reason,
      patientName: document.getElementById('patientName').value
  });

  saveAppointmentList(appointmentList);

  // Refresh schedule after booking
  checkSchedule();
}




