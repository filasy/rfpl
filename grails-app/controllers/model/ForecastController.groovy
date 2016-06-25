package model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured('ROLE_ADMIN')
class ForecastController {
    static scaffold = Forecast
	def springSecurityService

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def editAjax(Forecast forecast) {
        respond forecast
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
            render(template: "showInGameIndex", model: [forecast: forecast])
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
}
