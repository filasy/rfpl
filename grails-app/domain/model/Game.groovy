package model

import org.grails.databinding.BindingFormat
import secure.User

class Game {
    Date startDate
    Team firstTeam
    Team secondTeam
    Score score
    Rank rank
    SortedSet forecasts
    static hasMany = [forecasts: Forecast]

    static constraints = {
        startDate nullable: false
        firstTeam nullable: false, unique: (['secondTeam','rank'])
        secondTeam nullable: false, unique: (['firstTeam','rank'])
        score nullable: true
        rank nullable: false, unique: (['firstTeam','secondTeam'])
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
