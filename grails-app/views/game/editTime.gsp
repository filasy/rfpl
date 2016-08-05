<g:set var="id_update" value="${"editTime_game".plus(game?.id)}"/>
<g:form >
<div class="fieldcontain ${hasErrors(bean: game, field: 'startDate', 'error')} required">
    <label for="startDate">
        <g:message code="game.startDate.label" default="Start Date" />
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="startDate" precision="minute"  value="${game?.startDate}" years="${new Date().getYear()+1900}" />
    <g:submitToRemote value="OK"
                      url = "[controller: 'game', action: 'updateTime', params: [id: game?.id]]"
                      update = "${id_update}"/>
</div>
</g:form>