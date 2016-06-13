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
    %{--<g:form action="showResults" class="message">--}%
        %{--<g:select name="userID" from="${secure.User.findAll()}" optionKey="id" noSelection="['':'-Все участники-']" value="${selectedUser}"/>--}%
        %{--<g:submitButton name="search" value="Применить"/>--}%
    %{--</g:form>--}%
    Матчи, начавшиеся до <g:formatDate format="yyyy-MM-dd HH:MM:ss" date="${new Date()}"/>
    <table>
        <thead>
            <tr>
                <th><g:message code="game.startDate.label" default="Начало"/></th>
                <th><g:message code="game.label" default="Матч"/></th>
                <th><g:message code="game.score.label" default="Факт"/></th>
                <g:each in="${users}" status="i" var="user">
                    <th>${user.name}
                      ${user.getBall()}
                    </th>
                </g:each>
            </tr>
        </thead>
        <tbody>
            <g:each in="${games}" status="i" var="game">
                <g:set var="forecasts" value="${game.forecasts}"/>
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${game.startDate}</td>
                    <td>${game}</td>
                    <td>${game?.score}</td>
                    <g:each in="${users}" status="j" var="user">
                        <g:set var="forecast" value="${forecasts?.find {it.user.id == user.id}}"/>
                        <td><g:if test="${forecast}">${forecast} (${forecast?.getBall()})</g:if></td>
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