package com.plcoding.repository

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft

interface ToDoRepository {


    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

    fun addToDo(draft: ToDoDraft): ToDo

    fun removeToDo(id: Int): Boolean

    fun updateToDo(id: Int, draft: ToDoDraft ): Boolean


}