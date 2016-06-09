package model

import secure.User

class Round {
    Rank rank
    Integer roundNumber
    boolean locked = false

    static hasMany = [games: Game]

    static constraints = {
        rank nullable: false
        roundNumber nullable: false, blank: false
    }

    static mapping = {
        sort "roundNumber":"desc"
    }

    @Override
    String toString(){
        "Тур " + roundNumber
    }

    int getBallForUser(User user){
        def result = 0
        games.each {
            result += Forecast.findByUserAndGame(user, it)?.getBall()?:0
        }
        return result
    }
}