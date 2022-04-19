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
		createUserRole()
                createTeam()
                createRankAndRound()
                createGame()
                createForecast()
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

    void changeUserInfo(String oldName, String newName, String newPass){
        def testUser = Gamer.findByUsername(oldName)
        testUser.username = newName
        if (newPass) {
            testUser.password = newPass
        }
        testUser.save(flush:true)
    }

    void createUserRole(){
        def adminRole = new Role('ROLE_ADMIN').save(flush:true)
        def userRole = new Role('ROLE_USER').save(flush:true)

        def testUser = new Gamer('admin', 'admin','Admin').save(flush:true)
        UserRole.create(testUser, adminRole, true)
        testUser = new Gamer('EP', '123', 'EP').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Alexandr', '123', 'Alexandr').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Bond', '123','Bond').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Dron', '123', 'Dron').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Dima', '123', 'Dima').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Ivan', '123', 'Ivan').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('ML', '123', 'ML').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Oleg', '123', 'Oleg').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('OlegT', '123', 'OlegT').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new Gamer('Pavel', '123', 'Pavel').save(flush:true)
        UserRole.create(testUser, userRole, true)
    }

    void createTeam(){
        def team = new Team(name: "Урал").save(flush:true)
        team = new Team(name: "Уфа").save(flush:true)
        team = new Team(name: "Локомотив М").save(flush:true)
        team = new Team(name: "Нижний-Новгород").save(flush:true)
        team = new Team(name: "Сочи").save(flush:true)
        team = new Team(name: "Ахмат").save(flush:true)
        team = new Team(name: "Рубин").save(flush:true)
        team = new Team(name: "Арсенал").save(flush:true)
        team = new Team(name: "Химки").save(flush:true)
        team = new Team(name: "Крылья Советов").save(flush:true)
        team = new Team(name: "Ростов").save(flush:true)
        team = new Team(name: "Спартак М").save(flush:true)
        team = new Team(name: "ЦСКА").save(flush:true)
        team = new Team(name: "Динамо М").save(flush:true)
        team = new Team(name: "Краснодар").save(flush:true)
        team = new Team(name: "Зенит").save(flush:true)
        //team = new Team(name: "Италия").save(flush:true)
        //team = new Team(name: "Швейцария").save(flush:true)
        //team = new Team(name: "Испания").save(flush:true)
        //team = new Team(name: "Португалия").save(flush:true)
        //team = new Team(name: "Северная Ирландия").save(flush:true)
        //team = new Team(name: "Австрия").save(flush:true)
    }


    void createRankAndRound(){
       def rank = new Rank(name: "РПЛ 2021/22")
                .addToUsers(Gamer.findByUsername('admin'))
                .addToUsers(Gamer.findByUsername('EP'))
                .addToUsers(Gamer.findByUsername('ML'))
                .addToUsers(Gamer.findByUsername('Oleg'))
                .addToUsers(Gamer.findByUsername('OlegT'))
                .addToUsers(Gamer.findByUsername('Ivan'))
                .addToUsers(Gamer.findByUsername('Dron'))
                .addToUsers(Gamer.findByUsername('Bond'))
                .addToUsers(Gamer.findByUsername('Dima'))
                .addToUsers(Gamer.findByUsername('Pavel'))
                .addToUsers(Gamer.findByUsername('Aleksandr'))
                .save(flush:true)
    }

    void createGame(){
        def game = new Game(startDate: new Date(122,24,4,14,00), rank: Rank.findByName("РПЛ 2021/22"),
                firstTeam:  Team.findByName("Урал"), secondTeam:  Team.findByName("Уфа")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,11,16,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Албания"), secondTeam:  Team.findByName("Швейцария")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,11,19,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Уэльс"), secondTeam:  Team.findByName("Словакия")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,11,22,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Англия"), secondTeam:  Team.findByName("Россия")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,12,16,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Турция"), secondTeam:  Team.findByName("Хорватия")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,12,19,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Польша"), secondTeam:  Team.findByName("Северная Ирландия")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,12,22,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Германия"), secondTeam:  Team.findByName("Украина")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,14,19,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Австрия"), secondTeam:  Team.findByName("Венгрия")).save(flush:true)
        //game = new Game(startDate: new Date(116,5,14,22,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Португалия"), secondTeam:  Team.findByName("Исландия")).save(flush:true)
	//	game = new Game(startDate: new Date(116,5,18,16,00), rank: Rank.findByName("Евро 2016"),
        //        firstTeam:  Team.findByName("Бельгия"), secondTeam:  Team.findByName("Ирландия")).save(flush:true)
        //game = new Game(startDate: new Date().plus(6), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(5,2),
        //        firstTeam:  Team.findOrCreateByName("Исландия"), secondTeam:  Team.findOrCreateByName("Австрия")).save(flush:true)
        //game = new Game(startDate: new Date().plus(5), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(9,2),
        //        firstTeam:  Team.findOrCreateByName("Россия"), secondTeam:  Team.findOrCreateByName("Уэльс")).save(flush:true)
        //game = new Game(startDate: new Date().plus(4), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0),
        //        firstTeam:  Team.findOrCreateByName("Швейцария"), secondTeam:  Team.findOrCreateByName("Франция")).save(flush:true)
        //game = new Game(startDate: new Date().plus(3), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2),
        //        firstTeam:  Team.findOrCreateByName("Словакия"), secondTeam:  Team.findOrCreateByName("Англия")).save(flush:true)
    }

    def createForecast(){
        //def forecast = new Forecast(user: Gamer.findByUsername('Pirogov12'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Andrey131'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //       score: Score.findOrCreateByFirstTeamAndSecondTeam(2,3)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Evgeny522'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(1,3)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Michael675'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Alexey709'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Ivan201'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(1,4)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Pavel749'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Alexander582'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(1,5)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Evgeny316'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(2,0)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Sergey286'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(1,0)).save(flush:true)
        //forecast = new Forecast(user: Gamer.findByUsername('Pirogov12'),
        //        game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
        //        score: Score.findOrCreateByFirstTeamAndSecondTeam(3,0)).save(flush:true)
    }
}
