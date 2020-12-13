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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.querto.R
import com.querto.adapters.AddressAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_address.view.*


class AddressFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_address, container, false)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()


        view.recyclerViewAddress.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
            MainActivityViewModel::class.java)
        view.recyclerViewAddress.adapter = AddressAdapter(requireContext(),  mMainActivityViewModel.address_title, mMainActivityViewModel.address_street,mMainActivityViewModel.address_post_code, mMainActivityViewModel.address_house_number, mMainActivityViewModel.address_city_name)

        if(mAuth.currentUser==null){
            Toast.makeText(requireContext(), "To add address please login",Toast.LENGTH_SHORT).show()
            activity?.nav_view?.setCheckedItem(R.id.login)
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.loginFragment)?.commit()

        }

        view.add_address_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.addAddressFragment)?.commit()
        }
        return view
    }


}