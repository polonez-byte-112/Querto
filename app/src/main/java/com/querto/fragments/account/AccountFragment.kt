package com.querto.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.MainActivity
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.fragment_address.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class AccountFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private val openFloatingMenu: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim) }
    private val closeFloatingMenu: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim) }
    private var floatingButtonClicked = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var view = inflater.inflate(R.layout.fragment_account, container, false)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        database.child("users").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    val name = snapshot.child("name").value.toString()
                    val surname = snapshot.child("surname").value.toString()
                    val age = snapshot.child("age").value.toString()


                    view.account_name.text = name
                    view.account_surname.text = surname
                    view.account_age.text = age


                }

            }

            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })


        view.account_main_floating_btn.setOnClickListener {
            onMainClicked()
        }

        view.account_delete_floating_btn.setOnClickListener {
            deleteAccount()

        }
        return  view
    }

    private fun deleteAccount() {
        //Usunie konto i wyloguje
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser!=null){
            mAuth.currentUser!!.delete()
            database.child("users").child(mAuth.currentUser?.uid.toString()).removeValue()
            database.child("addresses").child(mAuth.currentUser?.uid.toString()).removeValue()
            (activity as MainActivity).updateUI()


            mMainActivityViewModel =
                    ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
                            .create(MainActivityViewModel::class.java)


            (activity as MainActivity).supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mMainActivityViewModel.loginFragment).commit()
        }
    }


    private fun onMainClicked() {
        setVisibility(floatingButtonClicked)
        setAnimation(floatingButtonClicked)

        floatingButtonClicked = !floatingButtonClicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            account_edit_account_floating_btn.startAnimation(openFloatingMenu)
            account_delete_floating_btn.startAnimation(openFloatingMenu)
        } else {
            account_edit_account_floating_btn.startAnimation(closeFloatingMenu)
            account_delete_floating_btn.startAnimation(closeFloatingMenu)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            account_edit_account_floating_btn.visibility = View.VISIBLE
            account_delete_floating_btn.visibility = View.VISIBLE
        } else {
            account_edit_account_floating_btn.visibility= View.INVISIBLE
            account_delete_floating_btn.visibility = View.INVISIBLE
        }
    }


}