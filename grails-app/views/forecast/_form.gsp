<%@ page import="model.Forecast" %>

<div class="fieldcontain ${hasErrors(bean: forecastInstance, field: 'user', 'error')} required">
    <label for="user">
        <g:message code="forecast.user.label" default="User" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="user" name="user.id" from="${secure.User.list()}" optionKey="id" required="" value="${forecastInstance?.user?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: forecastInstance, field: 'game', 'error')} required">
    <label for="game">
        <g:message code="forecast.game.label" default="Game" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="game" name="game.id" from="${model.Game.list()}" optionKey="id" required="" value="${forecastInstance?.game?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: forecastInstance, field: 'score', 'error')} required">
    <label for="score">
        <g:message code="forecast.score.label" default="Score" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="score" name="score.id" from="${model.Score.list()}" optionKey="id" required="" value="${forecastInstance?.score?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: forecastInstance, field: 'locked', 'error')} ">
    <label for="locked">
        <g:message code="forecast.locked.label" default="Locked" />

    </label>
    <g:checkBox readonly="true" name="locked" value="${forecastInstance?.locked}" />

</div>