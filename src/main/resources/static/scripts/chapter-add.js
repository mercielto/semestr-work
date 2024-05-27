document.getElementById('submit-input').addEventListener('click', function() {
    document.getElementById('text-form').submit();
});

const form = document.getElementById('text-form');
var linkkk = window.location.href.split("/").pop().split("?")[0];
const newAction = `/chapter/${linkkk}`;
form.action = newAction;