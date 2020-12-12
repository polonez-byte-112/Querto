package com.querto.fragments.register

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.model.User
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment() {

    private lateinit var mMainActivityViewModel: MainActivityViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)
        view.register_btn.setOnClickListener {
            inputUser()
        }


        return view


    }

    private fun inputUser() {
        val name = registerName.text.toString()
        val surname = registerSurname.text.toString()
        val age = registerAge.text
        val username = registerUsername.text.toString()
        val password = registerPassword.text.toString()
        username.toLowerCase()
        name.toLowerCase()
        surname.toLowerCase()

        surname.capitalize()
        name.capitalize()
        username.capitalize()

        if (inputCheck(name, surname, username, password, age)) {
            val user = User(0, name, surname, username, password, Integer.parseInt(age.toString()))
            mMainActivityViewModel.addUser(user)
            Toast.makeText(requireContext(), "Added a user", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "Fill all columns", Toast.LENGTH_SHORT).show()
        }

    }


    private fun inputCheck(firstName: String, secondName: String, username: String, password: String, age: Editable): Boolean {
        return !((TextUtils.isEmpty(firstName) || firstName.length > 17) || (TextUtils.isEmpty(secondName) || secondName.length > 17) || (TextUtils.isEmpty(username) || username.length > 22) || (TextUtils.isEmpty(password) || password.length > 22) || age.isEmpty())
    }

}