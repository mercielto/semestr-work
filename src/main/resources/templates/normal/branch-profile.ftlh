<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Branch profile</title>
    <link rel="stylesheet" href="/styles/branch-profile.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

<div id="settings-body">
    <button id="overview-btn">Обзор</button>
    <button id="read-btn">Читать</button>
    <button id="comments-btn">Отзывы</button>
</div>

<div class="body">

    <div class="main-div">
        <h1>${branch.getName()}</h1>

        <#assign creator = branch.getCreator()>
        <a class="user-info" href="/user/${creator.getLink()}">
            <img src="/uploads/user/${creator.getImageName()}" id="user-image">
            <p>${creator.getUsername()}</p>
        </a>

        <div class="description">
            <p>Название произведения: <a href="/post/profile/${branch.getPostLink()}">${branch.getPostName()}</a></p>
            <p>Оценка: ${branch.getRating()}</p>
        </div>

        <p class="author-comment"><#if branch.getDescription()??>${branch.getDescription()}</#if></p>

        <p>Моя оценка:</p>
        <div class="rating">
            <#assign numbers = 1..10>
            <#list numbers as number>
                <#assign r = 11 - number>
                <input type="radio" id="star${number}" name="rating" value="${r}"
                        <#if rating < r + 1 && rating != 0>checked</#if>/>
                <label for="star${number}">☆</label>
            </#list>
        </div>
    </div>


</div>
<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/branch-navigation.js"></script>
<script src="/scripts/branch-profile.js"></script>
<script src="/scripts/navbar.js"></script>
</body>
</html>
