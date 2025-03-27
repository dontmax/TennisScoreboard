<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/matchesStyle.css" rel="stylesheet" type="text/css">
    <meta charset="UTF-8">
    <title>Tennis Scoreboard | Finished Matches</title>
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
        <h1>Matches</h1>
        <section class="nameSearchSection">
            <form class="searchForm" action="${pageContext.request.contextPath}/matches?page=1" method="post">
                <label class="textContainer">
                    <input class="text" type="text" name="filter_by_player_name" placeholder="Filter by name">
                </label>
                <div class="findButtonContainer">
                    <input class="findButton" type="submit" value="Find">
                </div>
            </form>
            <form class="resetForm" action="${pageContext.request.contextPath}/matches?page=1" method="get">
                <div class="resetButtonContainer">
                    <input class="resetButton" type="submit" value="Reset Filter">
                </div>
            </form>
        </section>
        <div class="tableSet">
            <table class="table">
                <thead>
                <tr>
                    <td>Player One</td>
                    <td>Player Two</td>
                    <td>Winner</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="match" items="${matches}">
                    <tr>
                        <td>${match.getFirstPlayerName()}</td>
                        <td>${match.getSecondPlayerName()}</td>
                        <td class="winner"><p>${match.getWinnerName()}</p></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <c:if test="${pagination.lastPage > 1}">
                    <a class="pageLink" href="${pageContext.request.contextPath}/matches?page=1${pathName}"> < </a>
                    <c:if test="${(pagination.currentPageNumber != 1&&pagination.lastPage==2) || pagination.lastPage > 2}">
                        <a class="${pagination.isPreviousSelected() ? 'selected' : ''}"
                           href="${pageContext.request.contextPath}/matches?page=${pagination.previousPage}${pathName}">${pagination.previousPageView}</a>
                    </c:if>
                    <a class="${pagination.isCurrentSelected() ? 'selected' : ''}"
                       href="${pageContext.request.contextPath}/matches?page=${pagination.currentPage}${pathName}">${pagination.currentPageView}</a>
                    <c:if test="${(pagination.currentPageNumber == 1&&pagination.lastPage==2) || pagination.lastPage > 2}">
                        <a class="${pagination.isNextSelected() ? 'selected' : ''}"
                           href="${pageContext.request.contextPath}/matches?page=${pagination.nextPage}${pathName}">${pagination.nextPageView}</a>
                    </c:if>
                    <a class="pageLink"
                       href="${pageContext.request.contextPath}/matches?page=${pagination.lastPage}${pathName}"> > </a>
                </c:if>
            </div>
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