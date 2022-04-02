package com.plcoding

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft
import io.ktor.application.*
import com.plcoding.plugins.*
import com.plcoding.repository.InMemoryToDoRepository
import com.plcoding.repository.MySQLTodoRepository
import com.plcoding.repository.ToDoRepository
import com.typesafe.config.ConfigException.Null
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

import io.ktor.serialization.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    // configureSerialization()
    //configureMonitoring()

    install(CallLogging)
    install(ContentNegotiation) {
        gson {

        }
    }

    routing {


        val repository: ToDoRepository = MySQLTodoRepository()


        get("/") {
            call.respondText { "kk" }
        }
        get("/todos") {
            call.respond(repository.getAllToDos())
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "id parametr zly")
                return@get
            }

            val todo = repository.getToDo(id)
            if (todo == null) {
                call.respond(HttpStatusCode.NotFound, "not found todo")
            } else {
                call.respond(todo)
            }
            call.respondText {

                "Todolist Details fot todo item#$id"
            }
        }

        post("/todos") {

            val toDoDraft = call.receive<ToDoDraft>()
        val todo = repository.addToDo(toDoDraft)
        call.respond(todo )
        }

        put("/todos/{id}") {
            val toDoDraft = call.receive<ToDoDraft>()
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null){
                call.respond(HttpStatusCode.BadRequest,"kokoko")
                return@put
            }
            val updated = repository.updateToDo(todoId, toDoDraft)
            if (updated) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound,"not found")
            }
        }

        delete("/todos/{id}") {
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null){
                call.respond(HttpStatusCode.BadRequest,"kokoko")
                return@delete
            }
            val removed = repository.removeToDo(todoId)
            if (removed) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }

        }

    }

}
