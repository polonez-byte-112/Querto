package com.querto.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.querto.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(user: User)

    @Query("SELECT user_table.* FROM user_table WHERE username= :username AND password=:password")
    suspend fun loginUser(username: String, password: String): User?
}