document.addEventListener('DOMContentLoaded', function() {
    const followButton = document.getElementById('btn-follow');
    let isFollowing = false;
    const link = window.location.href.split("/").pop().split("?")[0];

    $.ajax({
        url: "/user/ajax/subscribed/" + link,
        type: "get",
        contentType: "application/json",
        success: function(response) {
            isFollowing = response;
            followButton.textContent = isFollowing ? 'Unfollow' : 'Follow';
            followButton.style.backgroundColor = isFollowing ? '#f44336' : '#4caf50';
            console.log(response)
        },
        error: function(xhr, status, error) {
            console.error('There was a problem:', error);
        }
    });

    followButton.addEventListener('click', function() {
        const action = isFollowing ? 'unfollow' : 'follow';
        $.ajax({
            url: "/user/ajax/follow/" + link,
            type: "PUT",
            contentType: "application/json",
            success: function(response) {
                isFollowing = !isFollowing;
                followButton.textContent = isFollowing ? 'Unfollow' : 'Follow';
                followButton.style.backgroundColor = isFollowing ? '#f44336' : '#4caf50';
            },
            error: function(xhr, status, error) {
                console.error('There was a problem with the fetch operation:', error);
            }
        });

    });
});