package model

import grails.plugin.springsecurity.annotation.Secured
import secure.Gamer

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured('ROLE_ADMIN')
class GameController {

    static scaffold = Game
    def springSecurityService

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index(Integer max) {
	    params.max = Math.min(max ?: 10, 100)
        def date = params.date ?: new Date()
        date.setHours(0)
        date.setMinutes(0)
        def startDate = date.minus(3)
        def endDate = date.plus(3)
        def list = Game.findAllByStartDateBetweenAndRankInList(startDate, endDate, getAuthenticatedUser().ranks)
        respond list, model:[gameCount: list.size(), date: date, user: getAuthenticatedUser()]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def showResults(Integer max){
        params.max = Math.min(max ?: 8, 100)
        def rank = Rank.get(params.id)
        if (rank) {
            respond Game.findAllByRankAndStartDateLessThan(rank, new Date(), params),
                    model: [gameCount: Game.findAllByRankAndStartDateLessThan(rank, new Date()).size(),
                            users: rank?.getGamers().sort{-it.getBallByRank(rank)},
                            rank: rank]
        } else {
            notFound()
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
    def showFByGame(){
        if (!params.game.id) {
            notFound()
        } else {
            def game = Game.get(params.game.id)
           // [forecasts: game?.startDate <= new Date() ? game?.forecasts?.sort { -it.getBall() } : null]
		[forecasts: game?.forecasts]
        }
    }

    def editTime(Game game){
        [game: game]
    }

    def updateTime(Game game){
        game.startDate = params.startDate
        game.save(flush: true)
        [game: game]
    }
	
//	@Secured(['ROLE_USER'])
//   	def editFact(Game game){
//        [game: game]
//    }
	
//	@Secured(['ROLE_USER'])
//    def updateFact(Game game){
//		game?.startDate >= new Date() ? game.score = params.score        
//        game.save(flush: true)
//        [game: game]
//    }

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
                redirect controller: "game", action:"index", method:"GET"
            }
            '*' { respond game, [status: CREATED] }
        }
    }
}
