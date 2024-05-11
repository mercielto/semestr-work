function checkRequirement(id, regex) {
    var element = document.getElementById(id);
    if (regex.test(element.innerHTML)) {
        element.style.display = "auto";
    } else {
        element.style.display = "none";
    }
}


document.addEventListener('DOMContentLoaded', function() {
    var digitRegex = /\d/;
    var lowercaseRegex = /[a-z]/;
    var uppercaseRegex = /[A-Z]/;
    var lengthRegex = /.{8,}/;

    var password = document.getElementById("password")

    password.addEventListener("input", function (ev) {
        checkRequirement("digit", digitRegex);
        checkRequirement("lowercase", lowercaseRegex);
        checkRequirement("uppercase", uppercaseRegex);
        checkRequirement("length", lengthRegex);
    });
});

$(document).ready(function(e) {
    $("#submit").click(function(){
        $.ajax({
            url: "http://localhost:8080/admin/ajax/users",
            type: "PUT",
            data: data,
            success: function(response) {
                success(this);
            },
            error: function(xhr, status, error) {
                error()
            },
            complete: function() {
                $(this).prop('disabled', false);
            }
        });
    });
});