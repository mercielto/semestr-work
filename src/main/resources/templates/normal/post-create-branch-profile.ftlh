<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Branch profile</title>
    <link rel="stylesheet" href="/styles/branch-profile.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
    <link rel="stylesheet" href="/styles/post-create-branch-profile.css">
</head>
<body>

<div id="settings-body">
    <button id="overview-btn">Обзор</button>
    <button id="read-btn">Читать</button>
    <button id="comments-btn">Отзывы</button>
</div>

<div class="body">

    <div class="main-div">
        <h1>
            <input id="branch-name" value="${branch.getName()}">
        </h1>

        <#assign creator = branch.getCreator()>
        <a class="user-info" href="/user/${creator.getLink()}">
            <img src="/uploads/post/${creator.getImageName()}" id="user-image">
            <p>${creator.getUsername()}</p>
        </a>

        <div class="description">
            <p>Название произведения: <a href="/post/create/settings/${branch.getPostLink()}">${branch.getPostName()}</a></p>
            <p>Оценка: ${branch.getRating()}</p>
        </div>

        <textarea><#if branch.getDescription()??>${branch.getDescription()}</#if></textarea>
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

        <button id="save-button">Сохранить</button>
    </div>

    <div id="success-message" class="success-message hidden">Успех! Ваши данные сохранены.</div>

</div>
<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/branch-navigation.js"></script>
<script src="/scripts/branch-profile.js"></script>
<script src="/scripts/post-create-branch-profile.js"></script>
</body>
</html>
