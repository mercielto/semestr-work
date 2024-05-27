function addPosts(posts) {
    const mainElement = document.getElementById('main');

    posts.forEach(post => {
        const postElement = document.createElement('div');
        postElement.classList.add('post');

        const postHeader = document.createElement('div');
        postHeader.classList.add('post-header');

        const userParams = document.createElement('div');
        userParams.classList.add('user-params');

        const userLink = document.createElement('a');
        userLink.href = `/user/profile/${post['creator']['link']}`;

        const userImage = document.createElement('img');
        userImage.src = `/static/images/${post['creator']['imageName']}`;
        userImage.alt = "User";

        const username = document.createElement('p');
        username.textContent = post['creator']['username'];

        const postParams = document.createElement('div');
        postParams.classList.add('post-params');

        const postLink = document.createElement('a');
        postLink.href = `/post/profile/${post['webLink']}`;

        const postTitle = document.createElement('h4');
        postTitle.textContent = post['title'];

        const postImage = document.createElement('img');
        postImage.src = `/static/images/${post['imagePath']}`;
        postImage.alt = "Post Image";

        const postDescription = document.createElement('div');
        postDescription.classList.add('post-description');

        const descriptionText = document.createElement('p');
        descriptionText.textContent = post['description'] || 'EMPTY';

        const postRating = document.createElement('div');
        postRating.classList.add('post-rating');

        const ratingText = document.createElement('p');
        ratingText.textContent = `Rating: ${post['rating']}`;

        userLink.appendChild(userImage);
        userParams.appendChild(userLink);
        userParams.appendChild(username);

        postLink.appendChild(postTitle);
        postParams.appendChild(postLink);
        postParams.appendChild(postImage);

        postHeader.appendChild(userParams);
        postHeader.appendChild(postParams);

        postDescription.appendChild(descriptionText);

        postRating.appendChild(ratingText);

        postElement.appendChild(postHeader);
        postElement.appendChild(postDescription);
        postElement.appendChild(postRating);

        mainElement.appendChild(postElement);

    });
}

document.addEventListener("DOMContentLoaded", function() {
    const loadMoreButton = document.getElementById('load-more');
    loadMoreButton.addEventListener('click', function() {

        const mainElement = document.getElementById('main');
        const postElements = mainElement.getElementsByClassName('post');
        const postCount = postElements.length;
        const size = 9;
        $.ajax({
            url: "/post/ajax/content?count=" + postCount / size + "&size=" + size ,
            type: "GET",
            success: function(response) {
                console.log("Success");
                console.log(response);
                if (response.length < size) {
                    mainElement.removeChild(loadMoreButton);
                }

                addPosts(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + status)
            }
        });

    });
});
