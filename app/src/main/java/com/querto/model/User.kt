package com.querto.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        @NotNull
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "surname")
        val surname: String,

        @ColumnInfo(name = "username")
        val username: String,

        @ColumnInfo(name = "password")
        val password: String,

        @ColumnInfo(name = "age")
        val age: Int
)