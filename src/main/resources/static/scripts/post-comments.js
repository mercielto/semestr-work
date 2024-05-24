function loadMoreReviews() {
    const allReviews = document.querySelectorAll('.review');
    const nestedReviews = Array.from(allReviews).filter(review => {
        return review.closest('.review') !== null;
    });

    const pageSize = 5;
    const data = {
        "from": ~~(nestedReviews.length / pageSize) + '',
        "count": pageSize + ''
    }

    $.ajax({
        url: `/post/comment/ajax/${link}/${data['from']}/${data['count']}`,
        type: "Get",
        contentType: "application/json",
        success: function(response) {
            console.log(response);
            if (response.length !== pageSize) {
                removeLoadMoreButton();

            }
            renderReviews(response);
        },
        error: function(xhr, status, error) {
            console.log("Error: " + status)
        }
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
        url: "/post/comment/ajax/" + link,
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

loadMoreBtn.addEventListener('click', loadMoreReviews);