package secure

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import model.Forecast
import model.Rank

@EqualsAndHashCode(includes='username')
@ToString(includes='name', includeNames=true, includePackage=true)
class Gamer implements Serializable, Comparable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String name
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	SortedSet ranks
	static hasMany = [ranks: Rank]


	Gamer(String username, String password, String name) {
		this()
		this.username = username
		this.password = password
		this.name = name
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	boolean isAdmin(){
		return  UserRole.exists(this.id, Role.findByAuthority("ROLE_ADMIN").id)
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		name blank: false, unique: true
		password blank: false, password: true
	}

	static mapping = {
		password column: '`password`'
		sort name: "asc"
	}

	@Override
	String toString() {
		this.name;
	}

	@Override
	int compareTo(obj) {
		this.name.compareTo(obj.name)
	}

	int getBall(){
		def result = 0
		Forecast.findAllByUser(this).each {
			result += it.getBall()?: 0
		}
		return result
	}

	int getBallByRank(Rank rank) {
		def result = 0
		Forecast.findAllByUser(this).findAll {it.game.rank.id == rank.id}.each {
				result += it.getBall()?: 0
		}
		return result
	}
}
