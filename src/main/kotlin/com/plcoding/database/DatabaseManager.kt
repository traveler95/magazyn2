package com.plcoding.database

import com.plcoding.data.model.*
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
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
        }as Int
        return Material(insertedId, draft.name, draft.qty)
    }

    fun addLog(draft: LogDraft): Log {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBLogsTable){
           // set(DBLogsTable.date, draft.date)
            set(DBLogsTable.materialid, draft.materialid)
            set(DBLogsTable.userid, draft.userid)
            set(DBLogsTable.contractorid, draft.contractorid)
        }as Int
        return Log(insertedId, draft.materialid, draft.userid, draft.contractorid)
    }

    fun updateMaterial(id: Int, draft: MaterialLogDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBMaterialTable){
            set(DBMaterialTable.name, draft.name)
            set(DBMaterialTable.qty, draft.qty)
            where {
                it.id eq id
            }
        }
        updateLog(draft.userid,draft.materialid,draft.contractorid)
        return updatedRows>0
    }

fun updateLog(a: Int, b: Int ,c: Int){
    ktormDatabase.insertAndGenerateKey(DBLogsTable){
        set(DBLogsTable.materialid, a)
        set(DBLogsTable.userid, b)
        set(DBLogsTable.contractorid, c)
    }
}

    fun removeMaterial(id: Int): Boolean{
        val deletedRows=ktormDatabase.delete(DBMaterialTable){
            it.id eq id
        }
        return deletedRows>0
    }
}