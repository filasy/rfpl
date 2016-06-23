<g:if test="${forecasts}">
    <g:each in="${forecasts}" var="forecast" status="i" >
        <li><span class="property-value" aria-labelledby="forecast${i}-label">
            ${forecast.score} (${forecast.getBall()}) ${forecast.user}
        </span></li>
    </g:each>
</g:if><g:else>
    <li> Нет прогнозов </li>
</g:else>
