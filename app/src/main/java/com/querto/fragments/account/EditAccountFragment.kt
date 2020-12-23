package com.querto.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.R
import com.querto.models.User.User
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_edit_account.view.*


class EditAccountFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_edit_account, container, false)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        mMainActivityViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
                .create(MainActivityViewModel::class.java)

        database.child("users").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    var name = snapshot.child("name").value.toString()
                    var surname = snapshot.child("surname").value.toString()
                    var age = snapshot.child("age").value.toString()


                    view.account_edit_name.setText(name)
                    view.account_edit_surname.setText(surname)
                    view.account_edit_age.setText(age)


                    view.account_edit_btn.setOnClickListener {
                        var  input_name = view.account_edit_name.text.toString()
                        var input_surname = view.account_edit_surname.text.toString()
                        var input_age = view.account_edit_age.text.toString()

        if(inputCheck(input_name, input_surname, input_age)){
            val user = User(mAuth.currentUser?.uid, input_name,input_surname,snapshot.child("username").value.toString() ,  input_age)
            database.child("users").child(mAuth.currentUser?.uid.toString()).setValue(user).addOnCompleteListener {
                if(it.isSuccessful){
                    activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.accountFragment)?.commit()
                    Toast.makeText(requireContext(), "Edited data", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(requireContext(), "Problem with edit Profile.\nCheck Your connection", Toast.LENGTH_SHORT).show()
                }
            }

        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })




        return view
    }



    private fun inputCheck(firstName: String, secondName: String, age: String)=
        firstName.isNotEmpty() && secondName.isNotEmpty()  && age.isNotEmpty()
}