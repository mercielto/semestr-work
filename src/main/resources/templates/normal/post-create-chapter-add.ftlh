<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add chapter</title>
    <link rel="stylesheet" href="/styles/post-create-chapter-add.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
</head>
<body>
<div id="settings-body">
    <button id="settings-button">Настройки</button>
    <button id="chapters-button">Главы</button>
</div>

<div id="main-body">
    <form id="text-form" method="post" action="/post/create/add-chapter/??">
        <a href="/post/****">
            <h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>
        </a>
        <input id="chapterName" value="Название главы" name="chapterName" type="text">

        <textarea id="text" name="text" placeholder="Введите текст главы..."></textarea>
        <input type="submit" id="submit-input" value="Добавить">
    </form>
</div>

<script src="/scripts/post-create-navigation.js"></script>
<script src="/scripts/chapter-add.js"></script>
</body>
</html>
