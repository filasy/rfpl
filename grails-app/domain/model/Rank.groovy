package model

import secure.User

class Rank implements Comparable{
    String name;
	Date dateCreated = new Date()
    Date lastUpdated
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
	
	//@Override
	int compareTo(obj) {
        this.name.compareTo(obj.name)
    }
}