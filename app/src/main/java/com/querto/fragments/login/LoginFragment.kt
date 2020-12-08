package com.querto.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    lateinit var username: EditText
    lateinit var password: EditText



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mMainActivityViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
                .create(MainActivityViewModel::class.java)

        username = view.loginUsername
        password = view.loginPassword

        view.login_btn.setOnClickListener {
            val takenUsername = username.text.toString()
            val takenPassword = password.text.toString()

            if(takenUsername.isEmpty() || takenPassword.isEmpty()){
                Toast.makeText(context, "Fill all columns", Toast.LENGTH_SHORT).show()
            } else {
                //Check user is valid or not in db and you will get the callback on line #
                mMainActivityViewModel.checkLogin(takenUsername, takenPassword)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginObserver()
    }

    private fun setupLoginObserver() {
        mMainActivityViewModel.loginStatus.observe(this, Observer { isValidUser ->
            if (isValidUser) {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Bad login or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

}