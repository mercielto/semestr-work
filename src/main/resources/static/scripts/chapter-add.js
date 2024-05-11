document.getElementById('text-form').addEventListener('submit', function(event) {
    event.preventDefault();
});

document.getElementById('chapterName').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
    }
});

document.getElementById('submit-input').addEventListener('click', function() {
    document.getElementById('text-form').submit();
});

const form = document.getElementById('text-form');
const link = window.location.pathname.split('/').pop();
const newAction = `/post/create/add-chapter/${link}`;
form.action = newAction;