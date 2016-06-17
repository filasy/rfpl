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
        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()

        def testUser = new User('admin', 'admin','Филатова').save()
        UserRole.create(testUser, adminRole, true)

        testUser = new User('pirogov', '123', 'Евгений П').save()
        UserRole.create(testUser, userRole, true)

        testUser = new User('aleksandr', '123', 'Александр').save()
        UserRole.create(testUser, userRole, true)

        testUser = new User('evgeny', '123','Евгений К').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('mahail', '123', 'Михаил').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('ekaterina', '123', 'Екатерина').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('oleg', '123', 'Олег').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('ivan', '123', 'Иван').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('pavel', '123', 'Павел').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('sergey', '123', 'Сергей').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('daniil', '123', 'Даниил').save()
        UserRole.create(testUser, userRole, true)
        testUser = new User('semen', '123', 'Семен').save()
        UserRole.create(testUser, userRole, true)


//        assert User.count() == 12
//        assert Role.count() == 12
//        assert UserRole.count() == 2
    }

    void createTeam(){
        def team = new Team(name: "Амкар").save()
        team = new Team(name: "Анжи").save()
        team = new Team(name: "Динамо").save()
        team = new Team(name: "Зенит").save()
        team = new Team(name: "Краснодар").save()
        team = new Team(name: "Кубань").save()
        team = new Team(name: "Локомотив").save()
        team = new Team(name: "Мордовия").save()
        team = new Team(name: "Ростов").save()
        team = new Team(name: "Рубин").save()
        team = new Team(name: "Спартак").save()
        team = new Team(name: "Терек").save()
        team = new Team(name: "Крылья Советов").save()
        team = new Team(name: "Урал").save()
        team = new Team(name: "Уфа").save()
        team = new Team(name: "ЦСКА").save()

        team = new Team(name: "Франция").save()
        team = new Team(name: "Румыния").save()
        team = new Team(name: "Албания").save()
        team = new Team(name: "Швейцария").save()
        team = new Team(name: "Уэльс").save()
        team = new Team(name: "Словакия").save()
        team = new Team(name: "Англия").save()
        team = new Team(name: "Россия").save()
        team = new Team(name: "Турция").save()
        team = new Team(name: "Хорватия").save()
        team = new Team(name: "Польша").save()
        team = new Team(name: "Северная Ирландия").save()
        team = new Team(name: "Германия").save()
        team = new Team(name: "Украина").save()
        team = new Team(name: "Австрия").save()
        team = new Team(name: "Венгрия").save()
        team = new Team(name: "Португалия").save()
        team = new Team(name: "Исландия").save()
        team = new Team(name: "Бельгия").save()
        team = new Team(name: "Ирландия").save()

//        assert Team.count() == 20
    }


    void createRankAndRound(){
        def rank = new Rank(name: "РФПЛ 2015-2016")
                .addToUsers(User.findByUsername('pirogov'))
                .addToUsers(User.findByUsername('admin'))
                .addToUsers(User.findByUsername('aleksandr'))
                .save()
        rank = new Rank(name: "Евро 2016")
                .addToUsers(User.findByUsername('admin'))
                .addToUsers(User.findByUsername('pirogov'))
                .addToUsers(User.findByUsername('evgeny'))
                .save()
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
                .save()
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
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,0)).save()
        game = new Game(startDate: new Date(115,8,19,16,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Терек"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(4,1)).save()
        //Ту10
        game = new Game(startDate: new Date(115,8,26,14,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("ЦСКА"), secondTeam:  Team.findByName("Локомотив"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        game = new Game(startDate: new Date(115,8,26,16,30), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Анжи"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        //Ту11
        game = new Game(startDate: new Date(115,9,2,17,0), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Урал"), secondTeam:  Team.findByName("Крылья Советов")).save()
        game = new Game(startDate: new Date(115,9,3,14,30), rank: Rank.findByName("РФПЛ 2015-2016"),
                firstTeam:  Team.findByName("Мордовия"), secondTeam:  Team.findByName("Спартак")).save()

        //Евро
        game = new Game(startDate: new Date(116,5,10,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Франция"), secondTeam:  Team.findByName("Румыния")).save()
        game = new Game(startDate: new Date(116,5,11,16,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Албания"), secondTeam:  Team.findByName("Швейцария")).save()
        game = new Game(startDate: new Date(116,5,11,19,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Уэльс"), secondTeam:  Team.findByName("Словакия")).save()
        game = new Game(startDate: new Date(116,5,11,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Англия"), secondTeam:  Team.findByName("Россия")).save()
        game = new Game(startDate: new Date(116,5,12,16,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Турция"), secondTeam:  Team.findByName("Хорватия")).save()
        game = new Game(startDate: new Date(116,5,12,19,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Польша"), secondTeam:  Team.findByName("Северная Ирландия")).save()
        game = new Game(startDate: new Date(116,5,12,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Германия"), secondTeam:  Team.findByName("Украина")).save()
        game = new Game(startDate: new Date(116,5,14,19,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Австрия"), secondTeam:  Team.findByName("Венгрия")).save()
        game = new Game(startDate: new Date(116,5,14,22,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Португалия"), secondTeam:  Team.findByName("Исландия")).save()
		game = new Game(startDate: new Date(116,5,18,16,00), rank: Rank.findByName("Евро 2016"),
                firstTeam:  Team.findByName("Бельгия"), secondTeam:  Team.findByName("Ирландия")).save()
        game = new Game(startDate: new Date(116,5,25,16,00), rank: Rank.findByName("РФПЛ 2016-2017"),
                firstTeam:  Team.findByName("Бельгия"), secondTeam:  Team.findByName("Анжи")).save()
//        assert Game.count() == 30
    }

    def createForecast(){
        //Тур10
        def forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Анжи'), Team.findByName("Уфа")),
                score: Score.findByFirstTeamAndSecondTeam(2,1)).save()
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('ЦСКА'), Team.findByName("Локомотив")),
                score: Score.findByFirstTeamAndSecondTeam(1,1)).save()
        //Тур9
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Терек'), Team.findByName("Уфа")),
                score: Score.findByFirstTeamAndSecondTeam(2,0)).save()
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Ростов'), Team.findByName("Анжи")),
                score: Score.findByFirstTeamAndSecondTeam(2,1)).save()
        forecast = new Forecast(user: User.findByUsername('pirogov'),
                game: Game.findByFirstTeamAndSecondTeam(Team.findByName('Бельгия'), Team.findByName("Анжи")),
                score: Score.findOrCreateByFirstTeamAndSecondTeam(9,9)).save()

        assert Forecast.count() == 5
    }
}
