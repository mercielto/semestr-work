// function showUploadButton() {
//     document.getElementById("uploadButton").style.display = "flex";
// }
//
// function hideUploadButton() {
//     document.getElementById("uploadButton").style.display = "none";
// }

function deletePost(element) {
    var postElement = element.closest('.recent-post');
    var postLink = postElement.querySelector('a');
    var confirmation = confirm("Are you sure you want to delete this post?");
    if (confirmation) {
        if (postLink) {
            var postHref = postLink.getAttribute('href');
            if (postHref) {

                $.ajax({
                    url: `/post/ajax/${link}`,
                    type: 'DELETE',
                    contentType: 'application/json',
                    success: function () {
                        postElement.remove();
                        updatePostCount();
                    },
                    error: function (xhr, status, error) {
                        console.log("Ошибка: " + error)
                    }
                });

            }
        }
    }
}


function updatePostCount() {
    var postCount = document.querySelectorAll(".recent-post").length;
    document.getElementById("postCount").innerText = postCount;
}

function editUsername() {
    var usernameElement = document.getElementById('username');
    var usernameInput = document.getElementById('usernameInput');

    if (usernameElement.classList.contains('hidden')) {

        $.ajax({
            url: `/user/ajax/username/${link}/${usernameInput.value}`,
            type: 'PATCH',
            contentType: 'application/json',
            success: function (result) {
                usernameElement.textContent = usernameInput.value;
                usernameElement.classList.remove('hidden');
                usernameInput.classList.add('hidden');
            },
            error: function (xhr, status, error) {
                console.log("Ошибка: " + error)
            }
        });
    } else {
        usernameInput.value = usernameElement.textContent;
        usernameElement.classList.add('hidden');
        usernameInput.classList.remove('hidden');
        usernameInput.focus();
    }
}

function editBio(bioId) {
    var bioElement = document.getElementById(bioId);
    var bioInput = document.getElementById('bioInput');

    if (bioElement.classList.contains('hidden')) {

        $.ajax({
            url: `/user/ajax/bio/${link}/${bioInput.value}`,
            type: 'PATCH',
            contentType: 'application/json',
            success: function (result) {
                bioElement.textContent = bioInput.value;
                bioElement.classList.remove('hidden');
                bioInput.classList.add('hidden');
            },
            error: function (xhr, status, error) {
                console.log("Ошибка: " + error)
            }
        });
    } else {
        bioInput.value = bioElement.textContent;
        bioElement.parentNode.insertBefore(bioInput, bioElement.nextSibling);
        bioElement.classList.add('hidden');
        bioInput.classList.remove('hidden');
        bioInput.focus();
    }
}

function uploadImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {

            var formData = new FormData();
            formData.append('file', input.files[0]);

            $.ajax({
                url: `/user/ajax/image/${link}`,
                type: 'PATCH',
                contentType: false,
                processData: false,
                data: formData,
                success: function () {
                    document.getElementById('avatar').src = e.target.result;
                },
                error: function (xhr, status, error) {
                    console.log("Ошибка: " + error)
                }
            });

        };
        reader.readAsDataURL(input.files[0]);
    }
}

const link = window.location.pathname.split('/').pop().split("?")[0];