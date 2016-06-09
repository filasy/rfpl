package model

class Team {
    String name

    static constraints = {
        name blank: false, unique: true, nullable: false, maxSize:200
    }

    @Override
    String toString(){
        name
    }
}
