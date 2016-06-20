package model

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import secure.User

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class RankController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Rank.list(params), model:[rankCount: Rank.count()]
    }

    def show(Rank rank) {
        respond rank
    }

    def create() {
        respond new Rank(params)
    }

    @Transactional
    def save(Rank rank) {
        if (rank == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rank.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rank.errors, view:'create'
            return
        }
		
		params.users.each {						
			rank.addToUsers(User.get(it))
		}
		rank.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rank.label', default: 'Rank'), rank.id])
                redirect rank
            }
            '*' { respond rank, [status: CREATED] }
        }
    }

    def edit(Rank rank) {
        respond rank
    }

    @Transactional
    def update(Rank rank) {
        if (rank == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rank.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rank.errors, view:'edit'
            return
        }

//        println "params: " + params
//        println "params.users: " + params.users
//        println "rank.users: " + rank.users
//        params.users.each {
//            println "User.get("+ it +"): " + User.get(it)
////            rank.addToUsers(User.get(it))
//        }

        rank.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rank.label', default: 'Rank'), rank.id])
                redirect rank
            }
            '*'{ respond rank, [status: OK] }
        }
    }

    @Transactional
    def delete(Rank rank) {

        if (rank == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rank.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rank.label', default: 'Rank'), rank.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
