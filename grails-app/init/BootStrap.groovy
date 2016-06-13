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

        def testUser = new User('admin', 'admin','Филатова')
        testUser.activityChamp=false
        testUser.save()
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

        assert Team.count() == 16
    }


    void createRankAndRound(){
        def rank = new Rank(name: "РФПЛ 2015-2016").save()
        rank = new Rank(name: "Евро 2016").save()
        assert Rank.count() == 2
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
        //Тур1
        def game = new Game(startDate: new Date(2015,7,17,19,0),
                firstTeam:  Team.findByName("Спартак"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(2,2)).save()
        game = new Game(startDate: new Date(2015,7,18,18,30),
                firstTeam:  Team.findByName("ЦСКА"), secondTeam:  Team.findByName("Рубин"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,0)).save()
        game = new Game(startDate: new Date(2015,7,18,21,0),
                firstTeam:  Team.findByName("Ростов"), secondTeam:  Team.findByName("Терек"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        game = new Game(startDate: new Date(2015,7,19,13,30),
                firstTeam:  Team.findByName("Зенит"), secondTeam:  Team.findByName("Динамо"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(2,1)).save()
        game = new Game(startDate: new Date(2015,7,19,18,0),
                firstTeam:  Team.findByName("Мордовия"), secondTeam:  Team.findByName("Локомотив"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(0,1)).save()
        game = new Game(startDate: new Date(2015,7,19,21,0),
                firstTeam:  Team.findByName("Анжи"), secondTeam:  Team.findByName("Крылья Советов"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(0,1)).save()
        game = new Game(startDate: new Date(2015,7,20,17,0),
                firstTeam:  Team.findByName("Амкар"), secondTeam:  Team.findByName("Краснодар"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(0,1)).save()
        game = new Game(startDate: new Date(2015,7,20,20,0),
                firstTeam:  Team.findByName("Кубань"), secondTeam:  Team.findByName("Урал"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(0,2)).save()

        //Тур2
        game = new Game(startDate: new Date(2015,7,24,19,0),
                firstTeam:  Team.findByName("Крылья Советов"), secondTeam:  Team.findByName("ЦСКА"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(0,2)).save()
        //Тур3
        game = new Game(startDate: new Date(2015,7,31,20,0),
                firstTeam:  Team.findByName("Кубань"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        //Тур4
        game = new Game(startDate: new Date(2015,8,7,18,0),
                firstTeam:  Team.findByName("Терек"), secondTeam:  Team.findByName("Мордовия"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(0,0)).save()
        //Тур5
        game = new Game(startDate: new Date(2015,8,14,17,0),
                firstTeam:  Team.findByName("Амкар"), secondTeam:  Team.findByName("Анжи"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        //Тур6
        game = new Game(startDate: new Date(2015,8,21,20,0),
                firstTeam:  Team.findByName("Терек"), secondTeam:  Team.findByName("Динамо"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        //Тур7
        game = new Game(startDate: new Date(2015,8,28,17,0),
                firstTeam:  Team.findByName("Урал"), secondTeam:  Team.findByName("Терек"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(3,3)).save()
        //Тур8
        game = new Game(startDate: new Date(2015,9,12,17,0),
                firstTeam:  Team.findByName("Амкар"), secondTeam:  Team.findByName("Кубань"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        //Тур9
        game = new Game(startDate: new Date(2015,9,18,19,0),
                firstTeam:  Team.findByName("Ростов"), secondTeam:  Team.findByName("Анжи"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,0)).save()
        game = new Game(startDate: new Date(2015,9,19,16,0),
                firstTeam:  Team.findByName("Терек"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(4,1)).save()
        //Ту10
        game = new Game(startDate: new Date(2015,9,26,14,0),
                firstTeam:  Team.findByName("ЦСКА"), secondTeam:  Team.findByName("Локомотив"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        game = new Game(startDate: new Date(2015,9,26,16,30),
                firstTeam:  Team.findByName("Анжи"), secondTeam:  Team.findByName("Уфа"),
                score: Score.findOrSaveByFirstTeamAndSecondTeam(1,1)).save()
        //Ту11
        game = new Game(startDate: new Date(2015,10,2,17,0),
                firstTeam:  Team.findByName("Урал"), secondTeam:  Team.findByName("Крылья Советов")).save()
        game = new Game(startDate: new Date(2015,10,3,14,30),
                firstTeam:  Team.findByName("Мордовия"), secondTeam:  Team.findByName("Спартак")).save()
        assert Game.count() == 21
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

        assert Forecast.count() == 4
    }
}
