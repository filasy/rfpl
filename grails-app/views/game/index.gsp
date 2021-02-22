<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.home.label"/></title>
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
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form action="index" class="message">
                <g:datePicker id="date" name="date" value="${date}" default="${new Date()}" precision="day" years="${2020..2026}"/>
                <g:submitButton name="search" value="Найти"/>
                <font color="#a9a9a9">
                    <g:formatDate date="${date-4}" format="dd.MM.yy"/> - <g:formatDate date="${date+2}" format="dd.MM.yy"/>
                </font>
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
                            <td>
                                <g:set var="id_game" value="${'game'.plus(game.id)}"/>
                               %{-- <g:if test="${game?.startDate <= new Date()}"> --}%
                                    <g:remoteLink action="showFByGame"
                                                  update="${id_game}" params="['game.id':game.id]"
                                                  onSuccess="showHide('${id_game}')">${game}
                                    </g:remoteLink>
                                    <div id="${id_game}" style="display: none;"></div>
                                %{--</g:if><g:else> --}%
                                %{--        ${game} --}%
                                %{--</g:else> --}%
                                <sec:ifAnyGranted roles="ROLE_ADMIN"><g:link method="GET" resource="${game}"> |Факт|</g:link></sec:ifAnyGranted>
                                <sec:ifAnyGranted roles="ROLE_ADMIN">
                                    <div id="${"editTime_" + id_game}">
                                        <g:remoteLink controller="game"
                                                      action="editTime"
                                                      update="${"editTime_"+ id_game}" params="[id :game.id]">
                                            |Время|
                                        </g:remoteLink>
                                    </div>
                                </sec:ifAnyGranted>
                                %{--юзер может проставить факт--}%
                                %{-- <g:if test="${game?.startDate >= new Date()}"> --}%                                
                                %{--<sec:ifAnyGranted roles="ROLE_USER"> --}%
                                %{--    <div id="${"editFact_" + id_game}"> --}%
                                %{--        <g:remoteLink controller="game" --}%
                                %{--                      action="editFact" --}%
                                %{--                      update="${"editFact_"+ id_game}" params="[id :game.id]"> --}%
                                %{--            /Факт/
                                %{--        </g:remoteLink> --}%
                                %{--    </div> --}%
                                %{--</sec:ifAnyGranted> --}%
                                %{-- </g:if> --}%
                            </td>
                            <td>
                                <g:set var="forecast" value="${game.forecasts.find {it.user == user}}"/>
                                <div id="${'for'.plus(game.id)}">
                                    <g:render template="/forecast/showInGameIndex" model="[forecast: forecast, game: game, user:user]" />
                                </div>
                            </td>
                            <td>
                                ${fieldValue(bean: game, field: "score")}
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            %{--<div class="pagination">--}%
                %{--<g:paginate total="${gameCount ?: 0}" params="['date': date]"/>--}%
            %{--</div>--}%
        </div>
    <script type="text/javascript">
        function showHide(el_id) {
            if (document.getElementById(el_id)) {
                var obj = document.getElementById(el_id);
                if (obj.style.display != "block") {
                    obj.style.display = "block";

                }
                else obj.style.display = "none";
            }
        }
    </script>
    </body>
</html>
