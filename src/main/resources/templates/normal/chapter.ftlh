<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chapter</title>
    <link rel="stylesheet" href="/styles/chapter.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

<div id="settings-body">
    <button id="overview-btn">Обзор</button>
    <button id="read-btn">Читать</button>
    <button id="comments-btn">Отзывы</button>
</div>

<div class="container">
    <h1 id="chapter-title" data-link="${chapter.getBranchLink()}">${chapter.getTitle()}</h1>
    <div id="chapter-text" class="chapter-text">
        <div class="formatted-text">${chapter.getText()}</div>
    </div>
</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/chapter-navigation.js"></script>
</body>
</html>
