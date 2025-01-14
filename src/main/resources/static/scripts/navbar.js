document.addEventListener('DOMContentLoaded', function () {
    const dropdownToggle = document.getElementById('dropdownToggle');
    const dropdownMenu = document.getElementById('dropdownMenu');
    const userName = document.getElementById('userName');

    if (dropdownToggle == null) {
        return;
    }

    dropdownToggle.addEventListener('click', function () {
        dropdownMenu.classList.toggle('show');
        dropdownToggle.classList.toggle('active');
    });

    document.addEventListener('click', function (event) {
        if (!dropdownMenu.contains(event.target) && !dropdownToggle.contains(event.target) && !userName.contains(event.target)) {
            dropdownMenu.classList.remove('show');
            dropdownToggle.classList.remove('active');
        }
    });
});

var login = document.getElementById('loginBtn');

if (login != null) {
    login.addEventListener('click', function() {
        window.location.href = '/authentication/sign-in';
    });

    document.getElementById('signupBtn').addEventListener('click', function() {
        window.location.href = '/authentication/sign-up';
    });
}
