<div id="edit-forecast" class="content scaffold-edit" role="main">
    %{--<h1><g:message code="default.edit.label" args="[entityName]" /></h1>--}%
    <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.forecast}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.forecast}" var="error">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
    </g:hasErrors>
    <g:form resource="${this.forecast}" method="PUT">
        <g:hiddenField name="version" value="${this.forecast?.version}" />
            <f:all bean="forecast" except="user,game"/>
            <input type="submit" value="OK" />
    </g:form>
</div>
