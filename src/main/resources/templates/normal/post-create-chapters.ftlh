<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet" href="/styles/post-create-chapters.css">
    <link rel="stylesheet" href="/styles/post-create-navigation.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

<div id="settings-body">
    <button id="settings-button">Настройки</button>
    <button id="chapters-button">Главы</button>
</div>


<div id="main-body">
    <h1><#if post.getTitle()??>${post.getTitle()}<#else >Title</#if></h1>

    <h4>
        <a id="branchName" href="/branch/create/profile/${branch.getLink()}"><#if branch.getName()??>${branch.getName()}<#else >Branch name</#if></a>
    </h4>

    <div class="contents" id="contents">

        <#list chapters as key, chaptersList>
            <#assign selected = chaptersList[0]>

            <div class="chapter">
                <a class="chapter-name" data-number="${selected.getNumber()}" href="/post/create/chapter/${selected.getLink()}">Глава ${selected.getNumber()}:</a>
                <#if chaptersList?size != 1>
                    <select class="chapter-select">

                        <#list chaptersList as chapter>

                            <option data-link="${chapter.getBranchLink()}" data-chapterLink="${chapter.getLink()}"->${chapter.getTitle()}</option>

                        </#list>

                    </select>
                <#else>
                    ${selected.getTitle()}
                </#if>
                <button class="delete-chapter-btn" <#if chaptersList?size != 1 || chapters?size == 1>
                    style="visibility: hidden"
                </#if>>Delete</button>

            </div>

        </#list>

    </div>

    <label for="branchNumber" id="label-for-chapter-number-input"></label>
    <input id="branchNumber" type="number">
    <input type="submit" id="create-branch-button" class="chapter-add" value="Новая ветка">
</div>

<script>
    var num = document.getElementById('branchNumber');
    var label = document.getElementById("label-for-chapter-number-input")
    num.value = ${minNumber};
    num.addEventListener('input', function() {
        const minValue = ${minNumber};
        const maxValue = ${lastNumber} + 1;
        const inputValue = parseInt(this.value);
        label.innerText = "Укажите главу";
        label.visibility = true;
        if (isNaN(inputValue) || inputValue < minValue || inputValue > maxValue) {
            this.value = '';
        }
    });
</script>
<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/post-create-chapters.js"></script>
<script src="/scripts/post-create-navigation.js"></script>
</body>
</html>
