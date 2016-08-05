<sec:ifAnyGranted roles="ROLE_ADMIN">
        <g:remoteLink controller="game"
                      action="editTime"
                      update="${"editTime_game" + game?.id}" params="[id :game.id]">
            |Время|
        </g:remoteLink>
</sec:ifAnyGranted>