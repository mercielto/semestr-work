<#import "navbar.ftlh" as tools>
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
<@tools.navbar userOptional=navUser/>

<div id="settings-body">
    <button id="overview-btn">Настройки</button>
    <button id="read-btn">Главы</button>
</div>

<div id="main-body">
    <form id="text-form" method="POST" action="/chapter/??">
        <a href="/post/****">
            <h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>
        </a>
        <div class="chapter-param">
            <input id="number" name="number" type="number" required>
            <input id="title" value="Название главы" name="title" type="text" required>
        </div>
        <textarea id="text" name="text" placeholder="Введите текст главы..."></textarea>
        <input type="submit" id="submit-input" value="Добавить">
    </form>
</div>

<script>
    var num = document.getElementById('number');
    num.innerText = ${minNumber};
    num.addEventListener('input', function() {
        const minValue = ${minNumber};
        const maxValue = ${lastNumber} + 1;
        const inputValue = parseInt(this.value);

        if (isNaN(inputValue) || inputValue < minValue || inputValue > maxValue) {
            this.value = '';
        }
    });
</script>
<script src="/scripts/chapter-add-default.js"></script>
<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/branch-create-navigation.js"></script>
<script src="/scripts/chapter-add.js"></script>
</body>
</html>
