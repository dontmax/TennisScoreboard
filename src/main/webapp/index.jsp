<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <link href="css/indexStyle.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Tennis Scoreboard | Home page</title>
</head>
<body>
<div class="header">
    <header class="tabProperties">
        <p class="projectName">Tennis Scoreboard</p>
        <nav class="navLinks">
            <a class="navLink" href="${pageContext.request.contextPath}/" target="_self">Home</a>
            <a class="navLink" href="${pageContext.request.contextPath}/matches?page=1" target="_self">Matches</a>
        </nav>
    </header>
</div>
<div class="menu">
    <main>
        <h1><strong>Welcome to Tennis Scoreboard</strong></h1>
        <h2>Manage your tennis matches, record results, and track rankings</h2>
        <figure class="imgBlock">
            <img class="imgTennisRocket" src="/images/mainPictures/rockets.jpeg" alt="2 Tennis rockets image">
        </figure>
        <section class="buttonContainer">
            <a class="buttonNewMatch" href="${pageContext.request.contextPath}/new-match" target="_self">Start a new
                match</a>
            <a class="buttonMatches" href="${pageContext.request.contextPath}/matches?page=1" target="_self">View match
                results</a>
        </section>
    </main>
</div>
<div class="footer">
    <footer>
        <p class="footerText">Tennis Scoreboard, project from <a
                href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </footer>
</div>
</body>
</html>