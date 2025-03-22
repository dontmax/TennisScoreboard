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
        <div class="tableSet">
            <form class="form" action="${pageContext.request.contextPath}/matches" method="get">
                <input type="hidden" name="page" value="1">
                <label class="textLabel">
                    <c:if test="${message!=null}">
                        <input class="text" type="text" name="filter_by_player_name"  placeholder="${message}">
                    </c:if>
                    <c:if test="${message==null}">
                        <input class="text" type="text" name="filter_by_player_name"  placeholder="Filter by name">
                    </c:if>
                </label>
                <div class="submitContainer">
                    <input class="submit" type="submit" value="Reset Filter">
                </div>
            </form>
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
                            <td>${match.getWinnerName()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <c:if test="${pagination.lastPage > 2}">
                    <a class="pageLink" href="${pageContext.request.contextPath}/matches?page=1${pathName}"> < </a>
                    <c:if test="${pagination.currentPageNumber != 2 || pagination.lastPage > 2}">
                        <a class="${pagination.isPreviousSelected() ? 'selected' : ''}" href="${pageContext.request.contextPath}/matches?page=${pagination.previousPage}${pathName}">${pagination.previousPageView}</a>
                    </c:if>
                    <a class="${pagination.isCurrentSelected() ? 'selected' : ''}" href="${pageContext.request.contextPath}/matches?page=${pagination.currentPage}${pathName}">${pagination.currentPageView}</a>
                    <c:if test="${pagination.currentPageNumber != pagination.previousPageView || pagination.lastPage > 2}">
                        <a class="${pagination.isNextSelected() ? 'selected' : ''}" href="${pageContext.request.contextPath}/matches?page=${pagination.nextPage}${pathName}">${pagination.nextPageView}</a>
                    </c:if>
                    <a class="pageLink" href="${pageContext.request.contextPath}/matches?page=${pagination.lastPage}${pathName}"> > </a>
                </c:if>
            </div>
        </div>
    </main>
</div>
<div class="footer">
    <footer>
        <p class="footerText">Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap</p>
    </footer>
</div>
</body>
</html>