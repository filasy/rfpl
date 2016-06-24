<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-game" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list game">
                <g:if test="${game?.rank}">
                    <li class="fieldcontain">
                        <span id="rank-label" class="property-label"><g:message code="game.rank.label" default="Score" /></span>
                        <span class="property-value" aria-labelledby="rank-label">${game?.rank?.encodeAsHTML()}</span>
                    </li>
                </g:if>
                <g:if test="${game}">
                    <li class="fieldcontain">
                        <span id="game-label" class="property-label"><g:message code="game.label" default="Матч" /></span>
                        <span class="property-value" aria-labelledby="game-label">${game.encodeAsHTML()}</span>
                    </li>
                </g:if>
                <g:if test="${game?.startDate}">
                    <li class="fieldcontain">
                        <span id="startDate-label" class="property-label"><g:message code="game.startDate.label" default="Start Date" /></span>
                        <span class="property-value" aria-labelledby="startDate-label"><g:formatDate formatName="default.date.format" date="${game?.startDate}" /></span>
                    </li>
                </g:if>
                <g:if test="${game?.score}">
                    <li class="fieldcontain">
                        <span id="score-label" class="property-label"><g:message code="game.score.label" default="Score" /></span>
                        <span class="property-value" aria-labelledby="score-label">${game?.score?.encodeAsHTML()}</span>
                    </li>
                </g:if>
                <g:if test="${game?.forecasts}">
                    <li class="fieldcontain">
                    <span id="forecasts-label" class="property-label"><g:message code="game.forecasts.label" default="Score" /></span>
                        <g:each in="${game?.forecasts.sort{ -it.getBall()}}" var="forecast" status="i" >
                                <span class="property-value" aria-labelledby="forecast${i}-label">
                                    <font color="#a9a9a9">
                                        <g:formatDate format="dd.MM.yy HH:mm" date="${forecast.lastUpdated}"/>
                                    </font>
                                     (${forecast.getBall()}) ${forecast.score} ${forecast.user}
                                </span>
                        </g:each>
                    </li>
                </g:if>

            </ol>
            %{--<f:display bean="game" />--}%
            <g:form resource="${this.game}" method="DELETE">
                <fieldset class="buttons">
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <g:link class="edit" action="edit" resource="${this.game}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:ifAnyGranted>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
