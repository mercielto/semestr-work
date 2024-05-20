$(document).ready(function () {
    $('#submit-input').on('click', function (event) {
        event.preventDefault();

        var formData = {
            "number": $('#number').val(),
            "title": $('#title').val(),
            "text": $('#text').val()
        };

        $.ajax({
            url: '/chapter/ajax/' + window.location.pathname.split('/').pop().split("?")[0],
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (result) {
                window.location.href = '/post/create/chapters/' + result;
            },
            error: function (xhr, status, error) {
                console.log("Ошибка: " + error)
            }
        });
    });
});