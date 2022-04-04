package com.plcoding.plugins

import com.plcoding.routes.randomRabbit
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {
    routing {
        get("/")  {
            call.respondText("Status: OK")
        }
        randomRabbit()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
