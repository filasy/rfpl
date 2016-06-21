<g:if test="${forecast.game?.startDate <= new Date()}">
    ${forecast?.score}
</g:if><g:else>
    <g:set var="edit_forecast" value="${'edit_forecast'.plus(forecast.id)}"/>
    <div id="${edit_forecast}">
        <g:remoteLink controller="forecast"
                      action="edit"
                      update="${edit_forecast}" params="[id :forecast.id]">
            ${forecast?.score}
        </g:remoteLink>
    </div>
</g:else>