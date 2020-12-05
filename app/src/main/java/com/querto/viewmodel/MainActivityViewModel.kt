package com.querto.viewmodel


import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.querto.data.UserDatabase
import com.querto.fragments.home.HomeFragment
import com.querto.fragments.login.LoginFragment
import com.querto.fragments.register.RegisterFragment
import com.querto.model.User
import com.querto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val homeFragment = HomeFragment()
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()

    private val repository: UserRepository

    private val mutableLoginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = mutableLoginStatus

    init {
        val userDao = UserDatabase.getDataBase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    /**
     * Function is to validate provided user on login page
     */
    fun checkLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loginUser(username, password)?.let {
                mutableLoginStatus.postValue(true)
            } ?: mutableLoginStatus.postValue(false)
        }
    }

    fun shareApp(context: Context) {
        Toast.makeText(context, "App Shared", Toast.LENGTH_SHORT).show()
    }

    fun openStore(context: Context) {
        Toast.makeText(context, "Store opened", Toast.LENGTH_SHORT).show()
    }

    fun sendMail(context: Context) {
        Toast.makeText(context, "Send Email", Toast.LENGTH_SHORT).show()
    }
}