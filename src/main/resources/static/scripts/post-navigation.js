function handleButtonClick(buttonId) {
    const btns = document.querySelectorAll('.post-navigation button');
    btns.forEach(button => {
        if (button.id === buttonId) {
            window.location.href = buttonLinks[buttonId];
        }
    });
}

const link = window.location.href.split("/").pop().split("?")[0];

const buttonLinks = {
    'post-overview': '/post/profile/' + link,
    'post-read': '/post/read/' + link,
    'post-vote': '/post/vote/' + link,
    'post-feedbacks': '/post/comments/' + link
};

const overviewBtn = document.getElementById('post-overview');
const readBtn = document.getElementById('post-read');
const voteBtn = document.getElementById('post-vote');
const feedbackBtn = document.getElementById('post-feedbacks');


overviewBtn.addEventListener('click', () => handleButtonClick('post-overview'));
readBtn.addEventListener('click', () => handleButtonClick('post-read'));
voteBtn.addEventListener('click', () => handleButtonClick('post-vote'));
feedbackBtn.addEventListener('click', () => handleButtonClick('post-feedbacks'));
