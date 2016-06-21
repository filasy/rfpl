<g:if test="${!forecast}">
    <g:set var="id_update" value="${'for'.plus(game?.id)}"/>
        <g:formRemote name="create_forecast"
                      url="[controller:'forecast', action:'createByUser', params: ['game.id': game.id, 'user.id':user.id]]"
                      update="${id_update}">
            <g:textField type="text" name="firstTeam" size="1"/>
            <g:textField type="text" name="secondTeam" size="1"/>
            <g:submitButton name="OK"/>
        </g:formRemote>
</g:if>
<g:elseif test="${forecast.game?.startDate <= new Date()}">
    ${forecast?.score}
</g:elseif><g:else>
    <g:set var="id_update" value="${'for'.plus(forecast.game.id)}"/>
        <g:remoteLink controller="forecast"
                      action="edit"
                      update="${id_update}" params="[id :forecast.id]">
            ${forecast?.score}
        </g:remoteLink>
</g:else>