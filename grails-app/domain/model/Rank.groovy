package model

import secure.Gamer

class Rank implements Comparable{
    String name;
	Date dateCreated = new Date()
    Date lastUpdated
    boolean enabled = true
    SortedSet users

    static belongsTo  = [Gamer]
    static hasMany = [users: Gamer]

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