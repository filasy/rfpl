<%@ page import="model.Rank; model.Game" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="list-game" class="content scaffold-list" role="main">
	Матчи, начавшиеся до <g:formatDate formatName="default.date.format" date="${new Date()}"/>
    <table>
        <thead>
            <tr>
                <th><g:message code="game.startDate.label" default="Начало"/></th>
                <th><g:message code="game.label" default="Матч"/></th>
                <th><g:message code="game.score.label" default="Факт"/></th>
                <g:each in="${users}" status="i" var="user">
                    <th>${user.name} (${user?.getBallByRank(rank)})
                    </th>
                </g:each>
            </tr>
        </thead>
        <tbody>
            <g:each in="${games}" status="i" var="game">
                <g:set var="forecasts" value="${game.forecasts}"/>
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><g:formatDate formatName="default.date.format" date="${game.startDate}"/></td>
                    <td>${game}</td>
                    <td>${game?.score}</td>
                    <g:each in="${users}" status="j" var="user">
                        <g:set var="forecast" value="${forecasts?.find {it.user.id == user.id}}"/>
                        <td><g:if test="${forecast}">${forecast?.score} (${forecast?.getBall()})</g:if></td>
                    </g:each>
                </tr>
            </g:each>
        </tbody>
    </table>
    %{--<div class="pagination">--}%
        %{--<g:paginate total="${countGames ?: 0}"/>--}%
    %{--</div>--}%
</div>

</body>
</html>