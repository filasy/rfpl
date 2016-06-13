package model

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ForecastController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Forecast.list(params), model:[forecastCount: Forecast.count()]
    }

    def show(Forecast forecast) {
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

        if (forecast.hasErrors()) {
            transactionStatus.setRollbackOnly()
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
