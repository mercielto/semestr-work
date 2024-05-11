<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация и Регистрация</title>
    <link rel="stylesheet" href="/styles/authentication.css">
</head>
<body>
<div class="container">
    <div class="form-container">
        <h2>Вход</h2>
        <form id="login-form" method="post">
            <input type="text" name="login" id="name" placeholder="Login" required>
            <input type="password" name="password" id="password" placeholder="Password" required>
            <input type="checkbox" id="rememberMe" name="rememberMe">
            <button type="submit">Войти</button>
        </form>
    </div>
</div>

<script src="/scripts/authentication.js"></script>
</body>
</html>