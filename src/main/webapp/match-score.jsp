<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/matchScoreStyle.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Tennis Scoreboard | Match Score</title>
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
        <c:if test="${winner!=null}">
            <div class="model">
                <div class="modelContent">
                    <h2 class="bordered-text">winner</h2>
                    <p class="bordered-text">${winner}</p>
                    <div class="modelLinkContainer">
                        <a class="modelLink" href="${pageContext.request.contextPath}/" target="_self">Home</a>
                        <a class="modelLink" href="${pageContext.request.contextPath}/matches?page=1" target="_self">Matches</a>
                    </div>
                </div>
            </div>
        </c:if>
        <h1><strong>Current match</strong></h1>
        <figure class="imgBlock">
            <img class="imgBallInWatter" src="images/mainPictures/ballInWatter.jpg" alt="2 Tennis rockets image">
        </figure>
        <section>
            <table class="table">
                <thead>
                <tr>
                    <th>Players</th>
                    <th>Sets</th>
                    <th>Games</th>
                    <th>Points</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr class="firstPlayer">
                    <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="POST">
                        <td>${currentMatch.getFirstPlayer().getName()}</td>
                        <td>${currentMatch.getFirstPlayerSets()}</td>
                        <td>${currentMatch.getFirstPlayerGames()}</td>
                        <td>${currentMatch.getFirstPlayerPointsView()}</td>
                        <input type="hidden" name="scoreWinnerId" value="${currentMatch.getFirstPlayer().getId()}">
                        <td><input class="buttonScore" type="submit" value="Score"></td>
                    </form>
                </tr>
                <tr class="secondPlayer">
                    <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="POST">
                        <td>${currentMatch.getSecondPlayer().getName()}</td>
                        <td>${currentMatch.getSecondPlayerSets()}</td>
                        <td>${currentMatch.getSecondPlayerGames()}</td>
                        <td>${currentMatch.getSecondPlayerPointsView()}</td>
                        <input type="hidden" name="scoreWinnerId" value="${currentMatch.getSecondPlayer().getId()}">
                        <td><input class="buttonScore" type="submit" value="Score"></td>
                    </form>
                </tr>
                </tbody>
            </table>
        </section>
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