<g:if test="${it}">
    <g:each in="${it.sort{ -it.getBall()}}" var="forecast" status="i" >
        <li><span class="property-value" aria-labelledby="forecast${i}-label">
            ${forecast.score} ${forecast.user} (${forecast.getBall()})
        </span></li>
    </g:each>
</g:if><g:else>
    <li> Нет прогнозов </li>
</g:else>
