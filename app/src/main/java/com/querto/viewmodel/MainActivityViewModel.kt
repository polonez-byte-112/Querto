package com.querto.viewmodel


import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.querto.R
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


    private val mutableLoginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = mutableLoginStatus


    var pizza_names: Array<String> = application.resources.getStringArray(R.array.pizza_titles)
    var pizza_desc: Array<String> = application.resources.getStringArray(R.array.pizza_desc)
    val pizza_small_price : IntArray = application.resources.getIntArray(R.array.pizza_small_price)
    val pizza_medium_price : IntArray = application.resources.getIntArray(R.array.pizza_medium_price)
    val pizza_big_price : IntArray = application.resources.getIntArray(R.array.pizza_big_price)
    //dodac te zdjecia potem
    var pizza_img: Array<Int> = arrayOf( R.drawable.napoletana,  R.drawable.margherita, R.drawable.estate, R.drawable.pepperone, R.drawable.pancetta, R.drawable.ortolana, R.drawable.marinara, R.drawable.diavola, R.drawable.messicana, R.drawable.quattro_formaggi, R.drawable.sugoza, R.drawable.semola, R.drawable.capriciossa, R.drawable.vulcano, R.drawable.romana, R.drawable.capodanno, R.drawable.primavera, R.drawable.regina, R.drawable.quattro_stagioni, R.drawable.cilento, R.drawable.tirolese, R.drawable.michele, R.drawable.pollo, R.drawable.havana, R.drawable.siciliana, R.drawable.sandra, R.drawable.bari, R.drawable.gringo, R.drawable.angelo, R.drawable.spinaci)

    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDataBase(application).userDao()
        repository = UserRepository(userDao)


    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
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


    fun sendMail(context : Context) {
        Toast.makeText(context, "Send Email", Toast.LENGTH_SHORT).show()
    }
}