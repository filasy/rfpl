package model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured('ROLE_ADMIN')
class ForecastController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_ADMIN')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Forecast.list(params), model:[forecastCount: Forecast.count()]
    }

    def show(Forecast forecast) {
        if (forecast.user.id != getAuthenticatedUser().id && !getAuthenticatedUser().isAdmin()){
            flash.message = message(code: 'springSecurity.denied.message')
            return
        }
        respond forecast
    }

    @Secured('ROLE_ADMIN')
    def create() {
        Forecast forecast = new Forecast(params)
        respond forecast
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    @Transactional
    def save(Forecast forecast) {
        if (forecast == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (forecast.hasErrors() ) {
            transactionStatus.setRollbackOnly()
            respond forecast.errors, view:'create'
            return
        }

        if (notCurrentUser(forecast)){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'forecast.error.wrongUser', args: forecast.user)
            respond forecast.errors, view:'create'
            return
        }
		
		if (timeIsOver(forecast)){
			transactionStatus.setRollbackOnly()
			flash.message = message(code: 'forecast.error.timeIsOver')
            respond forecast.errors, view:'create'
            return
		}
        forecast.lastUser = getAuthenticatedUser()
        forecast.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'forecast.message.create', args: [forecast.score ,forecast.game])
                redirect controller: "game", action:"index", method:"GET"
            }
            '*'{ respond forecast, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def edit(Forecast forecast) {
        respond forecast
    }

    @Transactional
    @Secured('ROLE_ADMIN')
    def update(Forecast forecast) {
        if (forecast == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (forecast.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond forecast.errors, view:'edit'
            return
        }

        if (notCurrentUser(forecast)){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'forecast.error.wrongUser', args: forecast.user)
            respond forecast.errors, view:'edit'
            return
        }

        if (timeIsOver(forecast)){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'forecast.error.timeIsOver')
            respond forecast.errors, view:'edit'
            return
        }
        forecast.lastUser = getAuthenticatedUser()
        forecast.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'forecast.message.update', args: [forecast.score ,forecast.game])
                redirect controller: "game", action:"index", method:"GET"
            }
            '*'{ respond forecast, [status: OK] }
        }
    }

    @Transactional
    @Secured('ROLE_ADMIN')
    def delete(Forecast forecast) {

        if (forecast == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (notCurrentUser(forecast)){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'forecast.error.wrongUser', args: forecast.user)
            return
        }

        forecast.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'forecast.message.delete', args: [forecast.score, forecast.game])
                redirect controller: "game", action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'forecast.label', default: 'Forecast'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def createByUser () {
        def error, forecast
        if (Game.get(params?.game?.id)?.startDate <= new Date() && !getAuthenticatedUser().isAdmin()) {
            error = message(code: 'forecast.error.timeIsOver')
        } else {
            forecast = new Forecast(user: params.user, game: params.game, lastUser: getAuthenticatedUser(),
                    score: Score.findOrSaveByFirstTeamAndSecondTeam(params.firstTeam, params.secondTeam)).save()
        }
        render(template: "showInGameIndex", model: [forecast: forecast, error: error])

    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def updateByUser(Forecast forecast) {
        if (Game.get(forecast?.game?.id)?.startDate <= new Date() && !getAuthenticatedUser().isAdmin()) {
            render(template: "showInGameIndex", model: [forecast: null, error: message(code: 'forecast.error.timeIsOver')])
        } else {
            forecast.lastUser = getAuthenticatedUser()
            forecast.save flush:true
            render(template: "showInGameIndex", model: [forecast])
        }

    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def deleteByUser(Forecast forecast) {
        def error
        if (Game.get(forecast?.game?.id)?.startDate <= new Date() && !getAuthenticatedUser().isAdmin()) {
            error = message(code: 'forecast.error.timeIsOver')
        } else {
            forecast.delete flush: true
        }
        render(template: "showInGameIndex", model: [forecast: Forecast.findById(params.id),
                                                    game: forecast?.game, user: getAuthenticatedUser(), error: error])
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def showAllByGame(){
        if (!params.game.id) {
            notFound()
        } else {
            def game = Game.get(params.game.id)
            [forecasts: game?.startDate <= new Date() ? Forecast.findAllByGame(game).sort { -it.getBall() } : null]
        }
    }

    private boolean timeIsOver(Forecast forecast) {
        return  forecast.game.startDate <= new Date() && !getAuthenticatedUser().isAdmin()
    }

    private boolean notCurrentUser(Forecast forecast) {
        return forecast.user.id != getAuthenticatedUser().id && !getAuthenticatedUser().isAdmin()
    }


}
