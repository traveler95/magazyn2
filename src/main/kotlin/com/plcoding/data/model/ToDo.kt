package com.plcoding.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(

    val id: Int,
    var title: String,
    var ilosc: Int,

    )
