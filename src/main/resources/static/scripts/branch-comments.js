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

reviewForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const data = {
        "comment": document.getElementById('comment').value,
        "date": new Date(Date.now())
    }

    let link = window.location.href.split("/").pop().split("?")[0];

    $.ajax({
        url: "/branch/ajax/comment/" + link,
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response) {
            const reviewElement = createReviewElement(convert(response));
            reviewsList.insertBefore(reviewElement, reviewsList.firstChild);
            reviewForm.reset();
        },
        error: function(xhr, status, error) {
            console.log("Error: " + status)
        }
    });
});

function removeLoadMoreButton() {
    const parent = loadMoreBtn.parentElement;
    parent.removeChild(loadMoreBtn);
}

function loadMoreReviews() {
    const allReviews = document.querySelectorAll('.review');
    const nestedReviews = Array.from(allReviews).filter(review => {
        return review.closest('.review') !== null;
    });

    const data = {
        "from": nestedReviews.length.toString(),
        "count": 5 + ''
    }

    $.ajax({
        url: `/branch/ajax/comments/${link}/${data['from']}/${data['count']}`,
        type: "Get",
        contentType: "application/json",
        success: function(response) {
            console.log(response);
            if (response.length !== 0) {
                renderReviews(response);
            } else {
                removeLoadMoreButton();
            }
        },
        error: function(xhr, status, error) {
            console.log("Error: " + status)
        }
    });
}

loadMoreBtn.addEventListener('click', loadMoreReviews);