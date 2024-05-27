<#macro navbar userOptional>
    <link rel="stylesheet" href="/styles/navbar.css">
    <nav class="navbar">
        <div class="navbar-brand">
            <a href="/main">StoryBranch</a>
        </div>
        <div class="navbar-nav">
            <a href="/main/search">Search</a>

            <#if userOptional.isPresent()>
                <a href="/post/create/">Create</a>
            </#if>
        </div>

        <#if userOptional.isPresent()>
            <#assign user = userOptional.get()>
            <div class="navbar-user">
                <a href="/user/profile/${user.getLink()}" id="user-link">
                    <img src="/uploads/user/${user.getImageName()}" alt="User Avatar" class="user-avatar">
                    <span class="user-name" id="userName">${user.getUsername()}</span>
                </a>
                <div class="dropdown">
                    <button class="dropdown-toggle" id="dropdownToggle">
                        ▼
                    </button>
                    <div class="dropdown-menu" id="dropdownMenu">
                        <a href="/publications">My Stories</a>
                        <a href="/logout">Logout</a>
                    </div>
                </div>
            </div>
        <#else>
            <div class="navbar-auth">
                <button class="btn login-btn" id="loginBtn">Войти</button>
                <button class="btn signup-btn" id="signupBtn">Зарегистрироваться</button>
            </div>
        </#if>
    </nav>
    <script src="/scripts/navbar.js"></script>
</#macro>