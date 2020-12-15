package com.querto.fragments.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.R
import com.querto.adapters.AddressAdapter
import com.querto.model.Address
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_address.view.*


class AddressFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var addressAdapter: AddressAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        var view =  inflater.inflate(R.layout.fragment_address, container, false)

        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()


        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)

        addressAdapter = AddressAdapter(requireContext(), mMainActivityViewModel.list_of_addresses)
        getAddresses()

        if(mAuth.currentUser==null){
            Toast.makeText(requireContext(), "To add address please login",Toast.LENGTH_SHORT).show()
            activity?.nav_view?.setCheckedItem(R.id.login)
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.loginFragment)?.commit()

        }

        view.recyclerViewAddress.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.recyclerViewAddress.adapter = AddressAdapter(requireContext(), mMainActivityViewModel.list_of_addresses)



        view.add_address_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.addAddressFragment)?.commit()
        }


        return view
    }



    fun getAddresses(){
        val ref = FirebaseDatabase.getInstance().reference.child("addresses")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userId = dataSnapshot.child("userId").value.toString()
                    val name =  dataSnapshot.child("name").value.toString()
                    val street = dataSnapshot.child("street").value.toString()
                    val postcode = dataSnapshot.child("street").value.toString()
                    val house_number = dataSnapshot.child("house_number").value.toString()
                    val city_name = dataSnapshot.child("city_name").value.toString()
                    val address = Address(userId,name,street,postcode,house_number,city_name)
                    mMainActivityViewModel.list_of_addresses.add(address)
                    addressAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }
}



