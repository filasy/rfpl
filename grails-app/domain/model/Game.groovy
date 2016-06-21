package model

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
        rank nullable: false, unique: (['firstTeam','secondTeam'])
        firstTeam nullable: false, unique: (['secondTeam','rank'])
        secondTeam nullable: false, unique: (['firstTeam','rank'])
        startDate nullable: false
        score nullable: true
        forecasts()

    }

    static embedded = ['score']
    static mapping = {
        sort "startDate":"desc"
    }


    @Override
    String toString() {
        firstTeam.toString() + " - " + secondTeam.toString()
    }

    int getBallForUser(User user){
        return forecasts.find { it.user == user }.getBall()?:0
    }
}
