package com.plcoding.repository

import com.plcoding.data.model.Log
import com.plcoding.data.model.LogDraft
import com.plcoding.data.model.Material
import com.plcoding.data.model.MaterialDraft

class InMemoryMaterialRepository: MaterialRepository {


    private val todos = mutableListOf<Material>(
        Material(1,"dsadsadsadads", 11),
        Material(2,"fdsfsafsf", 12),
        Material(3,"dsfdsfsdf",13)
    )


    override fun getAllMaterials(): List<Material> {
        return todos
    }

    override fun getAllLogs(): List<Log> {
        TODO("Not yet implemented")
    }

    override fun getMaterial(id: Int): Material? {
        return todos.firstOrNull { it.id == id }
    }

    override fun addMaterial(draft: MaterialDraft): Material {

        val todo = Material(
            id = todos.size +1 ,
            name = draft.name,
            qty = draft.qty
        )
        todos.add(todo)
        return todo

    }

    override fun addLog(draft: LogDraft): Log {
        TODO("Not yet implemented")
    }

    override fun removeMaterial(id: Int): Boolean {
        return todos.removeIf{ it.id ==id}
    }

    override fun updateMaterial(id: Int, draft: MaterialDraft): Boolean {
        val todo = todos.firstOrNull{ it.id == id}
            ?: return false


        todo.name = draft.name
        todo.qty = draft.qty
        return true

    }


}