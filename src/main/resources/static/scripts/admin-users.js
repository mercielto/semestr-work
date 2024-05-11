var Constants = {
    BLOCK: "BLOCK",
    UNBLOCK: "UNBLOCK"
}

Object.freeze(Constants);

function getParams(button) {
    let userDiv = button.closest('.user');
    let userLoginElement = userDiv.querySelector('.user-login');
    return userLoginElement.textContent;
}

function success(button) {
    if (button.innerHTML === Constants.BLOCK) {
        button.innerHTML = Constants.UNBLOCK;
    } else {
        button.innerHTML = Constants.BLOCK;
    }
}

function errorFound(error) {
    alert("Произошла ошибка: " + error);
}

$(document).ready(function() {
    $(".user-state-block-btn").click(function(){
        var data = getParams(this);
        $(this).prop('disabled', true);

        var button = this;

        $.ajax({
            url: "/admin/ajax/users",
            type: "PUT",
            data: data,
            contentType: "application/json",
            success: function(response) {
                success(button);
            },
            error: function(xhr, status, error) {
                errorFound(error)
            },
            complete: function() {
                $(button).prop('disabled', false);
            }
        });
    });
});