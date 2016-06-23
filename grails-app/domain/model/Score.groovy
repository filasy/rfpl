package model

class Score implements Comparable <Score> {
    Integer firstTeam
    Integer secondTeam

    static constraints = {
        firstTeam  blank: false, nullable: false
        secondTeam  blank: false, nullable: false
    }

    static mapping = {
        sort firstTeam: "asc", secondTeam: "asc"
    }

    @Override
    String toString(){
        firstTeam + ":" + secondTeam
    }

    @Override
    int compareTo(Score obj) {
        int result = firstTeam.compareTo(obj.firstTeam)
        if (result != 0) {
            return result
        } else {
            return secondTeam.compareTo(obj.secondTeam)
        }
    }

    def getBall(Score score) {
        if (!score || !this) {
            return 0
        } else if (firstTeam == score.firstTeam && secondTeam == score.secondTeam) {
            return 3
        } else if ((firstTeam-secondTeam) == (score.firstTeam - score.secondTeam)){
            return 2
        } else if (((firstTeam-secondTeam) > 0) && ((score.firstTeam - score.secondTeam) > 0)
                || ((firstTeam-secondTeam) < 0) && ((score.firstTeam - score.secondTeam) < 0)){
            return 1
        }
        return 0
    }
}
