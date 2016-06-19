<%@ page import="model.Rank; model.Game" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="game.showResult.label" args="[rank]"/></title>
</head>
<body>
<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="list-game" class="content scaffold-list" role="main">
    <h1>
        <g:message code="game.showResult.label" args="[rank]"/>, <g:message code="game.showResult.infoMessage" args="${new Date()}"/>
    </h1>
    <g:set var="sorted_users" value="${users.sort{-it.getBallByRank(rank)}}"/>
    <div class="scrollable">
        <table>
            <thead>
                <tr>
                    <th><g:message code="game.startDate.label" default="Начало"/></th>
                    <th><g:message code="game.label" default="Матч"/></th>
                    <th><g:message code="game.score.label" default="Факт"/></th>
                    <g:each in="${sorted_users}" status="i" var="user">
                        <th>${user} (${user?.getBallByRank(rank)})</th>
                    </g:each>
                </tr>
            </thead>
            <tbody>
                <g:each in="${games}" status="i" var="game">
                    <g:set var="forecasts" value="${game.forecasts}"/>
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:formatDate formatName="default.date.format" date="${game.startDate}"/></td>
                        <td><g:link method="GET" resource="${game}">${game}</g:link></td>
                        <td>${game?.score}</td>
                        <g:each in="${sorted_users}" status="j" var="user">
                            <g:set var="forecast" value="${forecasts?.find {it.user.id == user.id}}"/>
                            <td><g:if test="${forecast}">${forecast?.score} (${forecast?.getBall()})</g:if></td>
                        </g:each>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    %{--<div class="pagination">--}%
        %{--<g:paginate total="${countGames ?: 0}"/>--}%
    %{--</div>--}%
</div>

</body>
</html>