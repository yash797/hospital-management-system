// Medication history array
const medicationHistory = [];

document.getElementById('addMedicine').addEventListener('click', () => {
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
});

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
