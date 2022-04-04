package com.plcoding.data.model

import java.time.LocalDate


data class Log(
    val id: Int,
   // val date: LocalDate,
    val materialid: Int,
    val userid: Int,
    val contractorid: Int
)