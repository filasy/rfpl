<!doctype html>
<html>
    <head>
        <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <g:if env="development">
            <g:if test="${Throwable.isInstance(exception)}">
                <g:renderException exception="${exception}" />
            </g:if>
            <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
                <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
            </g:elseif>
            <g:else>
                <ul class="errors">
                    <li>Произошла внутренняя ошибка сервера</li>
                    <li>Исключение: ${exception}</li>
                    <li>Сообщение: ${message}</li>
                    <li>Путь: ${path}</li>
                </ul>
            </g:else>
        </g:if>
        <g:else>
            <ul class="errors">
                <li>Произошла внутренняя ошибка. Просьба повторить попытку и в случае повторения ошибки сообщить подробности на jFilasy@gmail.com</li>
            </ul>
        </g:else>
    </body>
</html>
