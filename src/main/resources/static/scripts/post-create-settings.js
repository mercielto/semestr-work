function getLink() {
    var link = window.location.href.split('/').pop();
    return link.split('?')[0];
}

function collectData() {
    var formData = {};

    formData['title'] = $('#title-input').val();
    formData['description'] = $('#description-textarea').val();
    formData['universeName'] = $('#universeSearchInput').val();
    formData['creatorComment'] = $('#comment').val();
    formData['status'] = $('#privateList').val();
    formData['webLink'] = getLink();
    formData['editorsLogins'] = [];

    return formData;
}


document.addEventListener('DOMContentLoaded', function() {
    var form = document.getElementById('main-body');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
    });

    var buttons = document.querySelectorAll('#creation-btns button');
    buttons.forEach(function(button) {
        if (button.id !== 'create-btn') {
            button.addEventListener('click', function(event) {
                event.preventDefault();
            });
        }
    });
});

$(document).ready(function() {
    $("#save-btn").click(function(event){
        event.preventDefault()
        var data = JSON.stringify(collectData());

        console.log(data);
        $.ajax({
            url: "/post/ajax/settings/save/" + getLink(),
            type: "Post",
            data: data,
            contentType: "application/json",
            success: function() {
                console.log("Success");
            },
            error: function(xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const postImage = document.getElementById('image-div-overlay-parent');
    const overlay = document.getElementById('overlay');

    postImage.addEventListener('mouseenter', function() {
        overlay.style.display = 'flex';
    });

    postImage.addEventListener('mouseleave', function() {
        overlay.style.display = 'none';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const imageUpload = document.getElementById('image-upload');
    const uploadButton = document.getElementById('upload-button');
    const postImage = document.getElementById('post-image');

    uploadButton.addEventListener('click', function() {
        imageUpload.click();
    });

    imageUpload.addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                postImage.src = e.target.result;
                postImage.style.display = 'inline';
            }
            reader.readAsDataURL(file);
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const createButton = document.getElementById('create-btn');
    const form = document.getElementById('main-body');

    form.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
        }
    });

    createButton.addEventListener('click', function(event) {
        event.preventDefault();
        form.submit();
    });
});