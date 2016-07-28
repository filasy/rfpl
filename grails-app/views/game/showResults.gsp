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
        <g:message code="game.showResult.label" args="[rank]"/>
        <font color="#a9a9a9">
            <g:message code="game.showResult.infoMessage"/>
        </font>
    </h1>

    <div id="result" style="overflow-x: scroll">
            <table>
                <thead>
                <tr>
                    <th><g:message code="game.startDate.label" default="Начало"/></th>
                    <th><g:message code="game.label" default="Матч"/></th>
                    <th><g:message code="game.score.label" default="Факт"/></th>
                    <g:each in="${users}" status="i" var="user">
                        <th>${user} (${user?.getBallByRank(rank)})</th>
                    </g:each>
                </tr>
                </thead>
                <tbody>
                <g:each in="${gameList}" status="i" var="game">
                    <g:set var="forecasts" value="${game.forecasts}"/>
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:formatDate formatName="default.date.format" date="${game.startDate}"/></td>
                        <td><g:link method="GET" resource="${game}">${game}</g:link></td>
                        <td>${game?.score}</td>
                        <g:each in="${users}" status="j" var="user">
                            <g:set var="forecast" value="${forecasts?.find {it.user.id == user.id}}"/>
                            <td>
                                <g:if test="${forecast}">
                                    <g:if test="${forecast?.lastUpdated > game?.startDate}">
                                        <font color="#1e90ff">
                                            ${forecast?.score} (${forecast?.getBall()})
                                        </font>
                                    </g:if><g:else>
                                    ${forecast?.score} (${forecast?.getBall()})
                                </g:else>
                                </g:if>
                            </td>
                        </g:each>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div><div class="pagination">
        <g:paginate total="${gameCount ?: 0}" id="${rank.id}" omitNext="true" omitPrev="true" />
    </div>
</div>
</body>
</html>