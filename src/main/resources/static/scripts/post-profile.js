// const link = window.location.href.split("/").pop().split("?")[0];
document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.rating input[type="radio"]');

    stars.forEach(function(star) {
        star.addEventListener("click", function() {
            const clickedStarValue = parseInt(this.value);

            var data = {
                "rating": clickedStarValue
            }

            $.ajax({
                url: "/post/ajax/rate/" + link,
                type: "PATCH",
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

$(document).ready(function() {
    $('#viewed').change(function() {
        var selectedValue = $(this).val();
        var $select = $(this);
        $select.prop('disabled', true);

        $.ajax({
            url: '/post/ajax/read/' + link,
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify({ status: selectedValue }),
            success: function(response) {
                console.log('Status updated successfully');
            },
            error: function(xhr, status, error) {
                console.error('Error updating status:', error);
            },
            complete: function() {
                $select.prop('disabled', false);
            }
        });
    });
});