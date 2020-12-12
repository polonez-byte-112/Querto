package com.querto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.querto.model.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {


        private var INSTANCE: UserDatabase? = null


        fun getDataBase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            //Ta metoda jest uzywana w kazdym watku po kolei
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user_database").build()
                INSTANCE = instance
                return instance
            }
        }

    }
}