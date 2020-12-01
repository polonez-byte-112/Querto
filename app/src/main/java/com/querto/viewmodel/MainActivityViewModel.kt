package com.querto.viewmodel


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.querto.fragments.home.HomeFragment
import com.querto.fragments.login.LoginFragment
import com.querto.fragments.register.RegisterFragment


class MainActivityViewModel : ViewModel() {

    val homeFragment= HomeFragment()
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()


    fun shareApp(context: Context){
        Toast.makeText(context, "App Shared", Toast.LENGTH_SHORT).show()
    }


    fun openStore(context: Context){
        Toast.makeText(context, "Store opened", Toast.LENGTH_SHORT).show()
    }


    fun sendMail(context : Context){
        Toast.makeText(context, "Send Email", Toast.LENGTH_SHORT).show()
    }


}