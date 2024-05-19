document.getElementById('text-form').addEventListener('submit', function(event) {
    event.preventDefault();
});

document.getElementById('title').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
    }
});