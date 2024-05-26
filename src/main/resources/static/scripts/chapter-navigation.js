function handleButtonClick(buttonId) {
    const buttons = document.querySelectorAll('#settings-body button');
    buttons.forEach(button => {
        if (button.id === buttonId) {
            window.location.href = buttonLinks[buttonId]; // Перенаправление
        }
    });
}

const h = document.getElementById("chapter-title");
const branchLink = h.getAttribute("data-link");

const buttonLinks = {
    'overview-btn': '/branch/profile/' + branchLink,
    'read-btn': '/branch/read/' + branchLink,
    'comments-btn': '/branch/comments/' + branchLink
};

const overviewBtn = document.getElementById('overview-btn');
const readBtn = document.getElementById('read-btn');
const commentsBtn = document.getElementById('comments-btn');

overviewBtn.addEventListener('click', () => handleButtonClick('overview-btn'));
readBtn.addEventListener('click', () => handleButtonClick('read-btn'));
commentsBtn.addEventListener('click', () => handleButtonClick('comments-btn'));