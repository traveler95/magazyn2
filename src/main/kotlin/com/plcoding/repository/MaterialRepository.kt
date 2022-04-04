package com.plcoding.repository

import com.plcoding.data.model.Log
import com.plcoding.data.model.LogDraft
import com.plcoding.data.model.Material
import com.plcoding.data.model.MaterialDraft

interface MaterialRepository {


    fun getAllMaterials(): List<Material>

    fun getAllLogs(): List<Log>

    fun getMaterial(id: Int): Material?

    fun addMaterial(draft: MaterialDraft): Material

    fun addLog(draft: LogDraft): Log

    fun removeMaterial(id: Int): Boolean

    fun updateMaterial(id: Int, draft: MaterialDraft): Boolean


}