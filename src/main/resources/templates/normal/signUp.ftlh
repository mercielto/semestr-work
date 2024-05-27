
<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация и Регистрация</title>
    <link rel="stylesheet" href="/styles/authentication.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

    <div class="container">
        <div class="form-container">
            <h2>Регистрация</h2>
            <form id="register-form" method="post" name="register-form">
                <input type="text" name="login" id="login" placeholder="Login" required>
                <input type="email" name="email" id="email" placeholder="Email" required>
                <input type="password" name="password" id="password" placeholder="Password" required> #pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                <ul id="password-params">
                    <li id="digit">Must contain at least one digit (0-9)</li>
                    <li id="lowercase">Must contain at least one lowercase letter (a-z)</li>
                    <li id="uppercase">Must contain at least one uppercase letter (A-Z)</li>
                    <li id="length">Must be at least 8 characters long</li>
                </ul>
                <button id="submit" type="submit">Зарегистрироваться</button>
            </form>
        </div>
    </div>

<script src="/scripts/authentication.js"></script>
</body>
</html>