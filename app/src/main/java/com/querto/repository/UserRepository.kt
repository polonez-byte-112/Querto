package com.querto.repository

import androidx.lifecycle.LiveData
import com.querto.data.UserDao
import com.querto.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun loginUser(username: String, password: String){
        userDao.loginUser(username, password)
    }

}