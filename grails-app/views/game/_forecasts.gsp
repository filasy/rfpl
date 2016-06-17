<g:each in="${it.sort{ -it.getBall()}}" var="forecast" status="i" >
    <li><span class="property-value" aria-labelledby="forecast${i}-label">
            ${forecast.score} ${forecast.user} (${forecast.getBall()})
            %{--<g:formatDate date="${forecast.lastUpdated}"></g:formatDate>--}%
    </span></li>
</g:each>