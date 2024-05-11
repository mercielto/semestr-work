<form id="main-body" method="post">
    <link rel="stylesheet" href="/styles/post-create-settings.css">
    <input type="text" id="title-input" placeholder="Title" required>

    <div id="image-div">
        <div id="image-div-overlay-parent">
            <img src="${post.getImagePath()}" id="post-image">

            <div id="overlay">
                <input type="file" id="image-upload" accept="image/*">
<#--                TODO: сделать генерацию gpt-->
                <button id="upload-button">Загрузить</button>
                <button class="generate-btn">Сгенерировать</button>
            </div>
        </div>
        <textarea id="description-textarea" placeholder="Описание текста"><#if post.getDescription()??>${post.getDescription()}</#if></textarea>
    </div>

    <div id="universe-name">
        <p>Выберите название вселенной</p>

<#--        TODO: поиск при написании (те, добавка букыв - новый ajax запрос)-->
        <input type="text" id="universeSearchInput" list="searchList" required>

        <datalist id="searchList">
            <option value="Похожий запрос 1">
            <option value="Похожий запрос 2">
            <option value="Похожий запрос 3">

        </datalist>
    </div>

    <textarea id="comment" placeholder="Комментарий автора"><#if post.getCreatorComment()??>${post.getCreatorComment()}</#if></textarea>

    <div id="private-div">
        <p>Приватность:</p>

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

    <script src="/scripts/post-create-settings.js"></script>
</form>