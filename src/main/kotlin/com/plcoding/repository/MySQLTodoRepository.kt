package com.plcoding.repository

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft
import com.plcoding.database.DatabaseManager

class MySQLTodoRepository: ToDoRepository {

    private val database = DatabaseManager()


    override fun getAllToDos(): List<ToDo> {
        return database.getAllTodos().map { ToDo(it.id, it.title, it.ilosc)}
    }

    override fun getToDo(id: Int): ToDo? {
        return database.getTodo(id)
            ?.let { ToDo(it.id, it.title, it.ilosc) }
    }

    override fun addToDo(draft: ToDoDraft): ToDo {

        return database.addTodo(draft)
    }

    override fun removeToDo(id: Int): Boolean {

        return database.removeTodo(id)
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        return database.updateTodo(id,draft)

    }
}