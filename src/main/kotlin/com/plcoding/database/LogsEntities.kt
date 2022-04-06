package com.plcoding.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBLogsTable: Table<DBLogsEntity>("logs"){

    val id = int("id").primaryKey().bindTo {  it.id}
    val date = varchar("date").bindTo { it.date }
    val materialid = int("materialid").bindTo { it.materialid }
    val userid = int("userid").bindTo { it.userid }
    val contractorid = int("contractorid").bindTo { it.contractorid}
    val type = varchar("type").bindTo {  it.type }

}




interface DBLogsEntity: Entity<DBLogsEntity>{
    companion object : Entity.Factory<DBLogsEntity>()
    val id: Int
    val date: String
    val materialid: Int
    val userid: Int
    val contractorid: Int
    val type: String

}