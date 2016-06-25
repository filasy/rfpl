<font color="red">
    ${error}
</font>
<g:if test="${!forecast}">
    <g:if test="${game?.startDate > new Date()}">
        <g:set var="update" value="${'for'.plus(game?.id)}"/>
        <g:formRemote name="create_forecast"
                      url="[controller:'forecast', action:'createByUser', params: ['game.id': game.id, 'user.id':user.id]]"
                      update="${update}">
            <g:textField type="text" name="firstTeam" size="1"/>
            <g:textField type="text" name="secondTeam" size="1"/>
            <g:submitButton name="OK"/>
        </g:formRemote>
    </g:if>
</g:if>
<g:elseif test="${forecast.game?.startDate <= new Date()}">
    ${forecast?.score} (${forecast?.getBall()})
</g:elseif>
<g:else>
    <g:set var="update" value="${'for'.plus(forecast.game.id)}"/>
        <g:remoteLink controller="forecast"
                      action="editAjax"
                      update="${update}" params="[id :forecast.id]">
            ${forecast?.score}
        </g:remoteLink>
</g:else>