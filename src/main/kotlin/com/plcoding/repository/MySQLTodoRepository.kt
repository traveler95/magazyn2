package com.plcoding.repository

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft
import com.plcoding.database.DatabaseManager

class MySQLTodoRepository: ToDoRepository {

    private val database = DatabaseManager()


    override fun getAllToDos(): List<ToDo> {
        return database.getAllTodos().map { ToDo(it.id, it.title, it.done)}
    }

    override fun getToDo(id: Int): ToDo? {
        TODO("Not yet implemented")
    }

    override fun addToDo(draft: ToDoDraft): ToDo {
        TODO("Not yet implemented")
    }

    override fun removeToDo(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        TODO("Not yet implemented")
    }
}