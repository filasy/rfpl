<g:set var="id_update" value="${'for'.plus(forecast?.game.id)}"/>
<g:form >
    <g:hiddenField name="version" value="${this.forecast?.version}" />
    <f:all bean="forecast" except="user,game,lastUser"/>
    <g:submitToRemote value="OK"
        url = "[controller: 'forecast', action: 'updateByUser', params: [id: forecast.id]]"
        update = "${id_update}"/>
    <g:submitToRemote value="Удалить"
        url = "[controller: 'forecast', action: 'deleteByUser', params: [id: forecast.id]]"
        method = "DELETE"
        update = "${id_update}"/>
</g:form>



