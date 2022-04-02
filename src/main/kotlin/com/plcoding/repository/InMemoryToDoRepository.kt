package com.plcoding.repository

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft

class InMemoryToDoRepository: ToDoRepository {


    private val todos = mutableListOf<ToDo>(
        ToDo(1,"dsadsadsadads", 11),
        ToDo(2,"fdsfsafsf", 12),
        ToDo(3,"dsfdsfsdf",13)
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
            ilosc = draft.ilosc
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
        todo.ilosc = draft.ilosc
        return true

    }


}