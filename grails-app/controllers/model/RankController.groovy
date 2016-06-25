package model

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import secure.Gamer

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class RankController {

    static scaffold = Rank

    @Transactional
    def save(Rank rank) {
        if (rank == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rank.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rank.errors, view: 'create'
            return
        }

        params.users.each {
            rank.addToUsers(Gamer.get(it))
        }
        rank.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rank.label', default: 'Rank'), rank.id])
                redirect rank
            }
            '*' { respond rank, [status: CREATED] }
        }
    }


    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
