<%@ page import="model.Forecast" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'forecast.label', default: 'Forecast')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-forecast" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-forecast" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <th><g:message code="forecast.user.label" default="User" /></th>

            <th><g:message code="forecast.game.label" default="Game" /></th>

            <th><g:message code="forecast.score.label" default="Score" /></th>

            <g:sortableColumn property="dateCreated" title="${message(code: 'forecast.dateCreated.label', default: 'Date Created')}" />

            <g:sortableColumn property="locked" title="${message(code: 'forecast.locked.label', default: 'Locked')}" />

            <g:sortableColumn property="lastUpdated" title="${message(code: 'forecast.lastUpdated.label', default: 'Last Updated')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${forecastInstanceList}" status="i" var="forecastInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${forecastInstance.id}">${fieldValue(bean: forecastInstance, field: "user")}</g:link></td>

                <td>${fieldValue(bean: forecastInstance, field: "game")}</td>

                <td>${fieldValue(bean: forecastInstance, field: "score")}</td>

                <td><g:formatDate date="${forecastInstance.dateCreated}" /></td>

                <td><g:formatBoolean boolean="${forecastInstance.locked}" /></td>

                <td><g:formatDate date="${forecastInstance.lastUpdated}" /></td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${forecastInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>