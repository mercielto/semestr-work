
<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vote for Text Excerpts</title>
    <link rel="stylesheet" href="/styles/post-vote.css">
    <link rel="stylesheet" href="/styles/post-navigation.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

<div id="body">
    <div class="post-navigation">
        <button id="post-overview">Обзор</button>
        <button id="post-read">Читать</button>
        <button id="post-vote">Голосование</button>
        <button id="post-feedbacks">Отзывы</button>
    </div>

    <div class="container" id="container">
        <div class="sort-dropdown">
<#--            TODO: подправить отобрадение звезд и повторную сортировку -->
            <label for="sort">Sort By:</label>
            <select id="sort">
                <option value="AVERAGE_RATING_DESC">Average Rating (High to Low)</option>
                <option value="AVERAGE_RATING_ASC">Average Rating (Low to High)</option>
                <option value="NUMBER_OF_VOTES_ASC">Number of Votes (Low to High)</option>
                <option value="NUMBER_OF_VOTES_DESC">Number of Votes (High to Low)</option>
            </select>
        </div>

        <#list branchesUser as branchUser>
            <#assign branch = branchUser.getResponse()>
            <#assign rating = branchUser.getRating()>

            <div class="excerpt" data-rating="0" data-votes="0">
                <h2><a href="/branch/profile/${branch.getLink()}">${branch.getName()}</a></h2>

                <div class="rating">
                    <#assign numbers = 1..10>
                    <#list numbers as number>
                        <#assign r = 11 - number>
                        <input type="radio" id="star${number}-${branch.getLink()}" name="rating-${branch.getLink()}" value="${r}"
                               <#if rating < r + 1 && rating != 0>checked</#if>/>
                        <label for="star${number}-${branch.getLink()}">☆</label>
                    </#list>
                </div>

                <p class="rating-info">Average Rating: ${branch.getRating()} (${branch.getVotersCount()} votes)</p>

                <div class="excerpt-content">
                    <p><#if branch.getDescription()??>${branch.getDescription()}</#if></p>
                </div>
            </div>

        </#list>

        <#if 5 <= branchesUser?size>>
            <button class="load-more-btn" id="load-more-btn">Load More</button>
        </#if>
    </div>

</div>

<script src="/scripts/jquery-3.6.0.min.js"></script>
<script src="/scripts/post-navigation.js"></script>
<script src="/scripts/post-vote.js"></script>
</body>
</html>
