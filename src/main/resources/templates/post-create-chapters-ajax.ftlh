<div id="main-body">
    <link href="/static/styles/post-create-chapters.css" rel="stylesheet">
    <h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>

    <#list post.getChapters() as chapter>
        <div class="chapter">
            <a class="chapter-name" href="/post/create/${post.getWebLink()}?part=chapter&number=${chapter.getNumber()}">
                <p>${chapter.getNumber()}. ${chapter.getTitle()}</p>
            </a>
        </div>

        <div class="chapter-divider">
            <input type="submit" class="chapter-add" value="Новая глава">
        </div>
    </#list>

    <input type="submit" id="chapter-add-main" class="chapter-add" value="Новая глава">

    <script src="/scripts/post-create-chapters.js"></script>
</div>

