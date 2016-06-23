package model

class Team {
    String name
    boolean locked
    
    static constraints = {
        name blank: false, unique: true, nullable: false, maxSize:200
    }

    static mapping = {
        sort name: "asc"
    }

    @Override
    String toString(){
        name
    }
}
