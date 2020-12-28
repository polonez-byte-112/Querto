package com.querto.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.querto.MainActivity
import com.querto.R
import com.querto.models.User.User
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
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

        (activity as MainActivity).REGISTER_STATUS=1
        view.register_btn.setOnClickListener {
            inputUser()
        }
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference


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
                   writeNewUser(mAuth.currentUser?.uid.toString(), name, surname, username, password, age)
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
        val user = User(userId, name, surname,username,age)
        database.child("users").child(mAuth.currentUser?.uid.toString()).setValue(user).addOnCompleteListener {
            (activity as MainActivity).nav_view?.setCheckedItem(R.id.home)
            (activity as MainActivity).REGISTER_STATUS=0
            (activity as MainActivity).supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mMainActivityViewModel.homeFragment).commit()

        }
        (activity as MainActivity).updateUI()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).REGISTER_STATUS=0
    }

}