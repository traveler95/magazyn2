package com.plcoding.repository

import com.plcoding.data.model.*

interface MaterialRepository {


    fun getAllMaterials(): List<Material>

    fun getAllLogs(): List<Log>

    fun getMaterial(id: Int): Material?

    fun addMaterial(draft: MaterialDraft): Material

    fun addLog(draft: LogDraft): Log

    fun removeMaterial(id: Int): Boolean

    fun updateMaterial(id: Int, draft: MaterialLogDraft): Boolean


}