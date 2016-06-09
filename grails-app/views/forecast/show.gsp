<%@ page import="model.Forecast" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'forecast.label', default: 'Forecast')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-forecast" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-forecast" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list forecast">

        <g:if test="${forecastInstance?.user}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="forecast.user.label" default="User" /></span>
                <span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${forecastInstance?.user?.id}">${forecastInstance?.user?.encodeAsHTML()}</g:link></span>
            </li>
        </g:if>

        <g:if test="${forecastInstance?.game}">
            <li class="fieldcontain">
                <span id="game-label" class="property-label"><g:message code="forecast.game.label" default="Game" /></span>

                <span class="property-value" aria-labelledby="game-label"><g:link controller="game" action="show" id="${forecastInstance?.game?.id}">${forecastInstance?.game?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${forecastInstance?.score}">
            <li class="fieldcontain">
                <span id="score-label" class="property-label"><g:message code="forecast.score.label" default="Score" /></span>

                <span class="property-value" aria-labelledby="score-label"><g:link controller="score" action="show" id="${forecastInstance?.score?.id}">${forecastInstance?.score?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${forecastInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="forecast.dateCreated.label" default="Date Created" /></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${forecastInstance?.dateCreated}" /></span>
            </li>
        </g:if>

        <g:if test="${forecastInstance?.lastUpdated}">
            <li class="fieldcontain">
                <span id="lastUpdated-label" class="property-label"><g:message code="forecast.lastUpdated.label" default="Last Updated" /></span>

                <span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${forecastInstance?.lastUpdated}" /></span>

            </li>
        </g:if>

        <g:if test="${forecastInstance?.locked}">
            <li class="fieldcontain">
                <span id="locked-label" class="property-label"><g:message code="forecast.locked.label" default="Locked" /></span>

                <span class="property-value" aria-labelledby="locked-label"><g:formatBoolean boolean="${forecastInstance?.locked}" /></span>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource:forecastInstance, action:'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${forecastInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>