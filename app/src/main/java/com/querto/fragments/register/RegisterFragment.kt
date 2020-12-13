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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.querto.R
import com.querto.model.User
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment() {

    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

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
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        if(mAuth.currentUser!=null){
            mAuth.signOut()
        }

        return view


    }

    private fun inputUser() {
        val name = registerName.text.toString()
        val surname = registerSurname.text.toString()
        val age = registerAge.text.toString()
        val username = registerUsername.text.toString()
        val password = registerPassword.text.toString()
        name.toLowerCase()
        surname.toLowerCase()

        surname.capitalize()
        name.capitalize()

        if (inputCheck(name, surname, username, password,age)) {

            mAuth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance().reference

            mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(requireContext(), "Created a user", Toast.LENGTH_SHORT).show()
                   writeNewUser(database.push().key.toString(), name, surname, username, password, age)
                }else{
                    Toast.makeText(requireContext(), "Fail at creating a user", Toast.LENGTH_SHORT).show()
                    it.exception?.printStackTrace()
                }
            }

        } else {
            Toast.makeText(requireContext(), "Fill all columns", Toast.LENGTH_SHORT).show()
        }

    }


    private fun inputCheck(firstName: String, secondName: String, username: String, password: String,age: String)=
        firstName.isNotEmpty() && secondName.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && age.isNotEmpty()

    private fun writeNewUser(userId: String, name: String, surname: String, username: String, password: String, age: String) {
        val user = User(userId, name, surname,username, password,age)
        database.child("users").child(userId).setValue(user)

    }

}