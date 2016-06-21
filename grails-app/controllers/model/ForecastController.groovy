package model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured(['ROLE_ADMIN','ROLE_USER'])
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

    def create() {
        respond new Forecast(params)
    }

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
			flash.message = message(code: 'forecast.error.wrongGame', args: forecast.game)
            respond forecast.errors, view:'create'
            return
		}

        forecast.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'forecast.message.create', args: [forecast.score ,forecast.game])
                redirect controller: "game", action:"index", method:"GET"
            }
            '*'{ respond forecast, [status: OK] }
        }
    }

    def createRemote () {
        def forecast = new Forecast(user: params.user, game: params.game,
                score: Score.findOrSaveByFirstTeamAndSecondTeam(params.first, params.second)).save()
        model: [forecast: forecast]
    }

    def edit(Forecast forecast) {
        respond forecast
    }

    @Transactional
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
            flash.message = message(code: 'forecast.error.wrongGame', args: forecast.game)
            respond forecast.errors, view:'edit'
            return
        }

        forecast.save flush:true

        request.withFormat {
            form multipartForm {
//                flash.message = message(code: 'forecast.message.update', args: [forecast.score ,forecast.game])
                redirect controller: "game", action:"index", method:"GET"
            }
            '*'{ respond forecast, [status: OK] }
        }
    }

    @Transactional
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

    def showForecastsByGame(){

        if (!params.game.id) {
            flash.message = "Не задан матч"
            return
        }
        [forecasts: Forecast.findAllByGame(Game.get(params.game.id)).sort{ -it.getBall()}]
    }

    private boolean timeIsOver(Forecast forecast) {
        return  forecast.game.startDate <= new Date() && !getAuthenticatedUser().isAdmin()
    }

    private boolean notCurrentUser(Forecast forecast) {
        return forecast.user.id != getAuthenticatedUser().id && !getAuthenticatedUser().isAdmin()
    }


}
