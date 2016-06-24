<%@ page import="model.Rank; model.Game" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="game.showResult.label" args="[rank]"/></title>
</head>
<body>
<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<div id="list-game" class="content scaffold-list" role="main">
    <h1>
        <g:message code="game.showResult.label" args="[rank]"/>, <g:message code="game.showResult.infoMessage" args="${new Date()}"/>
    </h1>

    <g:formRemote class="message" name="search-for"
                  url="[controller:'game', action:'showResultsAjax', params: [id :rank.id]]"
                  update="result">
        <g:select id="user" name="user" from="${rank.getUsers().findAll {!it.isAdmin()}}" optionKey="id" value="" noSelection="['':'-Все игроки-']" />
        <g:submitButton name="search" value="Найти"/>
    </g:formRemote>

    <div class="scrollable" id="result">
        <g:render template="showAjax" model="[games: games, users: users, rank:rank]" />
    </div>
</div>
</body>
</html>