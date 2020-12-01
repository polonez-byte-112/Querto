package com.querto.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="surname")
    val surname : String,

    @ColumnInfo(name="username")
    val username: String,

    @ColumnInfo(name="password")
    val password: String,

    @ColumnInfo(name="age")
    val age: Int
): Parcelable