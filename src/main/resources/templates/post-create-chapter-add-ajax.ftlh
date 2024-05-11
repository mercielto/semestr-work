<div id="main-body">
    <form id="text-form" method="post">
        <a href="/post/create/${post.getWebLink()}?part=chapter-add">
            <h1>${post.getTitle()}</h1>
        </a>
        <input id="chapterName" value="Название главы" name="chapterName">

        <textarea id="text" name="text" placeholder="Ваш текст"></textarea>
        <input type="submit" id="submit-input" value="Добавить">
    </form>
    <script src="/scripts/chapter-add.js"></script>
</div>