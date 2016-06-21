<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="create-game" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.game}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.game}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: game, field: 'rank', 'error')} ">
                        <label for="rank">
                            <g:message code="game.rank.label" default="Rank" />
                        </label>
                        <g:select id="rank" name="rank.id" from="${model.Rank.findAllByEnabled(true)}" optionKey="id" value="${game?.rank?.id}" class="many-to-one"/>
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: game, field: 'firstTeam', 'error')} required">
                        <label for="firstTeam">
                            <g:message code="game.firstTeam.label" default="First Team" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:select id="firstTeam" name="firstTeam.id" from="${model.Team.list()}" optionKey="id" required="" value="${game?.firstTeam?.id}" class="many-to-one"/>
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: game, field: 'secondTeam', 'error')} required">
                        <label for="secondTeam">
                            <g:message code="game.secondTeam.label" default="Second Team" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:select id="secondTeam" name="secondTeam.id" from="${model.Team.list()}" optionKey="id" required="" value="${game?.secondTeam?.id}" class="many-to-one"/>
                    </div>
                    <div class="fieldcontain ${hasErrors(bean: game, field: 'startDate', 'error')} required">
                        <label for="startDate">
                            <g:message code="game.startDate.label" default="Start Date" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:datePicker name="startDate" precision="minute"  value="${game?.startDate}" years="${new Date().getYear()+1900}" />
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
