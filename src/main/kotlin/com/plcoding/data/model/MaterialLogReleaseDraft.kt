package com.plcoding.data.model

data class MaterialLogReleaseDraft(
    val qty: Int,
    val userid: Int,
    val contractorid: Int,
    val materialid: Int,
    val type: String
)