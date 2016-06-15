package model

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class ForecastController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Forecast.list(params), model:[forecastCount: Forecast.count()]
    }

    def show(Forecast forecast) {
        respond forecast
    }
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def create() {
        respond new Forecast(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
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

        if (forecast.user.id != getAuthenticatedUser().id){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'forecast.error.wrongUser', args: forecast.user)
            respond forecast.errors, view:'create'
            return
        }

        forecast.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'forecast.label', default: 'Forecast'), forecast.id])
                redirect forecast
            }
            '*' { respond forecast, [status: CREATED] }
        }
    }
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def edit(Forecast forecast) {
        respond forecast
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
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

        if (forecast.user.id != getAuthenticatedUser().id){
            transactionStatus.setRollbackOnly()
            flash.message = message(code: 'forecast.error.wrongUser', args: forecast.user)
            respond forecast.errors, view:'edit'
            return
        }

        forecast.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'forecast.label', default: 'Forecast'), forecast.id])
                redirect forecast
            }
            '*'{ respond forecast, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def delete(Forecast forecast) {

        if (forecast == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        forecast.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'forecast.label', default: 'Forecast'), forecast.id])
                redirect action:"index", method:"GET"
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
}
