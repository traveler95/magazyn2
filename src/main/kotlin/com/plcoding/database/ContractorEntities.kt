package com.plcoding.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBContractorTable: Table<DBContractorEntity>("contractors"){

    val id = int("id").primaryKey().bindTo {  it.id}
    val name = varchar("name").bindTo { it.name }

}




interface DBContractorEntity: Entity<DBContractorEntity>{
    companion object : Entity.Factory<DBContractorEntity>()
    val id: Int
    val name: String

}