function showSuccessMessage() {
    const successMessage = document.getElementById('success-message');
    successMessage.classList.remove('hidden');
    successMessage.classList.add('show');

    setTimeout(() => {
        successMessage.classList.remove('show');
        successMessage.classList.add('hidden');
    }, 3000);
}

document.addEventListener("DOMContentLoaded", function() {
    const saveButton = document.getElementById('save-button');
    const branchNameInput = document.getElementById('branch-name');
    const commentTextarea = document.querySelector('textarea');

    saveButton.addEventListener("click", function() {
        const branchName = branchNameInput.value;
        const comment = commentTextarea.value;

        const data = {
            "name": branchName,
            "description": comment
        }

        let link = window.location.href.split("/").pop().split("?")[0]

        $.ajax({
            url: `/branch/ajax/profile/${link}`,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function(response) {
                showSuccessMessage()
            },
            error: function(xhr, status, error) {
                console.log("Error: " + status)
            }
        });
    });
});