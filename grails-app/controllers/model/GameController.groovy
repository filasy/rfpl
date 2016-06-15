package model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class GameController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def springSecurityService

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index(Integer max) {
	    params.max = Math.min(max ?: 10, 100)
        def date = params.date ?: new Date()
        def startDate = date - 7
        def endDate = date + 7
        respond Game.findAllByStartDateBetween(startDate, endDate, params), model:[gameCount: Game.findAllByStartDateBetween(startDate, endDate).size(),
		date: date, user: getAuthenticatedUser()]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def showResults(Integer max){
        if (!params.id) {
            notFound()
        }
        def rank = Rank.get(params.id)
        return [games: Game.findAllByRankAndStartDateLessThan(rank, new Date()), users: rank.getUsers(), rank: rank]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def show(Game game) {
        respond game
    }

    def create() {
        respond new Game(params)
    }

    @Transactional
    def save(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (game.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond game.errors, view:'create'
            return
        }

        game.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*' { respond game, [status: CREATED] }
        }
    }

    def edit(Game game) {
        respond game
    }

    @Transactional
    def update(Game game) {
        if (game == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (game.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond game.errors, view:'edit'
            return
        }

        game.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect game
            }
            '*'{ respond game, [status: OK] }
        }
    }

    @Transactional
    def delete(Game game) {

        if (game == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        game.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'game.label', default: 'Game'), game.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
