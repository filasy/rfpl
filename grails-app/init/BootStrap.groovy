import model.Forecast
import model.Game
import model.Rank

import model.Team
import model.Score
import secure.Gamer
import secure.Role
import secure.UserRole

class BootStrap {

    def init = { servletContext ->
        environments {
            production {
            }

            development {
                createUserRole()
                createTeam()
                createRankAndRound()
                createGame()
                createForecast()
            }

            test {}
        }
    }
    def destroy = {
    }

    void changeUsername(String newName, String oldName){
        def testUser = Gamer.findByUsername(oldName)
        testUser.username = newName
        testUser.save(flush:true)
    }

    void createUserRole(){
        def adminRole = new Role('ROLE_ADMIN').save(flush:true)
        def userRole = new Role('ROLE_USER').save(flush:true)

        def testUser = new Gamer('admin', 'sukanasos','Admin').save(flush:true)
        UserRole.create(testUser, adminRole, true)
        testUser = new Gamer('Pirogov563', '573', 'Евгений П').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Andrey131', '141', 'Андрей').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Evgeny522', '532','Евгений К').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Michael675', '685', 'Михаил').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Alexey709', '719', 'Алексей').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Ivan201', '211', 'Иван').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Alexander582', '592', 'Александр').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Pavel749', '759', 'Павел').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Sergey286', '296', 'Сергей').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Evgeny316', '936', 'Евгений Кр').save(flush:true)
        UserRole.create(testUser, userRole, true)
    }

    void createTeam(){
        def team = new Team(name: "Франция").save(flush:true)
        team = new Team(name: "Исландия").save(flush:true)
        team = new Team(name: "Украина").save(flush:true)
        team = new Team(name: "Швеция").save(flush:true)
        team = new Team(name: "Ирландия").save(flush:true)
        team = new Team(name: "Венгрия").save(flush:true)
        team = new Team(name: "Турция").save(flush:true)
        team = new Team(name: "Хорватия").save(flush:true)
        team = new Team(name: "Словакия").save(flush:true)
        team = new Team(name: "Россия").save(flush:true)
        team = new Team(name: "Польша").save(flush:true)
        team = new Team(name: "Германия").save(flush:true)
        team = new Team(name: "Албания").save(flush:true)
        team = new Team(name: "Румыния").save(flush:true)
        team = new Team(name: "Уэльс").save(flush:true)
        team = new Team(name: "Бельгия").save(flush:true)
        team = new Team(name: "Италия").save(flush:true)
        team = new Team(name: "Швейцария").save(flush:true)
        team = new Team(name: "Испания").save(flush:true)
        team = new Team(name: "Португалия").save(flush:true)
        team = new Team(name: "Северная Ирландия").save(flush:true)
        team = new Team(name: "Австрия").save(flush:true)
    }


    void createRankAndRound(){
       def rank = new Rank(name: "Евро 2016")
                .addToUsers(Gamer.findByUsername('admin'))
                .addToUsers(Gamer.findByUsername('Pirogov563'))
                .addToUsers(Gamer.findByUsername('Daniil926'))
                .addToUsers(Gamer.findByUsername('Sergey286'))
                .addToUsers(Gamer.findByUsername('Pavel749'))
                .addToUsers(Gamer.findByUsername('Alexander582'))
                .addToUsers(Gamer.findByUsername('Ivan201'))
                .addToUsers(Gamer.findByUsername('Alexey709'))
                .addToUsers(Gamer.findByUsername('Michael675'))
                .addToUsers(Gamer.findByUsername('Evgeny522'))
                .addToUsers(Gamer.findByUsername('Andrey131'))
                .save(flush:true)
    }

    void createGame(){
        def game = new Game(startDate: new Date(116,5,10,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Франция"), secondTeam:  Team.findByName("Румыния")).save(flush:true)
        game = new Game(startDate: new Date(116,5,11,16,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Албания"), secondTeam:  Team.findByName("Швейцария")).save(flush:true)
        game = new Game(startDate: new Date(116,5,11,19,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Уэльс"), secondTeam:  Team.findByName("Словакия")).save(flush:true)
        game = new Game(startDate: new Date(116,5,11,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Англия"), secondTeam:  Team.findByName("Россия")).save(flush:true)
        game = new Game(startDate: new Date(116,5,12,16,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Турция"), secondTeam:  Team.findByName("Хорватия")).save(flush:true)
        game = new Game(startDate: new Date(116,5,12,19,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Польша"), secondTeam:  Team.findByName("Северная Ирландия")).save(flush:true)
        game = new Game(startDate: new Date(116,5,12,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Германия"), secondTeam:  Team.findByName("Украина")).save(flush:true)
        game = new Game(startDate: new Date(116,5,14,19,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Австрия"), secondTeam:  Team.findByName("Венгрия")).save(flush:true)
        game = new Game(startDate: new Date(116,5,14,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Португалия"), secondTeam:  Team.findByName("Исландия")).save(flush:true)
		game = new Game(startDate: new Date(116,5,18,16,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Бельгия"), secondTeam:  Team.findByName("Ирландия")).save(flush:true)
        game = new Game(startDate: new Date().plus(6), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(5,2),
                firstTeam:  Team.findOrCreateByName("Исландия"), secondTeam:  Team.findOrCreateByName("Австрия")).save(flush:true)
        game = new Game(startDate: new Date().plus(5), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(9,2),
                firstTeam:  Team.findOrCreateByName("Россия"), secondTeam:  Team.findOrCreateByName("Уэльс")).save(flush:true)
        game = new Game(startDate: new Date().plus(4), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0),
                firstTeam:  Team.findOrCreateByName("Швейцария"), secondTeam:  Team.findOrCreateByName("Франция")).save(flush:true)
        game = new Game(startDate: new Date().plus(3), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2),
                firstTeam:  Team.findOrCreateByName("Словакия"), secondTeam:  Team.findOrCreateByName("Англия")).save(flush:true)
    }

    def createForecast(){
        def forecast = new Forecast(user: Gamer.findByUsername('Pirogov563'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Andrey131'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(2,3)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Evgeny522'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,3)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Michael675'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Alexey709'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Ivan201'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,4)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Pavel749'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Alexander582'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,5)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Daniil926'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(2,0)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Sergey286'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,0)).save(flush:true)
        forecast = new Forecast(user: Gamer.findByUsername('Pirogov563'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(3,0)).save(flush:true)
    }
}
