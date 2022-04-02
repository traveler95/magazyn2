package com.plcoding.database

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    private val hostname = "mysql37.mydevil.net"
    private val databaseName = "m1521_magazyn"
    private val username = "m1521_etraveler"
    private val password = "Kjkszyj45"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=m1521_etraveler&password=$password&useSSL=false&serverTimezone=UTC"
        ktormDatabase = Database.connect ( jdbcUrl )
    }



    fun getAllTodos(): List<DBTodoEntity> {
        return ktormDatabase.sequenceOf(DBTodoTable).toList()
    }
}