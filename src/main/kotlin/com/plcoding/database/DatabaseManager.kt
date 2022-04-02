package com.plcoding.database

import com.plcoding.data.model.ToDo
import com.plcoding.data.model.ToDoDraft
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
    private val password = "Kjkszyj45"
    private val ktormDatabase: Database



    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false&serverTimezone=UTC"
        ktormDatabase = Database.connect ( jdbcUrl )
    }




    fun getAllTodos(): List<DBTodoEntity> {
        return ktormDatabase.sequenceOf(DBTodoTable).toList()
    }




    fun getTodo(id: Int): DBTodoEntity? {
        return ktormDatabase.sequenceOf(DBTodoTable).firstOrNull { it.id eq id }
    }




    fun addTodo(draft: ToDoDraft): ToDo{

        val insertedId = ktormDatabase.insertAndGenerateKey(DBTodoTable){
            set(DBTodoTable.title, draft.title)
            set(DBTodoTable.ilosc, draft.ilosc)
        }as Int
        return ToDo(insertedId, draft.title, draft.ilosc
        )
    }




    fun updateTodo(id: Int, draft: ToDoDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBTodoTable){
            set(DBTodoTable.title, draft.title)
            set(DBTodoTable.ilosc, draft.ilosc)
            where {
                it.id eq id
            }
        }
        return updatedRows>0
    }





    fun removeTodo(id: Int): Boolean{
        val deletedRows=ktormDatabase.delete(DBTodoTable){
            it.id eq id
        }
        return deletedRows>0
    }
}