<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="/styles/user-profile.css">
</head>

<body>

<div class="profile-container">
    <div class="profile-header">
        <div class="profile-avatar">
            <img src="/uploads/user/${user.getImageName()}" alt="Avatar">
        </div>
        <div class="profile-info">
            <h1 class="profile-name">${user.getUsername()}</h1>
            <p class="profile-bio"><#if user.getBio()??>${user.getBio()}</#if></p>
            <div class="profile-stats">
                <div class="stat">
                    <span class="stat-label">Posts</span>
                    <span class="stat-value">${user.getPosts()?size}</span>
                </div>
                <div class="stat">
                    <span class="stat-label">Followers</span>
                    <span class="stat-value">${user.getFollowersCount()}</span>
                </div>
                <div class="stat">
                    <span class="stat-label">Following</span>
                    <span class="stat-value">${user.getFollowingsCount()}</span>
                </div>
            </div>
        </div>
    </div>

    <div class="profile-footer">
        <button id="btn-follow">Follow</button>
    </div>

    <div class="profile-body">
        <h2 class="section-title">Recent Posts</h2>
        <div class="recent-posts">

            <#list user.getPosts() as post>
                <div class="recent-post">
                    <img src="/uploads/post/${post.getImagePath()}" alt="Post 1">
                    <div class="recent-post-content">
                        <h3 class="recent-post-title"><a href="/post/profile/${post.getWebLink()}">${post.getTitle()}</a></h3>
                        <p class="recent-post-description">${post.getDescription()}</p>
                    </div>
                </div>
            </#list>

        </div>
    </div>


</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/user-profile.js"></script>
</body>
</html>
