function handleButtonClick(buttonId) {
    const buttons = document.querySelectorAll('#settings-body button');
    buttons.forEach(button => {
        if (button.id === buttonId) {
            window.location.href = buttonLinks[buttonId];
        }
    });
}

const link = window.location.href.split("/").pop().split("?")[0];

const buttonLinks = {
    'overview-btn': '/branch/create/profile/' + link,
    'read-btn': '/branch/create/read/' + link,
};

const overviewBtn = document.getElementById('overview-btn');
const readBtn = document.getElementById('read-btn');

overviewBtn.addEventListener('click', () => handleButtonClick('overview-btn'));
readBtn.addEventListener('click', () => handleButtonClick('read-btn'));