<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-game" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form action="index" class="message">
                <g:datePicker id="date" name="date"
                              value="${date}"
                              default="${new Date()}"
                              precision="day"
                              years="${2015..2017}"/>
                <g:submitButton name="search" value="Найти"/>
            </g:form>
            <div class="scrollable">
                <table>
                    <thread>
                        <tr>
                            <g:sortableColumn property="startDate" title="${message(code: 'game.startDate.label', default: 'Дата')}" />
                            <th><g:message code="game.label" default="Матч" /></th>
                            <th><g:message code="game.forecasts.grid.label" default="Прогнозы" /></th>
                            <g:sortableColumn property="score" title="${message(code: 'game.score.label', default: 'Факт')}" />
                        </tr>
                    </thread>
                    <tbody>
                    <g:each in="${gameList}" var="game" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><g:formatDate formatName="default.date.format" date="${game?.startDate}"/></td>
                            %{--<td><g:link method="GET" resource="${game}">${game}</g:link></td>--}%
                            <td>
                                <a href="javascript:void(0)" onclick="showHide('${'forecast_'.plus(game.id)}')">${game}</a>
                                <div id="${'forecast_'.plus(game.id)}" style="display: none;">
                                    <g:render template="forecasts" bean="${game?.forecasts}"></g:render>
                                </div>
                            </td>
                            <td>
                                <g:set var="forecast" value="${game.forecasts.find {it.user == user}}"/>
                                <g:if test="${game?.startDate <= new Date()}">
                                    ${forecast?.score}
                                </g:if><g:elseif test="${forecast}">
                                    <g:link method="GET" resource="${forecast}">${forecast?.score}</g:link>
                                </g:elseif>
                                <g:else>
                                    %{--<g:remoteLink controller="forecast" action="create" method="GET" params="['game.id': game.id, 'user.id':user.id]">remoteLink</g:remoteLink>--}%
                                    <g:link controller="forecast" action="create" params="['game.id': game.id, 'user.id':user.id]">Добавить</g:link>
                                </g:else>
                            </td>
                            <td>
                                <sec:ifAnyGranted roles="ROLE_ADMIN"><g:link method="GET" resource="${game?.score}">${fieldValue(bean: game, field: "score")}</g:link> </sec:ifAnyGranted>
                                <sec:ifAnyGranted roles="ROLE_USER"> ${fieldValue(bean: game, field: "score")}</sec:ifAnyGranted>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <g:paginate total="${gameCount ?: 0}" />
            </div>
        </div>
    <script type="text/javascript">
        function showHide(element_id) {
            if (document.getElementById(element_id)) {
                var obj = document.getElementById(element_id);
                if (obj.style.display != "block") {
                    obj.style.display = "block";
                }
                else obj.style.display = "none";
            }
//            else alert("Элемент с id: " + element_id + " не найден!");
        }
    </script>
    </body>
</html>