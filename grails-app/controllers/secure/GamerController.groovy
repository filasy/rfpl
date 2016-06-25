package secure

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class GamerController {

    def springSecurityService
    static scaffold = Gamer

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def changePass(Gamer gamer) {
        respond gamer
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def changePswd(Gamer gamer){
        if (gamer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (gamer != getAuthenticatedUser()){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'gamer.pswd.updated.denied.message')
            respond gamer.errors, view:'changePass'
        }

        if (gamer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond gamer.errors, view:'changePass'
            return
        }

        gamer.save flush:true
        springSecurityService.reauthenticate gamer.username
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'gamer.pswd.updated.message')
                redirect controller: "game", action: "index", method:"GET"
            }
            '*'{ respond gamer, [status: OK] }
        }
    }
}
