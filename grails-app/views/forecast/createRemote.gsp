<g:if test="${forecast.game?.startDate <= new Date()}">
    ${forecast?.score}
</g:if><g:else>
    <g:link method="GET" resource="${forecast}">${forecast?.score}</g:link>
</g:else>