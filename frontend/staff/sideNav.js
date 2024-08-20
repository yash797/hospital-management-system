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
            registerPatient();
        })
        .catch(error => console.error('Error fetching page content:', error));
}

function registerPatient() {
    const firstName = document.getElementById('firstName').value;
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


