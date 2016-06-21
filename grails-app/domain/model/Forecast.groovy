package model

import secure.User

class Forecast implements Comparable{
    User user
    Score score
    Date dateCreated
    Date lastUpdated

    static belongsTo = [game: Game]

    static constraints = {
        user nullable: false, unique: ('game')
        game nullable: false, unique: ('user')
        score()
        dateCreated()
        lastUpdated()
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
