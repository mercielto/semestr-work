document.getElementById('submit-input').addEventListener('click', function() {
    document.getElementById('text-form').submit();
});

const form = document.getElementById('text-form');
const link = window.location.pathname.split('/').pop();
const newAction = `/chapter/${link}`;
form.action = newAction;