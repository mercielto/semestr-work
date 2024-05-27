<#import "navbar.ftlh" as tools>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="/styles/error.css">
</head>
<body>
<@tools.navbar userOptional=navUser/>

<div class="error-container">
    <h1>Уууупс! Что-то пошло не так</h1>
    <p>Приносим извинения за причиненные неудобства.</p>
    <p>Код ошибки: ${status}</p>
    <p>${error}</p>
    <button onclick="goBack()">Go Back</button>
</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>
