package model

import grails.plugin.springsecurity.annotation.Secured
import secure.Gamer

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
        def startDate = date - 3
        def endDate = date + 3
        def list = Game.findAllByStartDateBetweenAndRankInList(startDate, endDate, getAuthenticatedUser().ranks)
//        respond Game.findAllByStartDateBetweenAndRankInList( startDate, endDate,
//                    getAuthenticatedUser().ranks, params),
//                model:[gameCount: Game.findAllByStartDateBetweenAndRankInList(startDate, endDate,
//                       getAuthenticatedUser().ranks).size(),
//                       date: date, user: getAuthenticatedUser()]
        respond list, model:[gameCount: list.size(), date: date, user: getAuthenticatedUser()]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def showResults(Integer max){
        params.max = Math.min(max ?: 8, 100)
        def rank = Rank.get(params.id)
        if (rank) {
            [games: Game.findAllByRankAndStartDateLessThan(rank, new Date()),
//             count: Game.findAllByRankAndStartDateLessThan(rank, new Date()),
             users: Gamer.get(params.user) ?: rank?.getUsers().findAll {!it.isAdmin()}.sort{-it.getBallByRank(rank)},
             rank: rank
             ]
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def showResultsAjax(Integer max){
        params.max = Math.min(max ?: 8, 100)
        def rank = Rank.get(params.id)
        if (rank) {
            render(template: "showAjax",
                    model: [games: Game.findAllByRankAndStartDateLessThan(rank, new Date()),
//                            count: Game.findAllByRankAndStartDateLessThan(rank, new Date()),
                            users: Gamer.get(params.user) ?: rank?.getUsers().sort{-it.getBallByRank(rank)},
                            rank: rank])
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def show(Game game) {
        if (game?.startDate <= new Date() || getAuthenticatedUser().isAdmin()){
            respond game
        } else {
            notFound()
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def create() {
        respond new Game(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
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

        if (!game?.rank?.enabled) {
            transactionStatus.setRollbackOnly()
            lash.message = message(code: 'game.error.rankDisabled', args: message(code: 'game.label', default: 'Game'))
            respond game.errors, view:'create'
            return
        }

        game.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), game.id, game])
//                redirect game
                redirect controller: "game", action:"index", method:"GET"
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
