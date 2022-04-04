package com.plcoding.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBMaterialTable: Table<DBMaterialEntity>("material"){

    val id = int("id").primaryKey().bindTo {  it.id}
    val name = varchar("name").bindTo { it.name }
    val qty = int("qty").bindTo { it.qty }

}




interface DBMaterialEntity: Entity<DBMaterialEntity>{
    companion object : Entity.Factory<DBMaterialEntity>()
    val id: Int
    val name: String
    val qty: Int
}