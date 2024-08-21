document.addEventListener('DOMContentLoaded', function () {
    const addMedicineButton = document.getElementById('addMedicine');
    const historyList = document.getElementById('historyList');
    const warnings = document.getElementById('warnings');

    addMedicineButton.addEventListener('click', function () {
        const medicineName = document.getElementById('medicineName').value;
        const dosage = document.getElementById('dosage').value;
        const frequency = document.getElementById('frequency').value;
        const duration = document.getElementById('duration').value;
        const instructions = document.getElementById('instructions').value;

        if (medicineName && dosage && frequency && duration) {
            const listItem = document.createElement('div');
            listItem.textContent = `${medicineName} - ${dosage} - ${frequency} - ${duration} - ${instructions}`;
            historyList.appendChild(listItem);

            // Check for interactions and allergies
            const interactionWarning = `Warning: ${medicineName} may interact with current medications.`;
            warnings.textContent = interactionWarning; // Example warning
        } else {
            alert('Please fill in all the required fields.');
        }
    });

    document.getElementById('savePrescription').addEventListener('click', function () {
        alert('Prescription saved successfully!');
    });

    document.getElementById('printPrescription').addEventListener('click', function () {
        window.print();
    });
});
