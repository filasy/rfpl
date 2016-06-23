import model.Forecast
import model.Game
import model.Rank

import model.Team
import model.Score
import secure.Role
import secure.User
import secure.UserRole

class BootStrap {

    def init = { servletContext ->
        environments {
            production {
                createUserRole()
                createTeam()
                createRankAndRound()
                createScore()
                createGame()
                createForecast()
            }

            development {
                createUserRole()
                createTeam()
                createRankAndRound()
                createScore()
                createGame()
                createForecast()
            }

            test {}
        }
    }
    def destroy = {
    }

    void createUserRole(){
        def adminRole = new Role('ROLE_ADMIN').save(flush:true)
        def userRole = new Role('ROLE_USER').save(flush:true)

        def testUser = new User('admin', 'admin','Филатова').save(flush:true)
        UserRole.create(testUser, adminRole, true)

        testUser = new User('pirogov', '123', 'Евгений П').save(flush:true)
        UserRole.create(testUser, userRole, true)

        testUser = new User('aleksandr', '123', 'Александр').save(flush:true)
        UserRole.create(testUser, userRole, true)

        testUser = new User('evgeny', '123','Евгений К').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('mahail', '123', 'Михаил').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('ekaterina', '123', 'Екатерина').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('oleg', '123', 'Олег').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('ivan', '123', 'Иван').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('pavel', '123', 'Павел').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('sergey', '123', 'Сергей').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('daniil', '123', 'Даниил').save(flush:true)
        UserRole.create(testUser, userRole, true)
        testUser = new User('semen', '123', 'Семен').save(flush:true)
        UserRole.create(testUser, userRole, true)


        assert User.count() == 12
        assert Role.count() == 2
        assert UserRole.count() == 12
    }

    void createTeam(){
        def team = new Team(name: "Амкар").save(flush:true)
        team = new Team(name: "Анжи").save(flush:true)
        team = new Team(name: "Динамо").save(flush:true)
        team = new Team(name: "Зенит").save(flush:true)
        team = new Team(name: "Краснодар").save(flush:true)
        team = new Team(name: "Кубань").save(flush:true)
        team = new Team(name: "Локомотив").save(flush:true)
        team = new Team(name: "Мордовия").save(flush:true)
        team = new Team(name: "Ростов").save(flush:true)
        team = new Team(name: "Рубин").save(flush:true)
        team = new Team(name: "Спартак").save(flush:true)
        team = new Team(name: "Терек").save(flush:true)
        team = new Team(name: "Крылья Советов").save(flush:true)
        team = new Team(name: "Урал").save(flush:true)
        team = new Team(name: "Уфа").save(flush:true)
        team = new Team(name: "ЦСКА").save(flush:true)

        team = new Team(name: "Франция").save(flush:true)
        team = new Team(name: "Румыния").save(flush:true)
        team = new Team(name: "Албания").save(flush:true)
        team = new Team(name: "Швейцария").save(flush:true)
        team = new Team(name: "Уэльс").save(flush:true)
        team = new Team(name: "Словакия").save(flush:true)
        team = new Team(name: "Англия").save(flush:true)
        team = new Team(name: "Россия").save(flush:true)
        team = new Team(name: "Турция").save(flush:true)
        team = new Team(name: "Хорватия").save(flush:true)
        team = new Team(name: "Польша").save(flush:true)
        team = new Team(name: "Северная Ирландия").save(flush:true)
        team = new Team(name: "Германия").save(flush:true)
        team = new Team(name: "Украина").save(flush:true)
        team = new Team(name: "Австрия").save(flush:true)
        team = new Team(name: "Венгрия").save(flush:true)
        team = new Team(name: "Португалия").save(flush:true)
        team = new Team(name: "Исландия").save(flush:true)
        team = new Team(name: "Бельгия").save(flush:true)
        team = new Team(name: "Ирландия").save(flush:true)

        assert Team.count() == 36
    }


    void createRankAndRound(){
        def rank = new Rank(name: "РФПЛ 2015-2016")
                .addToUsers(User.findByUsername('pirogov'))
                .addToUsers(User.findByUsername('admin'))
                .addToUsers(User.findByUsername('aleksandr'))
                .save(flush:true)
        rank = new Rank(name: "Евро 2016")
                .addToUsers(User.findByUsername('admin'))
                .addToUsers(User.findByUsername('pirogov'))
                .addToUsers(User.findByUsername('evgeny'))
                .save(flush:true)
        rank = new Rank(name: "РФПЛ 2016-2017")
                .addToUsers(User.findByUsername('admin'))
                .addToUsers(User.findByUsername('pirogov'))
                .addToUsers(User.findByUsername('semen'))
                .addToUsers(User.findByUsername('daniil'))
                .addToUsers(User.findByUsername('sergey'))
                .addToUsers(User.findByUsername('pavel'))
                .addToUsers(User.findByUsername('ivan'))
                .addToUsers(User.findByUsername('oleg'))
                .addToUsers(User.findByUsername('ekaterina'))
                .addToUsers(User.findByUsername('mahail'))
                .addToUsers(User.findByUsername('aleksandr'))
                .addToUsers(User.findByUsername('evgeny'))
                .save(flush:true)
        assert Rank.count() == 3
    }

    void createScore(){
        def score = Score.findOrSaveByFirstTeamAndSecondTeam(0,0)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(0,1)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(0,2)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(1,1)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(1,2)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(1,3)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(2,0)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(2,1)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(2,2)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(3,0)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(3,1)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(3,2)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(3,3)
        score = Score.findOrSaveByFirstTeamAndSecondTeam(4,1)

        assert Score.count() == 14
    }

    void createGame(){
        def game = new Game(startDate: new Date(115,8,18,19,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Ростов"), secondTeam:  Team.findByName("Анжи"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,0)).save(flush:true)
        game = new Game(startDate: new Date(115,8,19,16,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Терек"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(4,1)).save(flush:true)
        //Ту10
        game = new Game(startDate: new Date(115,8,26,14,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("ЦСКА"), secondTeam:  Team.findByName("Локомотив"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save(flush:true)
        game = new Game(startDate: new Date(115,8,26,16,30), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Анжи"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save(flush:true)
        //Ту11
        game = new Game(startDate: new Date(115,9,2,17,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Урал"), secondTeam:  Team.findByName("Крылья Советов")).save(flush:true)
        game = new Game(startDate: new Date(115,9,3,14,30), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Мордовия"), secondTeam:  Team.findByName("Спартак")).save(flush:true)

        //Евро
        game = new Game(startDate: new Date(116,5,10,22,00), rank: Rank.findByName("Евро 2016"),
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
        game = new Game(startDate: new Date(116,5,25,16,00), rank: Rank.findByName("РФПЛ 2016-2017"),
                firstTeam:  Team.findByName("Бельгия"), secondTeam:  Team.findByName("Анжи")).save(flush:true)
        game = new Game(startDate: new Date().plus(6), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(5,2),
                firstTeam:  Team.findOrCreateByName("Исландия"), secondTeam:  Team.findOrCreateByName("Австрия")).save(flush:true)
        game = new Game(startDate: new Date().plus(5), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(9,2),
                firstTeam:  Team.findOrCreateByName("Россия"), secondTeam:  Team.findOrCreateByName("Уэльс")).save(flush:true)
        game = new Game(startDate: new Date().plus(4), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0),
                firstTeam:  Team.findOrCreateByName("Швейцария"), secondTeam:  Team.findOrCreateByName("Франция")).save(flush:true)
        game = new Game(startDate: new Date().plus(3), rank: Rank.findByName("Евро 2016"), score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2),
                firstTeam:  Team.findOrCreateByName("Словакия"), secondTeam:  Team.findOrCreateByName("Англия")).save(flush:true)

        assert Game.count() == 21
    }

    def createForecast(){
        //Тур10
        def forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Анжи'), Team.findByName("Уфа")),
                score: Score.findByFirstTeamAndSecondTeam(2,1)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('ЦСКА'), Team.findByName("Локомотив")),
                score: Score.findByFirstTeamAndSecondTeam(1,1)).save(flush:true)
        //Тур9
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Терек'), Team.findByName("Уфа")),
                score: Score.findByFirstTeamAndSecondTeam(2,0)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Ростов'), Team.findByName("Анжи")),
                score: Score.findByFirstTeamAndSecondTeam(2,1)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Бельгия'), Team.findByName("Анжи")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(9,9)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,0)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('mahail'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,10)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('evgeny'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,1)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('ekaterina'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,3)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('oleg'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,2)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('ivan'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,4)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('pavel'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(0,0)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('sergey'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,5)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('daniil'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(2,0)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('semen'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,0)).save(flush:true)
        forecast = new Forecast(user: User.findByUsername('aleksandr'),
                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(3,0)).save(flush:true)

        User.each {
            println it
//            forecast = new Forecast(user: it,
//                game: Game.findByFirstTeamAndSecondTeamAndRank(Team.findByName('Словакия'), Team.findByName("Англия"), Rank.findByName("Евро 2016")),
//                score: Score.findOrCreateByFirstTeamAndSecondTeam(1,0)).save(flush:true)
        }

//        assert Forecast.count() == 17
    }
}
