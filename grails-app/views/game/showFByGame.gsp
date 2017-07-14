<g:if test="${forecasts}">
    <ol>
        <g:each in="${forecasts}" var="forecast" status="i" >
            <li><span class="property-value" aria-labelledby="forecast${i}-label">
           %{--      ${forecast.score} (${forecast.getBall()}) ${forecast.user} --}%
                    ${forecast.user}
            </span></li>
        </g:each>
    </ol>
</g:if><g:else>
    <li> Нет прогнозов </li>
</g:else>
