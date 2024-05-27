<#import "navbar.ftlh" as navbar>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Posts</title>
    <link rel="stylesheet" href="/styles/search.css">
    <link rel="stylesheet" href="/styles/navbar.css">
</head>
<body>
    <@navbar.navbar userOptional=userNav></@navbar.navbar>
    <div class="body">



    <div id="main">

        <#list posts as post>

            <div class="post">
                <div class="post-header">
                    <div class="user-params">
                        <#assign user = post.getCreator()>
                        <a href="/user/profile/${user.getLink()}">
                            <img src="/uploads/user/${user.getImageName()}" alt="User">
                            <p>${user.getUsername()}</p>
                        </a>
                    </div>
                    <div class="post-params">
                        <a href="/post/profile/${post.getWebLink()}">
                            <h4>${post.getTitle()}</h4>
                            <img src="/uploads/post/${post.getImagePath()}" alt="Post Image">
                        </a>
                    </div>
                </div>
                <div class="post-description">
                    <p><#if post.getDescription()??>post.getDescription()<#else >EMPTY</#if></p>
                </div>
                <div class="post-rating">
                    <p>Rating: ${post.getRating()}</p>
                </div>
            </div>

        </#list>



    </div>

    <#if 9 <= posts?size>
        <button id="load-more">Load More</button>
    </#if>


</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/navbar.js"></script>
<script src="/scripts/search.js"></script>
</body>
</html>
