<h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>

<#list post.getChapters() as chapter>

    <#if chapter.getNumber() != 1>
        <div class="chapter-divider">
            <input type="submit" class="chapter-add" value="Новая глава">
        </div>
    </#if>

    <div class="chapter">
        <a class="chapter-name" href="">
            <p>${chapter.getNumber()}. ${chapter.getTitle()}</p>
        </a>
        <button class="delete-chapter-btn">Delete</button>
    </div>
</#list>

<input type="submit" id="chapter-add-main" class="chapter-add" value="Новая глава">