package model

class Rank {
    String name;
    boolean enabled = true

    static constraints = {
        name blank: false, unique: true
        enabled nullable: false
    }

    @Override
    String toString(){
        name
    }
}