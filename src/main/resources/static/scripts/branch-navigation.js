function handleButtonClick(buttonId) {
    const buttons = document.querySelectorAll('#settings-body button');
    buttons.forEach(button => {
        if (button.id === buttonId) {
            window.location.href = buttonLinks[buttonId]; // Перенаправление
        }
    });
}

const link = window.location.href.split("/").pop().split("?")[0];

const buttonLinks = {
    'overview-btn': '/branch/profile/' + link,
    'read-btn': '/branch/read/' + link,
    'comments-btn': '/branch/comments/' + link
};

const overviewBtn = document.getElementById('overview-btn');
const readBtn = document.getElementById('read-btn');
const commentsBtn = document.getElementById('comments-btn');

overviewBtn.addEventListener('click', () => handleButtonClick('overview-btn'));
readBtn.addEventListener('click', () => handleButtonClick('read-btn'));
commentsBtn.addEventListener('click', () => handleButtonClick('comments-btn'));