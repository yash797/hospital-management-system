// sideNav.js

document.addEventListener("DOMContentLoaded", () => {
  const navLinks = document.querySelectorAll(".side-nav a");

  navLinks.forEach((link) => {
    link.addEventListener("click", (event) => {
      event.preventDefault();
      const pageUrl = link.getAttribute("href");
      fetchPageContent(pageUrl);
    });
  });
});

function fetchPageContent(url) {
  fetch(url)
    .then((response) => response.text())
    .then((html) => {
      document.querySelector("#main-content").innerHTML = html;
      initializePatientTable();
    })
    .catch((error) => console.error("Error fetching page content:", error));
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
                        <td class="py-2 px-4 border-b text-[#444444]">${
                          patient.patient_id
                        }</td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <input type="text" class="w-full border-gray-300 rounded-md hidden" value="${
                              patient.FullName
                            }" data-field="FullName" />
                            <span class="edit-cell">${patient.FullName}</span>
                        </td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <input type="date" class="w-full border-gray-300 rounded-md hidden" value="${
                              patient.date_of_birth
                            }" data-field="date_of_birth" />
                            <span class="edit-cell">${
                              patient.date_of_birth
                            }</span>
                        </td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <select class="w-full border-gray-300 rounded-md hidden" data-field="gender">
                                <option value="MALE" ${
                                  patient.gender === "MALE" ? "selected" : ""
                                }>MALE</option>
                                <option value="FEMALE" ${
                                  patient.gender === "FEMALE" ? "selected" : ""
                                }>FEMALE</option>
                                <option value="OTHER" ${
                                  patient.gender === "OTHER" ? "selected" : ""
                                }>OTHER</option>
                            </select>
                            <span class="edit-cell">${patient.gender}</span>
                        </td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <input type="text" class="w-full border-gray-300 rounded-md hidden" value="${
                              patient.disease
                            }" data-field="disease" />
                            <span class="edit-cell">${patient.disease}</span>
                        </td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <input type="text" class="w-full border-gray-300 rounded-md hidden" value="${
                              patient.DoctorAppointed
                            }" data-field="DoctorAppointed" />
                            <span class="edit-cell">${
                              patient.DoctorAppointed
                            }</span>
                        </td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <input type="date" class="w-full border-gray-300 rounded-md hidden" value="${
                              patient.AppointmentDate
                            }" data-field="AppointmentDate" />
                            <span class="edit-cell">${
                              patient.AppointmentDate
                            }</span>
                        </td>
                        <td class="py-2 px-4 border-b text-[#444444]">
                            <button class="edit-button text-blue-600">
                            <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="30" viewBox="0,0,300,150" style="fill:#40C057;">
                            <g fill="#40c057" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><g transform="scale(2,2)"><path d="M79.33594,15.66797c-1.27148,-0.0457 -2.56094,0.09414 -3.83594,0.43164c-3.4,0.9 -6.20039,3.09961 -7.90039,6.09961l-3.59961,6.5c-0.8,1.4 -0.30039,3.30156 1.09961,4.10156l17.30078,10c0.5,0.3 1,0.39844 1.5,0.39844c0.3,0 0.49883,0.00039 0.79883,-0.09961c0.8,-0.2 1.40078,-0.70039 1.80078,-1.40039l3.69922,-6.5c1.7,-3 2.20078,-6.49844 1.30078,-9.89844c-0.9,-3.4 -3.09961,-6.20039 -6.09961,-7.90039c-1.875,-1.0625 -3.94531,-1.65625 -6.06445,-1.73242zM60.09766,38.12695c-0.96875,0.07422 -1.89844,0.59766 -2.39844,1.47266l-30.19922,52.40039c-3.4,5.8 -5.29922,12.30039 -5.69922,18.90039l-0.80078,12.90039c-0.1,1.1 0.5,2.19883 1.5,2.79883c0.5,0.3 1,0.40039 1.5,0.40039c0.6,0 1.19922,-0.2 1.69922,-0.5l10.80078,-7.09961c5.5,-3.7 10.2,-8.60039 13.5,-14.40039l30.30078,-52.40039c0.8,-1.4 0.29844,-3.29961 -1.10156,-4.09961c-1.4,-0.8 -3.29961,-0.30039 -4.09961,1.09961l-30.29883,52.40039c-2.9,4.9 -6.90117,9.20039 -11.70117,12.40039l-5.79883,3.79883l0.39844,-6.89844c0.3,-5.7 2.00039,-11.30078 4.90039,-16.30078l30.30078,-52.40039c0.8,-1.4 0.30039,-3.29961 -1.09961,-4.09961c-0.525,-0.3 -1.12188,-0.41758 -1.70312,-0.37305zM49,121c-1.7,0 -3,1.3 -3,3c0,1.7 1.3,3 3,3h40c1.7,0 3,-1.3 3,-3c0,-1.7 -1.3,-3 -3,-3zM104,121c-1.65685,0 -3,1.34315 -3,3c0,1.65685 1.34315,3 3,3c1.65685,0 3,-1.34315 3,-3c0,-1.65685 -1.34315,-3 -3,-3z"></path></g></g>
                            </svg>
                            </button>
                            <button class="delete-button text-red-600">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true"><path d="M4 6V4a1 1 0 011-1h10a1 1 0 011 1v2h4v1H1V6h3zM4 8h12v9H4V8z" /></svg>
                            </button>
                        </td>
                    `;
          tableBody.appendChild(row);
        });

        pageInfo.textContent = `Page ${currentPage} of ${Math.ceil(
          data.length / rowsPerPage
        )}`;

        prevPageButton.disabled = currentPage === 1;
        nextPageButton.disabled =
          currentPage === Math.ceil(data.length / rowsPerPage);

        attachEventListeners();
      }

      function attachEventListeners() {
        document.querySelectorAll(".edit-button").forEach((button) => {
          button.addEventListener("click", function () {
            const row = this.closest("tr");
            const inputs = row.querySelectorAll("input, select");
            const spanElements = row.querySelectorAll(".edit-cell");

            if (this.classList.contains("text-blue-600")) {
              this.innerHTML = `
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#006B82" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-save">
    <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
    <polyline points="17 21 17 13 7 13 7 21"></polyline>
    <polyline points="7 3 7 8 15 8"></polyline>
  </svg>
                            `;
              inputs.forEach((input) => input.classList.remove("hidden"));
              spanElements.forEach((span) => span.classList.add("hidden"));
              this.classList.remove("text-blue-600");
              this.classList.add("text-green-600");
            } else {
              const index = row.getAttribute("data-index");
              const patient = data[index];
              const updatedData = {};
              inputs.forEach((input) => {
                updatedData[input.getAttribute("data-field")] = input.value;
              });
              data[index] = { ...patient, ...updatedData };

              this.innerHTML = `
                                <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="30" viewBox="0,0,300,150" style="fill:#40C057;">
                            <g fill="#40c057" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><g transform="scale(2,2)"><path d="M79.33594,15.66797c-1.27148,-0.0457 -2.56094,0.09414 -3.83594,0.43164c-3.4,0.9 -6.20039,3.09961 -7.90039,6.09961l-3.59961,6.5c-0.8,1.4 -0.30039,3.30156 1.09961,4.10156l17.30078,10c0.5,0.3 1,0.39844 1.5,0.39844c0.3,0 0.49883,0.00039 0.79883,-0.09961c0.8,-0.2 1.40078,-0.70039 1.80078,-1.40039l3.69922,-6.5c1.7,-3 2.20078,-6.49844 1.30078,-9.89844c-0.9,-3.4 -3.09961,-6.20039 -6.09961,-7.90039c-1.875,-1.0625 -3.94531,-1.65625 -6.06445,-1.73242zM60.09766,38.12695c-0.96875,0.07422 -1.89844,0.59766 -2.39844,1.47266l-30.19922,52.40039c-3.4,5.8 -5.29922,12.30039 -5.69922,18.90039l-0.80078,12.90039c-0.1,1.1 0.5,2.19883 1.5,2.79883c0.5,0.3 1,0.40039 1.5,0.40039c0.6,0 1.19922,-0.2 1.69922,-0.5l10.80078,-7.09961c5.5,-3.7 10.2,-8.60039 13.5,-14.40039l30.30078,-52.40039c0.8,-1.4 0.29844,-3.29961 -1.10156,-4.09961c-1.4,-0.8 -3.29961,-0.30039 -4.09961,1.09961l-30.29883,52.40039c-2.9,4.9 -6.90117,9.20039 -11.70117,12.40039l-5.79883,3.79883l0.39844,-6.89844c0.3,-5.7 2.00039,-11.30078 4.90039,-16.30078l30.30078,-52.40039c0.8,-1.4 0.30039,-3.29961 -1.09961,-4.09961c-0.525,-0.3 -1.12188,-0.41758 -1.70312,-0.37305zM49,121c-1.7,0 -3,1.3 -3,3c0,1.7 1.3,3 3,3h40c1.7,0 3,-1.3 3,-3c0,-1.7 -1.3,-3 -3,-3zM104,121c-1.65685,0 -3,1.34315 -3,3c0,1.65685 1.34315,3 3,3c1.65685,0 3,-1.34315 3,-3c0,-1.65685 -1.34315,-3 -3,-3z"></path></g></g>
                            </svg>
                            `;
              inputs.forEach((input) => input.classList.add("hidden"));
              spanElements.forEach((span) => span.classList.remove("hidden"));
              this.classList.remove("text-green-600");
              this.classList.add("text-blue-600");
            }
          });
        });

        document.querySelectorAll(".delete-button").forEach((button) => {
          button.addEventListener("click", function () {
            const index = this.closest("tr").getAttribute("data-index");
            data.splice(index, 1);
            renderTable();
          });
        });
      }

      prevPageButton.addEventListener("click", function () {
        if (currentPage > 1) {
          currentPage--;
          renderTable();
        }
      });

      nextPageButton.addEventListener("click", function () {
        if (currentPage < Math.ceil(data.length / rowsPerPage)) {
          currentPage++;
          renderTable();
        }
      });

      renderTable();
    });
}
