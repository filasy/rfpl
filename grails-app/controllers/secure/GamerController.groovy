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
        if (gamer != getAuthenticatedUser()){
            notFound()
        } else {
            respond gamer
        }
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
            println "gamer: " + gamer
            println "getAuthenticatedUser:" + getAuthenticatedUser()
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'gamer.pswd.updated.denied.message')
            respond gamer.errors, view:'changePass'
            return
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

    @Transactional
    def save(Gamer user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rank.errors, view: 'create'
            return
        }

        user.save flush: true

        UserRole.create(user, Role.findByAuthority("ROLE_USER"), true)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gamer.label', default: 'Gamer'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }
}
