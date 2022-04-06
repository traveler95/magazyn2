package com.plcoding.database

import com.plcoding.data.model.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    private val hostname = "mysql37.mydevil.net"
    private val databaseName = "m1521_magazyn"
    private val username = "m1521_etraveler"
    private val password = "Zaq!2wsx"
    private val ktormDatabase: Database



    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false&serverTimezone=UTC"
        ktormDatabase = Database.connect ( jdbcUrl )
    }




    fun getAllMaterials(): List<DBMaterialEntity> {
        return ktormDatabase.sequenceOf(DBMaterialTable).toList()
    }

    fun getAllLogs(): List<DBLogsEntity> {
        return ktormDatabase.sequenceOf(DBLogsTable).toList()
    }




    fun getMaterial(id: Int): DBMaterialEntity? {
        return ktormDatabase.sequenceOf(DBMaterialTable).firstOrNull { it.id eq id }
    }

    fun addMaterial(draft: MaterialDraft): Material{
        val insertedId = ktormDatabase.insertAndGenerateKey(DBMaterialTable){
            set(DBMaterialTable.name, draft.name)
            set(DBMaterialTable.qty, draft.qty)
            set(DBMaterialTable.sn, draft.sn)
        }as Int
        return Material(insertedId, draft.name, draft.qty, draft.sn)
    }

    fun addLog(draft: LogDraft): LogDraft {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBLogsTable){
           // set(DBLogsTable.date, draft.date)
            set(DBLogsTable.materialid, draft.materialid)
            set(DBLogsTable.userid, draft.userid)
            set(DBLogsTable.contractorid, draft.contractorid)
            set(DBLogsTable.type, draft.type)
        }as Int
        return LogDraft(draft.materialid, draft.userid, draft.contractorid,draft.type)
    }

    fun updateMaterial(id: Int, draft: MaterialLogDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBMaterialTable){
            set(DBMaterialTable.name, draft.name)
            set(DBMaterialTable.qty, draft.qty)
            where {
                it.id eq id
            }
        }
        updateLog(draft.userid,draft.materialid,draft.contractorid,draft.type,draft.qty)
        return updatedRows>0
    }


    fun materialDelivery(id: Int, draft: MaterialLogDeliveryDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBMaterialTable){
            set(DBMaterialTable.qty, DBMaterialTable.qty+draft.qty)
            where {
                it.id eq id
            }
        }
        updateLogOnDelivery(id, draft.userid, draft.type, draft.qty)
        return updatedRows>0
    }

    fun releaseMaterial(id: Int, draft: MaterialLogReleaseDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBMaterialTable){
            set(DBMaterialTable.qty, DBMaterialTable.qty-draft.qty)
            where {
                it.id eq id
            }
        }
         updateLog(id, draft.userid, draft.contractorid,draft.type, draft.qty)
        return updatedRows>0
    }


    fun updateLogOnDelivery(a: Int, b: Int ,c: String,d: Int){
        ktormDatabase.insertAndGenerateKey(DBLogsTable){
            set(DBLogsTable.materialid, a)
            set(DBLogsTable.userid, b)
            set(DBLogsTable.type, c)
            set(DBLogsTable.qty, d)
        }
    }

    fun updateLog(a: Int, b: Int ,c: Int,d: String, e: Int){
    ktormDatabase.insertAndGenerateKey(DBLogsTable){
        set(DBLogsTable.materialid, a)
        set(DBLogsTable.userid, b)
        set(DBLogsTable.contractorid, c)
        set(DBLogsTable.type, d)
        set(DBLogsTable.qty, e)
        }
    }

    fun removeMaterial(id: Int): Boolean{
        val deletedRows=ktormDatabase.delete(DBMaterialTable){
            it.id eq id
        }
        return deletedRows>0
    }
}