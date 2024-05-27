<#import "navbar.ftlh" as tools>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Reviews</title>
    <link rel="stylesheet" href="/styles/branch-comments.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
    <link rel="stylesheet" href="/styles/navbar.css">

</head>
<body>

<@tools.navbar userOptional=navUser/>


<div id="settings-body">
    <button id="overview-btn">Обзор</button>
    <button id="read-btn">Читать</button>
    <button id="comments-btn">Отзывы</button>
</div>

<div id="body">

    <div class="container">
        <h1>Отзывы</h1>

        <#if navUser??>
            <h2>Add Your Review</h2>
            <form id="review-form">
                <div>
                    <label for="comment">Your Comment:</label>
                    <textarea id="comment" name="comment" rows="4" required></textarea>
                </div>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>

            <hr>
        </#if>

        <ul class="reviews" id="reviews-list">

            <#list comments as comment>
                <#assign user = comment.getUser()>
                <div class="review">
                    <div class="user-info">
                        <img src="/uploads/user/${user.getImageName()}" alt="User Avatar" class="avatar">
                        <p class="username">${user.getUsername()}</p>
                    </div>
                    <div class="content">
                        <p>${comment.getComment()}</p>
                        <p class="timestamp">${comment.getDate()}</p>
                    </div>
                </div>
            </#list>
        </ul>

        <#if 5 <= comments?size>
            <div class="load-more">
                <button id="load-more-btn">Load More</button>
            </div>
        </#if>

    </div>

</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/branch-navigation.js"></script>
<script src="/scripts/comments-default.js"></script>
<script src="/scripts/branch-comments.js"></script>
<script src="/scripts/navbar.js"></script>

</body>
</html>
