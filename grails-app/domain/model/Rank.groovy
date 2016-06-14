package model

import secure.User

class Rank {
    String name;
    boolean enabled = true

    static belongsTo  = User
    static hasMany = [users: User]

    static constraints = {
        name blank: false, unique: true
        enabled nullable: false
    }

    static mapping = {
        sort id: "desc"
    }

    @Override
    String toString(){        
		name
    }
}