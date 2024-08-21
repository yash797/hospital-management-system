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
            document.querySelector('#main-content').innerHTML = html;
            initializePatientTable();
        })
        .catch(error => console.error('Error fetching page content:', error));
}


function initializePatientTable() {
    fetch("patient.json")
        .then((response) => response.json())
        .then((data) => {
            const tableBody = document.querySelector("#patient-table tbody");
            const prevPageButton = document.querySelector("#prev-page");
            const nextPageButton = document.querySelector("#next-page");
            const pageInfo = document.querySelector("#page-info");
            
            let currentPage = 1;
            const rowsPerPage = 10;

            function renderTable() {
                tableBody.innerHTML = "";
                const start = (currentPage - 1) * rowsPerPage;
                const end = start + rowsPerPage;
                const pageData = data.slice(start, end);

                pageData.forEach((patient, index) => {
                    const row = document.createElement("tr");
                    row.classList.add("text-center");
                    row.setAttribute("data-index", start + index);
                    row.innerHTML = `
                        <td class="py-2 px-4 border-b">${patient.patient_id}</td>
                        <td class="py-2 px-4 border-b">
                            <input type="text" class="w-full border-gray-300 rounded-md hidden" value="${patient.FullName}" data-field="FullName" />
                            <span class="edit-cell">${patient.FullName}</span>
                        </td>
                        <td class="py-2 px-4 border-b">
                            <input type="date" class="w-full border-gray-300 rounded-md hidden" value="${patient.date_of_birth}" data-field="date_of_birth" />
                            <span class="edit-cell">${patient.date_of_birth}</span>
                        </td>
                        <td class="py-2 px-4 border-b">
                            <select class="w-full border-gray-300 rounded-md hidden" data-field="gender">
                                <option value="MALE" ${patient.gender === 'MALE' ? 'selected' : ''}>MALE</option>
                                <option value="FEMALE" ${patient.gender === 'FEMALE' ? 'selected' : ''}>FEMALE</option>
                                <option value="OTHER" ${patient.gender === 'OTHER' ? 'selected' : ''}>OTHER</option>
                            </select>
                            <span class="edit-cell">${patient.gender}</span>
                        </td>
                        <td class="py-2 px-4 border-b">
                            <input type="text" class="w-full border-gray-300 rounded-md hidden" value="${patient.disease}" data-field="disease" />
                            <span class="edit-cell">${patient.disease}</span>
                        </td>
                        <td class="py-2 px-4 border-b">
                            <input type="text" class="w-full border-gray-300 rounded-md hidden" value="${patient.DoctorAppointed}" data-field="DoctorAppointed" />
                            <span class="edit-cell">${patient.DoctorAppointed}</span>
                        </td>
                        <td class="py-2 px-4 border-b">
                            <input type="date" class="w-full border-gray-300 rounded-md hidden" value="${patient.AppointmentDate}" data-field="AppointmentDate" />
                            <span class="edit-cell">${patient.AppointmentDate}</span>
                        </td>
                        <td class="py-2 px-4 border-b">
                            <button class="edit-button text-blue-600">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true"><path d="M13.707 2.293a1 1 0 00-1.414 0L11 3.586 14.414 7 15 6.414 13.707 2.293zM5.207 10.207l.707.707a1 1 0 01-.707.293H3a1 1 0 01-1-1V8a1 1 0 01.293-.707l.707-.707a1 1 0 01.707-.293h2.414a1 1 0 01.707.293zM5 4a1 1 0 00-.707.293L3.293 5.293A1 1 0 003 6v2a1 1 0 00.293.707l.707.707V14a1 1 0 001 1h4a1 1 0 001-1V8.707l.707-.707A1 1 0 0010 6V4a1 1 0 00-1-1H6a1 1 0 00-1 1zM9 2a1 1 0 01-1-1V1a1 1 0 112 0v.5a1 1 0 01-1 1z" /></svg>
                            </button>
                            <button class="delete-button text-red-600">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true"><path d="M4 6V4a1 1 0 011-1h10a1 1 0 011 1v2h4v1H1V6h3zM4 8h12v9H4V8z" /></svg>
                            </button>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });

                pageInfo.textContent = `Page ${currentPage} of ${Math.ceil(data.length / rowsPerPage)}`;

                prevPageButton.disabled = currentPage === 1;
                nextPageButton.disabled = currentPage === Math.ceil(data.length / rowsPerPage);

                attachEventListeners();
            }

            function attachEventListeners() {
                document.querySelectorAll('.edit-button').forEach(button => {
                    button.addEventListener('click', function() {
                        const row = this.closest('tr');
                        const inputs = row.querySelectorAll('input, select');
                        const spanElements = row.querySelectorAll('.edit-cell');

                        if (this.classList.contains('text-blue-600')) {
                            this.innerHTML = `
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                    <path d="M5 1a1 1 0 011 1v.5a1 1 0 01-1 1h-.5a1 1 0 01-1-1V2a1 1 0 011-1h.5zm9.5 0a1 1 0 011 1v.5a1 1 0 01-1 1h-.5a1 1 0 01-1-1V2a1 1 0 011-1h.5zM5 4h10v1H5V4zm0 2h10v1H5V6zm-3 2h16v8H2V8zM1 9h1v8a1 1 0 001 1h12a1 1 0 001-1V9h1v8H1V9z" />
                                </svg>
                            `;
                            inputs.forEach(input => input.classList.remove('hidden'));
                            spanElements.forEach(span => span.classList.add('hidden'));
                            this.classList.remove('text-blue-600');
                            this.classList.add('text-green-600');
                        } else {
                            const index = row.getAttribute('data-index');
                            const patient = data[index];
                            const updatedData = {};
                            inputs.forEach(input => {
                                updatedData[input.getAttribute('data-field')] = input.value;
                            });
                            data[index] = { ...patient, ...updatedData };

                            this.innerHTML = `
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                    <path d="M5 1a1 1 0 011 1v.5a1 1 0 01-1 1h-.5a1 1 0 01-1-1V2a1 1 0 011-1h.5zm9.5 0a1 1 0 011 1v.5a1 1 0 01-1 1h-.5a1 1 0 01-1-1V2a1 1 0 011-1h.5zM5 4h10v1H5V4zm0 2h10v1H5V6zm-3 2h16v8H2V8zM1 9h1v8a1 1 0 001 1h12a1 1 0 001-1V9h1v8H1V9z" />
                                </svg>
                            `;
                            inputs.forEach(input => input.classList.add('hidden'));
                            spanElements.forEach(span => span.classList.remove('hidden'));
                            this.classList.remove('text-green-600');
                            this.classList.add('text-blue-600');
                        }
                    });
                });

                document.querySelectorAll('.delete-button').forEach(button => {
                    button.addEventListener('click', function() {
                        const index = this.closest('tr').getAttribute('data-index');
                        data.splice(index, 1);
                        renderTable();
                    });
                });
            }

            prevPageButton.addEventListener('click', function() {
                if (currentPage > 1) {
                    currentPage--;
                    renderTable();
                }
            });

            nextPageButton.addEventListener('click', function() {
                if (currentPage < Math.ceil(data.length / rowsPerPage)) {
                    currentPage++;
                    renderTable();
                }
            });

            renderTable();
        });
}