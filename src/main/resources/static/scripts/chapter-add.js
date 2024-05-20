document.getElementById('submit-input').addEventListener('click', function() {
    document.getElementById('text-form').submit();
});

const form = document.getElementById('text-form');
const link = window.location.href.split("/").pop().split("?")[0];
const newAction = `/chapter/${link}`;
form.action = newAction;