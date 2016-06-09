package model

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')

class TeamController {
    static scaffold = Team
}
