package rfpl

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/"(controller: "Game")
//        "/showResults"(controller: "Game", action: "showResults")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
