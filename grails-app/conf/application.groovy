

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'secure.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'secure.UserRole'
grails.plugin.springsecurity.authority.className = 'secure.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/**',             access:  ['ROLE_ADMIN','ROLE_USER']],
	//[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/login/**', access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
	[pattern: '/logout/**', access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
	[pattern:  '/game/index', access: ['ROLE_ADMIN','ROLE_USER']],
	[pattern:  '/game/create/**', access: ['ROLE_ADMIN']],
	[pattern:  '/game/save/**', access: ['ROLE_ADMIN']],
	[pattern:  '/game/edit/**', access: ['ROLE_ADMIN']],
	[pattern:  '/game/update/**', access: ['ROLE_ADMIN']],
	[pattern:  '/game/delete/**', access: ['ROLE_ADMIN']],
	[pattern:  '/game/show/**', access: ['ROLE_ADMIN','ROLE_USER']],
	[pattern:  '/forecast/**', access: ['ROLE_ADMIN','ROLE_USER']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

