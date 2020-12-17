package com.querto.fragments.address

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
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_address.*
import kotlinx.android.synthetic.main.fragment_address.view.*


class AddressFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    var userId: String? =""
    var name: String? =""
    var street: String? =""
    var postcode: String? =""
    var house_number: String? =""
    var city_name: String? =""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        var view =  inflater.inflate(R.layout.fragment_address, container, false)

        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()


        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)




        if(mAuth.currentUser==null){
            Toast.makeText(requireContext(), "To add address please login",Toast.LENGTH_SHORT).show()
            activity?.nav_view?.setCheckedItem(R.id.login)
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.loginFragment)?.commit()
        }

        database.child("addresses").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    userId = dataSnapshot.child("userId").value.toString()
                    name = dataSnapshot.child("name").value.toString()
                    street = dataSnapshot.child("street").value.toString()
                    postcode = dataSnapshot.child("postcode").value.toString()
                    house_number = dataSnapshot.child("house_number").value.toString()
                    city_name = dataSnapshot.child("city_name").value.toString()

                    if(!(userId.equals("") && name.toString().equals("") &&  street.toString().equals("") && postcode.toString().equals("") &&  house_number.toString().equals("")  &&  city_name.toString().equals(""))){
                        view.address_biger_box.visibility= View.VISIBLE
                        view.address_title.text = name.toString()+"\u0020"
                        view.address_street.text = street.toString()
                        view.address_postcode.text = postcode.toString()
                        view.address_number.text = house_number.toString()
                        view.address_city.text = city_name.toString()

                        view.delete_address_btn.setOnClickListener {
                            view.address_biger_box.visibility= View.INVISIBLE
                            view.address_title.text = ""
                            view.address_street.text = ""
                            view.address_postcode.text = ""
                            view.address_number.text =""
                            view.address_city.text = ""

                            database.child("addresses").child(mAuth.currentUser?.uid.toString()).removeValue()
                        }
                    }

                } else {
                    System.out.println("Database doesnt exist");
                    view.address_biger_box.visibility= View.INVISIBLE
                    view.address_title.text = ""
                    view.address_street.text = ""
                    view.address_postcode.text = ""
                    view.address_number.text =""
                    view.address_city.text = ""
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        })





        view.add_address_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.addAddressFragment)?.commit()
        }


        return view
    }






}



