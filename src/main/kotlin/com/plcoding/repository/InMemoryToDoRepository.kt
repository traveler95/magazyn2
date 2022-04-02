package com.plcoding.repository

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft
import io.ktor.sessions.*

class InMemoryToDoRepository: ToDoRepository {


    private val todos = mutableListOf<ToDo>(
        ToDo(1,"dsadsadsadads", true),
        ToDo(2,"fdsfsafsf", false),
        ToDo(3,"dsfdsfsdf",false)
    )


    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getToDo(id: Int): ToDo? {
        return todos.firstOrNull { it.id == id }
    }

    override fun addToDo(draft: ToDoDraft): ToDo {

        val todo = ToDo(
            id = todos.size +1 ,
            title = draft.title,
            done = draft.done
        )
        todos.add(todo)
        return todo

    }

    override fun removeToDo(id: Int): Boolean {
        return todos.removeIf{ it.id ==id}
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        val todo = todos.firstOrNull{ it.id == id}
            ?: return false


        todo.title = draft.title
        todo.done = draft.done
        return true

    }


}