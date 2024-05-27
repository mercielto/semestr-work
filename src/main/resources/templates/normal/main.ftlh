<#import "navbar.ftlh" as tools>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StoryBranch</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/main.css">
</head>
<body>

<@tools.navbar userOptional=navUser/>

<header class="hero">
    <h1>Welcome to StoryBranch</h1>
    <p>Create and explore branching stories with endless possibilities.</p>
    <a href="#features" class="btn-primary">Learn More</a>
</header>


<section id="features" class="features">
    <div class="feature">
        <i class="fas fa-pencil-alt"></i>
        <h2>Create</h2>
        <p>Write your own stories and let others branch off from your chapters.</p>
    </div>
    <div class="feature">
        <i class="fas fa-code-branch"></i>
        <h2>Branch</h2>
        <p>Explore different storylines and see where your imagination takes you.</p>
    </div>
    <div class="feature">
        <i class="fas fa-users"></i>
        <h2>Collaborate</h2>
        <p>Work with other authors to create unique and engaging stories.</p>
    </div>
</section>

<footer class="footer">
    <p>&copy; 2024 StoryBranch. All rights reserved.</p>
</footer>

<script src="/scripts/navbar.js"></script>
</body>
</html>

