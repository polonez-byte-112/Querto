        package com.querto.fragments.address

        import android.os.Bundle
        import androidx.fragment.app.Fragment
        import android.view.LayoutInflater
        import android.view.View
        import android.view.ViewGroup
        import android.widget.Toast
        import androidx.lifecycle.ViewModelProvider
        import com.google.firebase.auth.FirebaseAuth
        import com.google.firebase.database.DatabaseReference
        import com.google.firebase.database.FirebaseDatabase
        import com.querto.R
        import com.querto.adapters.AddressAdapter
        import com.querto.model.Address
        import com.querto.viewmodel.MainActivityViewModel
        import kotlinx.android.synthetic.main.fragment_add_address.view.*

        class AddAddressFragment : Fragment() {
            private lateinit var database: DatabaseReference
            private lateinit var mAuth: FirebaseAuth
            private lateinit var mMainActivityViewModel: MainActivityViewModel
            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
                // Inflate the layout for this fragment
               var view = inflater.inflate(R.layout.fragment_add_address, container, false)



                mMainActivityViewModel =
                    ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
                        .create(MainActivityViewModel::class.java)

                view.addAddressButton.setOnClickListener {

                    val addressName = view.addAddressName.text.toString()
                    val addressStreet = view.addAddressStreet.text.toString()
                    val addressNumber = view.addAddressHouseNumber.text.toString()
                    val addressZipCode = view.addAddressCityZipCode.text.toString()
                    val addressCityName = view.addAddressCityName.text.toString()

                    if(inputCheck(addressName,addressStreet,addressNumber,addressZipCode, addressCityName)){
                        mAuth = FirebaseAuth.getInstance()
                        database = FirebaseDatabase.getInstance().reference

                        addAddress(addressName, addressStreet, addressNumber, addressZipCode, addressCityName)
                    }else{
                        Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                    }
                }
                return view
            }

            private fun addAddress(addressName: String, addressStreet: String, addressNumber: String,addressZipCode: String, addressCityName: String) {

                val address = Address(mAuth.currentUser?.uid, addressName, addressStreet,addressZipCode, addressNumber, addressCityName)
                database.child("addresses").child(database.push().key.toString()).setValue(address).addOnCompleteListener {
                    if(it.isSuccessful){
                        activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.addressFragment)?.commit()
                        Toast.makeText(requireContext(), "Added address", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(requireContext(), "Fail at creating address", Toast.LENGTH_SHORT).show()
                    }
                }



            }

            private fun inputCheck(addressName: String, addressStreet: String, addressNumber: String,addressZipCode: String, adressCityName: String)=
                addressName.isNotEmpty() && addressStreet.isNotEmpty() && addressNumber.isNotEmpty() && addressZipCode.isNotEmpty() && adressCityName.isNotEmpty() && addressZipCode.length==5



        }