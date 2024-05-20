document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.rating input[type="radio"]');

    stars.forEach(function(star) {
        star.addEventListener("click", function() {
            const clickedStarValue = parseInt(this.value);
            const link = window.location.href.split("/").pop().split("?")[0];
            var data = {
                "rate": clickedStarValue
            }

            $.ajax({
                url: "/branch/ajax/rate/" + link,
                type: "PUT",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(response) {
                    console.log("User rated:", clickedStarValue);
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + status)
                }
            });
        });
    });
});

