package model

import org.grails.databinding.BindingFormat
import secure.User

class Game {
    @BindingFormat('dd:MM:yyyy HH:mm')
    Date startDate
    Team firstTeam
    Team secondTeam
    Score score
    Rank rank

    static hasMany = [forecasts: Forecast]

    static constraints = {
        startDate nullable: false
        firstTeam nullable: false, unique: ('secondTeam')
        secondTeam nullable: false, unique: ('firstTeam')
        score nullable: true
        rank nullable: false
    }

    static mapping = {
        sort "startDate":"desc"
//        sort "forecasts": "asc"
    }

    @Override
    String toString() {
        firstTeam.toString() + " - " + secondTeam.toString()
    }

    int getBallForUser(User user){
        return forecasts.find { it.user == user }.getBall()?:0
    }
}
