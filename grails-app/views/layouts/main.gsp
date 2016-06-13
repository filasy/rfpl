<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> Тотик
                </a>
            </div>
            <sec:ifLoggedIn>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Мои лиги <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <g:each var="rank" in="${model.Rank.findAll()}">
                            <li><a href="${createLink(controller:'game', action: 'showResults', id: rank.id)}">${rank}</a></li>
                        </g:each>
                    </ul>
                </li>
            </sec:ifLoggedIn>
            %{--<div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">--}%
                %{--<ul class="nav navbar-nav navbar-right">--}%
                    %{--<g:pageProperty name="page.nav" />--}%
                %{--</ul>--}%
            %{--</div>--}%
        </div>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo">
        <sec:ifLoggedIn>
            <g:link controller="logout" action="index">Выход (<sec:username/>)</g:link>
        </sec:ifLoggedIn>
    </div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
