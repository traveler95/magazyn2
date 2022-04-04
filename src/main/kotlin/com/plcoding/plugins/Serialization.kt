package com.plcoding.plugins

import io.ktor.features.*
import io.ktor.application.*
import io.ktor.gson.*


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson()
    }
}
