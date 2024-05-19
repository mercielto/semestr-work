<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Branch profile</title>
    <link rel="stylesheet" href="/static/styles/branch-profile.css">
</head>
<body>

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
            <img src="/images/${creator.getLink()}" id="user-image">
            <p>${creator.getName()}</p>
        </a>

        <div class="description">
            <p>Название произведения: <a href="/post/profile/${branch.getPostLink()}">${branch.getPostName()}</a></p>

            <p>Прочитано: ${branch.getReadCount()}</p>
            <p>Оценка: ${branch.getRating()}</p>
        </div>

        <p class="author-comment"><#if branch.getDescription()??>${branch.getDescription()}</#if></p>

        <p>Оценить:</p>
        <div class="rating">
            <input type="radio" id="star10" name="rating" value="10" />
            <label for="star10">☆</label>
            <input type="radio" id="star9" name="rating" value="9" />
            <label for="star9">☆</label>
            <input type="radio" id="star8" name="rating" value="8" />
            <label for="star8">☆</label>
            <input type="radio" id="star7" name="rating" value="7" />
            <label for="star7">☆</label>
            <input type="radio" id="star6" name="rating" value="6" />
            <label for="star6">☆</label>
            <input type="radio" id="star5" name="rating" value="5" />
            <label for="star5">☆</label>
            <input type="radio" id="star4" name="rating" value="4" />
            <label for="star4">☆</label>
            <input type="radio" id="star3" name="rating" value="3" />
            <label for="star3">☆</label>
            <input type="radio" id="star2" name="rating" value="2" />
            <label for="star2">☆</label>
            <input type="radio" id="star1" name="rating" value="1" />
            <label for="star1">☆</label>
        </div>
    </div>


</div>
<script src="/scripts/branch-profile.js"></script>
</body>
</html>
