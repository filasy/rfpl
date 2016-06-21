package model

import secure.User

class Rank implements Comparable{
    String name;
	Date dateCreated = new Date()
    Date lastUpdated
    boolean enabled = true
    SortedSet users

    static belongsTo  = [User]
    static hasMany = [users: User]

    static constraints = {
        name blank: false, unique: true
        enabled nullable: false
        users()
        dateCreated()
        lastUpdated()
    }

    static mapping = {
        sort id: "desc"
    }

    @Override
    String toString(){        
		name?.toUpperCase()
    }
	
	@Override
	int compareTo(obj) {
        this.dateCreated.compareTo(obj.dateCreated)
    }
}