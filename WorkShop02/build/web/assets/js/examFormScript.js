// assets/js/examForm.js

/**
 * Deletes a question from the form.
 * @param {HTMLElement} button - The delete button clicked.
 */
function deleteQuestion(button) {
    const table = button.closest("table");
    if (table) {
        table.remove();
    }
}

/**
 * Adds a new question block to the form.
 */
function addQuestion() {
    const container = document.getElementById("question-container");
    const table = document.createElement("table");
    table.classList.add("question-table"); // Add the class for styling
    table.innerHTML = `
        <input type="hidden" name="questionId" value="-1" />
        <tr>
            <td colspan="4">
                <label>Câu hỏi:</label>
                <input type="text" name="questionText" required>
            </td>
        </tr>
        <tr>
            <td>
                <label>A:</label>
                <input type="text" name="optionA" required>
            </td>
            <td rowspan="4" class="spacer-col"></td>
            <td rowspan="4">
                <label>Đáp án đúng:</label><br>
                <select name="correctOption" required>
                    <option value="">--Chọn--</option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select>
            </td>
            <td rowspan="4">
                <button type="button" onclick="deleteQuestion(this)" class="btn btn-secondary">Xóa</button>
            </td>
        </tr>
        <tr>
            <td>
                <label>B:</label>
                <input type="text" name="optionB" required>
            </td>
        </tr>
        <tr>
            <td>
                <label>C:</label>
                <input type="text" name="optionC" required>
            </td>
        </tr>
        <tr>
            <td>
                <label>D:</label>
                <input type="text" name="optionD" required>
            </td>
        </tr>
    `;
    container.appendChild(table);
}

/**
 * Handles DOMContentLoaded event to show toast messages and fix select element selections.
 */
window.addEventListener("DOMContentLoaded", function () {
    const toast = document.getElementById("toast");
    // Check if toast element exists and its text content is not empty or just whitespace
    if (toast && toast.textContent.trim() !== "") {
        const message = toast.textContent.trim();
        // Check for specific success messages to show the toast
        if (message === "Create Exam Successfully!" || message === "Update Exam Successfully!") {
            toast.classList.add("show");
            setTimeout(() => {
                toast.classList.remove("show");
                toast.textContent = ""; // Clear the text after hiding to prevent re-showing on subsequent loads
            }, 3000);
        } else {
            // If it's not a recognized success message, hide it (e.g., if there's an errorMsg which is shown via alert)
            toast.style.display = "none";
        }
    }

    // Fix for select elements not having selected option on load if value is empty
    const selects = document.querySelectorAll('select[name="examCategoryId"], select[name="correctOption"]');
    selects.forEach(select => {
        if (select.value === "") {
            // Ensure the disabled option is selected if value is empty
            const defaultOption = select.querySelector('option[value=""][disabled]');
            if (defaultOption) {
                defaultOption.selected = true;
            }
        }
    });
});