<#import "navbar.ftlh" as tools>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/styles/user-profile.css">
    <link rel="stylesheet" href="/styles/user-profile-owner.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

<div class="profile-container">
    <div class="profile-header">
        <div class="profile-avatar" id="avatarContainer">
            <img id='avatar' src="/uploads/user/${user.getImageName()}" alt="Avatar">
            <div class="upload-avatar" id="uploadButton">
                <label for="image-upload">
                    <i class="fas fa-upload"></i>
                </label>
                <input class="hidden" type="file" id="image-upload" accept="image/*" onchange="uploadImage(this)">
            </div>
        </div>
        <div class="profile-info">
            <div class="profile-info-name">
                <h1 class="profile-name" id="username">${user.getUsername()}</h1>
                <input type="text" id="usernameInput" class="hidden">
                <i class="fas fa-pencil-alt edit-username" onclick="editUsername()"></i>
            </div>

            <p class="profile-bio" id="bio"><#if user.getBio()??>${user.getBio()}<#else>Bio</#if></p>
            <textarea id="bioInput" class="hidden"><#if user.getBio()??>${user.getBio()}</#if></textarea>
            <i class="fas fa-pencil-alt edit-bio" onclick="editBio('bio')"></i>

            <div class="profile-stats">
                <div class="stat">
                    <span class="stat-label">Posts</span>
                    <span class="stat-value" id="postCount">${user.getPosts()?size}</span>
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
                        <p class="recent-post-description"><#if post.getDescription()??>${post.getDescription()}</#if></p>
                    </div>
                </div>
            </#list>

        </div>
    </div>
</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/user-profile.js"></script>
<script src="/scripts/user-profile-owner.js"></script>
</body>
</html>
