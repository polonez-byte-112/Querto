package com.querto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "user_table")
data class User(
        val userId :String?,
        val name: String,
        val surname: String,
        val username: String,
        val password: String,
        val age: String
)