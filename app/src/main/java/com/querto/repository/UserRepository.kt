package com.querto.repository

import com.querto.data.UserDao
import com.querto.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.loginUser(username, password)
    }

}