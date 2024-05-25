<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Table of Contents</title>
    <link rel="stylesheet" href="/styles/post-chapters.css">
</head>
<body>

<div class="post-navigation">
    <button id="post-overview">Обзор</button>
    <button id="post-read">Читать</button>
    <button id="post-vote">Голосование</button>
    <button id="post-feedbacks">Отзывы</button>
</div>

<div class="container">
    <h1 class="title">Содержание</h1>

    <ul class="contents" id="contents">

        <#list chapters as key, chaptersList>
            <#assign selected = chaptersList[0]>
            <li>
                <a class="chapter" href="/chapter/${selected.getLink()}" data-number="${key}">Глава ${key}:</a>
                <#if chaptersList?size != 1>
                    <select class="chapter-select">

                        <#list chaptersList as chapter>

                            <option data-link="${chapter.getBranchLink()}" data-chapterLink="${chapter.getLink()}"->${chapter.getTitle()}</option>

                        </#list>

                    </select>
                <#else>
                    ${selected.getTitle()}
                </#if>
            </li>

        </#list>

    </ul>

    <input id="chapter-number-input" type="number" min="1" max="4" placeholder="Номер главы">
    <button id="create-branch-button">Создать новую ветку</button>
</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/post-navigation.js"></script>
<script src="/scripts/post-chapters.js"></script>

</body>
</html>
