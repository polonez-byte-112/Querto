package com.querto.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname : String,
    val username: String,
    val password: String,
    val age: Int
): Parcelable