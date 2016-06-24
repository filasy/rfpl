package model

import secure.Gamer

class Forecast implements Comparable {
    Gamer user
    Gamer lastUser
    Score score
    Date dateCreated
    Date lastUpdated

    static belongsTo = [game: Game]

    static constraints = {
        user nullable: false, unique: ('game')
        game nullable: false, unique: ('user')
//        score()
        score validator: { Score s, Forecast f ->
            return f.game.startDate > new Date() || f.user.isAdmin()
        }
        dateCreated()
        lastUpdated()
        lastUser(nullable: true)
    }

    @Override
    String toString() {
        return score.toString() + " (" + user.toString() + ")"
    }

    @Override
    int compareTo(obj) {
        this.user.compareTo(obj.user)
    }

    def getBall() {
        return  score.getBall(game?.score)?: 0
    }
}
