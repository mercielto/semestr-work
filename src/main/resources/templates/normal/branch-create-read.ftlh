<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet" href="/styles/post-create-chapters.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
    <link rel="stylesheet" href="/styles/navbar.css">

</head>
<body>
<@tools.navbar userOptional=navUser/>

<div id="settings-body">
    <button id="overview-btn">Обзор</button>
    <button id="read-btn">Главы</button>
</div>


<div id="main-body">
    <h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>

    <h4>
        <a id="branchName" href="/branch/create/profile/${branch.getLink()}"><#if branch.getName()??>${branch.getName()}<#else >Branch name</#if></a>
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
<script src="/scripts/branch-create-chapters.js"></script>
<script src="/scripts/branch-create-navigation.js"></script>
<script src="/scripts/navbar.js"></script>

</body>
</html>
