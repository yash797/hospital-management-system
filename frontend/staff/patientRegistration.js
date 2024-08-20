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