<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <link rel="stylesheet" href="/styles/post-create-settings.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
</head>
<body>
<div id="settings-body">
    <button id="settings-button">Настройки</button>
    <button id="chapters-button">Главы</button>
</div>

<#--TODO: нормальную вставку картинки -->

<form id="main-body" method="post">
    <input type="text" id="title-input" placeholder="Title" value="<#if post.getTitle()??>${post.getTitle()}</#if>">

    <div id="image-div">
        <div id="image-div-overlay-parent">
<#--            TODO: сделать ссылку на картинку -->
            <img src="/images/fff.jpg" id="post-image">

            <div id="overlay">
                <input type="file" id="image-upload" accept="image/*">
                <button id="upload-button">Загрузить</button>
                <button class="generate-btn">Сгенерировать</button>
            </div>
        </div>
        <textarea id="description-textarea" placeholder="Описание текста"><#if post.getDescription()??>${post.getDescription()}</#if></textarea>
    </div>

    <div id="universe-name">
        <p>Выберите название вселенной</p>

        <input type="text" id="universeSearchInput" list="searchList" value="<#if post.getUniverse()??>${post.getUniverse()}</#if>">

        <datalist id="searchList">
            <option value="Похожий запрос 1">
            <option value="Похожий запрос 2">
            <option value="Похожий запрос 3">
        </datalist>
    </div>

    <textarea id="comment" placeholder="Комментарий автора"><#if post.getCreatorComment()??>${post.getCreatorComment()}</#if></textarea>

    <div id="private-div">
        <p>Приветность:</p>

        <select id="privateList">
            <option value="PRIVATE">Приватный</option>
            <option value="PUBLIC">Публичный, с свободным редактированием</option>
            <option value="PUBLIC_WITH_LIMITS">Публичный, с редактированием по приглашению</option>
        </select>
    </div>

    <div id="creation-btns">
        <button id="save-btn">Сохранить</button>
        <input type="submit" value="Создать" id="create-btn">
    </div>
</form>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/post-create-settings.js"></script>
<script src="/scripts/post-create-navigation.js"></script>
</body>
</html>
