<#import "navbar.ftlh" as tools>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" href="/styles/admin-users.css">
    <link rel="stylesheet" href="/styles/navbar.css">

</head>
<body>

<@tools.navbar userOptional=navUser/>

    <div id="users-list">
        <#list users as user>
            <div class="user">
                <a href="/profile/${user.getLink()}" class="user-image-div">
                    <img src="/images/1183669.png" alt="user profile picture" class="user-image">
                    <div class="user-info">
                        <p class="user-name">${user.getName()! "ANONYMOUS"}</p>
                        <p class="user-login">${user.getLogin()}</p>
                    </div>
                </a>

                <div class="user-state" <#if user.getRole().equals("ADMIN")>disabled</#if>>
                    <button class="user-state-block-btn">
                        <#if user.isActive()>
                            BLOCK
                        <#else>
                            UNBLOCK
                        </#if>
                    </button>

                </div>
            </div>
        </#list>
    </div>

    <script src="/scripts/jquery-3.6.0.min.js"></script>
    <script src="/scripts/admin-users.js"></script>
    <script src="/scripts/navbar.js"></script>

</body>
</html>