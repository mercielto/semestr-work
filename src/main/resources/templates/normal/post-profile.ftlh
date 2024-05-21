<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Post profile</title>
    <link rel="stylesheet" href="/static/styles/post-profile.css">
</head>
<body>

<#assign post = postUser.getResponse()>
<div class="main-div">

    <h3>${post.getTitle()}</h3>

    <div class="post-navigation">
        <button id="post-overview">Обзор</button>
        <button id="post-read">Читать</button>
        <button id="post-vote">Голосование</button>
        <button id="post-feedbacks">Отзывы</button>
    </div>

    <div class="image-div">
        <img src="/uploads/post/${post.getImagePath()}" alt="Изображение">
        <div class="characteristics">
            <p>Количество веток: ${post.getBranchesCount()}</p>
            <p>Количество редакторов: ${post.getEditorsCount()}</p>
            <p>Просмотрено: ${post.getReadCount()}</p>
            <p>Создатель: <a href="/user/profile/${post.getCreator().getLink()}">${post.getCreator().getUsername()}</a></p>
<#--            TODO: сделать страницу для поиска по вселенным-->
            <p>Название вселенной: <a href="***"><#if post.getUniverse()??>${post.getUniverse()}</#if></a></p>
        </div>
    </div>

    <div class="rating">
        <div class="rating-value">${post.getRating()}</div>

        <#assign numbers = 1..10>
        <#list numbers as number>
            <#assign r = 11 - number>
            <input type="radio" id="star${number}" name="rating" value="${r}"
                   <#if rating < r + 1 && rating != 0>checked</#if>/>
            <label for="star${number}">☆</label>
        </#list>
    </div>

    <div id="select-viewed">
        <#assign status = postUser.getStatus()>
        <label for="viewed">Отметка о просмотре:</label>
        <select id="viewed">
            <option value="NOT_VIEWED" <#if status.name() == "NOT_VIEWED">selected</#if>>Не просмотрено</option>
            <option value="VIEWED" <#if status.name() == "VIEWED">selected</#if>>Просмотрено</option>
        </select>
    </div>

    <div>
        <h4>Описание:</h4>
        <p id="author-post-description">${post.getDescription()}</p>
    </div>

    <div>
        <h4>Комментарий:</h4>
        <p id="author-comment">${post.getCreatorComment()}</p>
    </div>
</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/post-navigation.js"></script>
<script src="/scripts/post-profile.js"></script>
</body>
</html>
