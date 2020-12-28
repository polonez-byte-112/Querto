package com.querto.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.MainActivity
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    lateinit var username: EditText
    lateinit var password: EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        mMainActivityViewModel =
                ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
                        .create(MainActivityViewModel::class.java)
        (activity as MainActivity).LOGIN_STATUS=1
        username = view.loginUsername
        password = view.loginPassword


        view.login_btn.setOnClickListener {
          var  takenUsername = username.text.toString()
          var  takenPassword = password.text.toString()
            if (takenUsername.isEmpty() || takenPassword.isEmpty()) {
                Toast.makeText(context, "Fill all columns", Toast.LENGTH_SHORT).show()
            } else {
                mMainActivityViewModel.checkLogin(takenUsername,takenPassword)
            }
        }

        view.redirectLoginBtn.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.registerFragment)?.commit()

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

                (activity as MainActivity).LOGIN_STATUS=0
                (activity as MainActivity).updateUI()
                (activity as MainActivity).nav_view?.setCheckedItem(R.id.home)
                (activity as MainActivity).supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mMainActivityViewModel.homeFragment).commit()
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Bad email or password", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).LOGIN_STATUS=0
    }




}