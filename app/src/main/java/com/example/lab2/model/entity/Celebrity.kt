package com.example.lab2.model.entity

data class Celebrity(
    val name: String,
    val nationality: String?,
    val height: Float?,
    val birthday: String?,
    val occupation: List<String>?
)
