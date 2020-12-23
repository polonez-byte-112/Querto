package com.querto.models.User

import androidx.room.Entity


@Entity(tableName = "user_table")
data class User(
        val userId :String?,
        val name: String,
        val surname: String,
        val username: String,
        val age: String
)