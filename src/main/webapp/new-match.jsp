<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/newMatchStyle.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Tennis Scoreboard | New match</title>
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
        <h1><strong>Start new match</strong></h1>
        <figure class="imgBlock">
            <img class="imgNewMatch" src="images/mainPictures/ballOnField.jpeg" alt="Tennis player image">
        </figure>
        <div class="exceptionContainer"><label class="exceptionLabel">
            <c:if test="${message!=null}">
                <p class="message">${message}</p>
            </c:if>
        </label></div>
        <div class="nameForm">
            <form class="form" action="${pageContext.request.contextPath}/new-match" method="POST">
                <p class="players">Player one:</p>
                <label>
                    <input class="nameTextLabel" type="text" name="firstPlayerName" placeholder="Name"/>
                </label>
                <p class="players">Player two:</p>
                <label>
                    <input class="nameTextLabel" type="text" name="secondPlayerName" placeholder="Name"/>
                </label>
                <br><br>
                <input class="startButton" type="submit" value="Start">
            </form>
        </div>
    </main>
</div>
<div class="footer">
    <footer>
        <p class="footerText">Tennis Scoreboard, project from <a
                href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap</p>
    </footer>
</div>
</body>
</html>