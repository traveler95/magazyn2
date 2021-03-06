package com.plcoding.repository

import com.plcoding.data.model.*
import com.plcoding.database.DatabaseManager

class MySQLMaterialRepository: MaterialRepository {

    private val database = DatabaseManager()


    override fun getAllMaterials(): List<Material> {
        return database.getAllMaterials().map { Material(it.id, it.name, it.qty, it.sn)}
    }

    override fun getAllLogs(): List<Log> {
        return database.getAllLogs().map { Log(it.id, it.date, it.materialid, it.userid, it.contractorid, it.type, it.qty)}
    }

    override fun getMaterial(id: Int): Material? {
        return database.getMaterial(id)
            ?.let { Material(it.id, it.name, it.qty, it.sn) }
    }

    override fun addMaterial(draft: MaterialDraft): Material {

        return database.addMaterial(draft)
    }

    override fun addLog(draft: LogDraft): LogDraft {

        return database.addLog(draft)
    }

    override fun removeMaterial(id: Int): Boolean {

        return database.removeMaterial(id)
    }


    override fun updateMaterial(id: Int, draft: MaterialLogDraft): Boolean {
        return database.updateMaterial(id,draft)
    }

    override fun releaseMaterial(id: Int, draft: MaterialLogReleaseDraft): Boolean {
        return database.releaseMaterial(id,draft)
    }

    override fun materialDelivery(id: Int, draft: MaterialLogDeliveryDraft): Boolean {
        return database.materialDelivery(id,draft)
    }
}