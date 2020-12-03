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

    @Query("SELECT * FROM user_table WHERE username= :username AND password=:password")
    fun  loginUser(username: String, password: String): User
}