function addTest() {
    const selectTest = document.getElementById("selectTest");
    const testList = document.getElementById("testList");

    if (selectTest.value !== "Select a Test" && selectTest.value !== "") {
        const testItem = document.createElement("div");
        testItem.className = "p-2 bg-gray-100 rounded-lg shadow-md flex justify-between items-center";

        testItem.innerHTML = `
            <img src="application/frontend/assets/cancel-svgrepo-com.svg" onclick="removeTest(this)" class="w-6 h-6 cursor-pointer">
            <span>${selectTest.options[selectTest.selectedIndex].text}</span>
        `;

        testList.appendChild(testItem);
        selectTest.selectedIndex = 0;
    }
}

function removeTest(element) {
    element.parentElement.remove();
}
