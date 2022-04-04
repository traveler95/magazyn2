package com.plcoding.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Material(

    val id: Int,
    var name: String,
    var qty: Int,

    )

