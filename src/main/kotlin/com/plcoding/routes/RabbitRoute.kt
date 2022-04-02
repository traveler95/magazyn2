package com.plcoding.routes

import com.plcoding.data.model.ToDo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

private const val BASE_URL = "http://192.168.0.2:8100"


fun Route.randomRabbit() {
    get("/randomrabbit") {
        call.respond(
            HttpStatusCode.OK,
        )
    }
}