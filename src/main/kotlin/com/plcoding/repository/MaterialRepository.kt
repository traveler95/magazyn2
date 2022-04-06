package com.plcoding.repository

import com.plcoding.data.model.*

interface MaterialRepository {


    fun getAllMaterials(): List<Material>

    fun getAllLogs(): List<Log>

    fun getMaterial(id: Int): Material?

    fun addMaterial(draft: MaterialDraft): Material

    fun addLog(draft: LogDraft): LogDraft

    fun removeMaterial(id: Int): Boolean

    fun updateMaterial(id: Int, draft: MaterialLogDraft): Boolean

    fun releaseMaterial(id: Int, draft: MaterialLogReleaseDraft): Boolean

    fun materialDelivery(id: Int, draft: MaterialLogDeliveryDraft): Boolean


}