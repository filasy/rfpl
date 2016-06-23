package secure

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import model.Forecast
import model.Game
import model.Rank

@EqualsAndHashCode(includes='username')
@ToString(includes='name', includeNames=true, includePackage=true)
class User implements Serializable, Comparable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String name
	boolean enabled = true
	boolean aexpired
	boolean alocked
	boolean pexpired
	
	SortedSet ranks
	static hasMany = [ranks: Rank]


	User(String username, String password, String name) {
		this()
		this.username = username
		this.password = password
		this.name = name
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	boolean isAdmin(){
		return  UserRole.findByUserAndRole(this, Role.findByAuthority("ROLE_ADMIN")) != null
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
		password blank: false, password: true
		username blank: false, unique: true
		name blank: false, unique: true
	}

	static mapping = {
//		password column: '`password`'
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
		Forecast.findAllByUser(this).each {
			if (it.game.rank.id == rank.id) {
				result += it.getBall()?: 0
			}
		}
		return result
	}
}
