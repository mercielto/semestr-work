const reviewsList = document.getElementById('reviews-list');
const loadMoreBtn = document.getElementById('load-more-btn');
const reviewForm = document.getElementById('review-form');

function createReviewElement(review) {
    const li = document.createElement('li');
    li.classList.add('review');
    li.innerHTML = `
            <div class="user-info">
                <img src="${review.avatar}" alt="User Avatar" class="avatar">
                <p class="username">${review.username}</p>
            </div>
            <div class="content">
                <p>${review.comment}</p>
                <p class="timestamp">${review.timestamp}</p>
            </div>
        `;
    return li;
}

function convert(response) {
    let rev = {};
    rev.username = response['user']['username'];
    rev.avatar = `/images/${response['user']['imageName']}`;
    rev.comment = response['comment'];
    rev.timestamp = response['date'];
    return rev;
}

function renderReviews(reviews) {
    reviews.forEach(review => {
        let rev = convert(review);
        const reviewElement = createReviewElement(rev);
        reviewsList.appendChild(reviewElement);
    });
}

function removeLoadMoreButton() {
    const parent = loadMoreBtn.parentElement;
    parent.removeChild(loadMoreBtn);
}