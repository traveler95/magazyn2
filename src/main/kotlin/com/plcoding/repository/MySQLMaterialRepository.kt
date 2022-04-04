package com.plcoding.repository

import com.plcoding.data.model.Log
import com.plcoding.data.model.LogDraft
import com.plcoding.data.model.Material
import com.plcoding.data.model.MaterialDraft
import com.plcoding.database.DatabaseManager

class MySQLMaterialRepository: MaterialRepository {

    private val database = DatabaseManager()


    override fun getAllMaterials(): List<Material> {
        return database.getAllMaterials().map { Material(it.id, it.name, it.qty)}
    }

    override fun getAllLogs(): List<Log> {
        return database.getAllLogs().map { Log(it.id, it.materialid, it.userid, it.contractorid)}
    }

    override fun getMaterial(id: Int): Material? {
        return database.getMaterial(id)
            ?.let { Material(it.id, it.name, it.qty) }
    }

    override fun addMaterial(draft: MaterialDraft): Material {

        return database.addMaterial(draft)
    }

    override fun addLog(draft: LogDraft): Log {

        return database.addLog(draft)
    }

    override fun removeMaterial(id: Int): Boolean {

        return database.removeMaterial(id)
    }


    override fun updateMaterial(id: Int, draft: MaterialDraft): Boolean {
        return database.updateMaterial(id,draft)

    }
}