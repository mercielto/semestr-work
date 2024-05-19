<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet" href="/styles/post-create-chapters.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
</head>
<body>
<div id="settings-body">
    <button id="settings-button">Настройки</button>
    <button id="chapters-button">Главы</button>
</div>


<div id="main-body">
    <h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>

    <#assign branch = post.getBranches()[0]>
    <#list post.getBranches() as br>
        <#if br.getLink() == branchLink>
            <#assign branch = br>
        </#if>
    </#list>

    <h4>
<#--        TODO: добавить /post/create/branch/settings/${branch.getLink()}-->
        <a style="visibility: hidden" id="branchName" href="/post/create/branch/settings/${branch.getLink()}"><#if branch.getName()??>${branch.getName()}<#else >Branch name</#if></a>
    </h4>
    <#list branch.getChapters() as chapter>
        <div class="chapter">
            <a class="chapter-name" href="/post/create/chapter/${chapter.getLink()}">
                <p>${chapter.getNumber()}. ${chapter.getTitle()}</p>
            </a>
            <button class="delete-chapter-btn">Delete</button>
        </div>
    </#list>

    <input type="submit" id="chapter-add-main" class="chapter-add" value="Новая глава">
</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/post-create-chapters.js"></script>
<script src="/scripts/post-create-navigation.js"></script>
</body>
</html>
