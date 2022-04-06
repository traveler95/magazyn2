package com.plcoding

import com.plcoding.data.model.*
import io.ktor.application.*
import com.plcoding.repository.MySQLMaterialRepository
import com.plcoding.repository.MaterialRepository
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    //configureRouting()
    //configureSerialization()
    //configureMonitoring()
    install(DefaultHeaders)
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Post)
        method(HttpMethod.Get)
        method(HttpMethod.Put)
        allowNonSimpleContentTypes = true // <-
        anyHost() //
        exposeHeader("Accept")
        exposeHeader("Content-Type")
    }
    install(CallLogging)
    install(DoubleReceive){
        receiveEntireContent

    }
    install(ContentNegotiation) {
        gson {
        }
    }

    routing {


        val repository: MaterialRepository = MySQLMaterialRepository()


        get("/") {
            call.respondText { "Status: OK" }
        }

        get("/materials") {
            call.respond(repository.getAllMaterials())
        }

        get("/logs") {
            call.respond(repository.getAllLogs())
        }

        get("/material/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "id parametr zly")
                return@get
            }

            val todo = repository.getMaterial(id)
            if (todo == null) {
                call.respond(HttpStatusCode.NotFound, "Nie znaleziono materiału o takim ID")
            } else {
                call.respond(todo)
            }
            call.respondText {

                "Todolist Details fot todo item#$id"
            }
        }

        post("/material") {
            val materialDraft = call.receive<MaterialDraft>()
            val todo = repository.addMaterial(materialDraft)
            call.response.header("Accept", "application/json")
            call.response.header("Content-Type", "application/json")
            call.respond(todo)
        }

        post("/log") {
            val logDraft = call.receive<LogDraft>()
            val log = repository.addLog(logDraft)
            call.response.header("Accept", "application/json")
            call.respond(log)
        }

        put("/material/{id}") {
            val materialDraft = call.receive<MaterialLogDraft>()
            val materialId = call.parameters["id"]?.toIntOrNull()

            if (materialId == null) {
                call.respond(HttpStatusCode.BadRequest, "kokoko")
                return@put
            }
            val updated = repository.updateMaterial(materialId, materialDraft)
            if (updated) {
                call.response.header("Accept", "application/json")
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "not found")
            }
        }



        put("/dostawa/{id}") {
            val materialDraft = call.receive<MaterialLogDeliveryDraft>()
            val materialId = call.parameters["id"]?.toIntOrNull()

            if (materialId == null) {
                call.respond(HttpStatusCode.BadRequest, "kokoko")
                return@put
            }
            val updated = repository.materialDelivery(materialId, materialDraft)
            if (updated) {
                call.response.header("Accept", "application/json")
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "not found")
            }
        }


        put("/wydanie/{id}") {
            val materialDraft = call.receive<MaterialLogReleaseDraft>()
            val materialId = call.parameters["id"]?.toIntOrNull()

            if (materialId == null) {
                call.respond(HttpStatusCode.BadRequest, "kokoko")
                return@put
            }
            val updated = repository.releaseMaterial(materialId, materialDraft)
            if (updated) {
                call.response.header("Accept", "application/json")
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound, "not found")
            }
        }



        delete("/material/{id}") {
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null) {
                call.respond(HttpStatusCode.BadRequest, "kokoko")
                return@delete
            }
            val removed = repository.removeMaterial(todoId)
            if (removed) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
