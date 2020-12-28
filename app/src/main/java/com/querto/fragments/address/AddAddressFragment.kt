        package com.querto.fragments.address

        import android.content.Intent
        import android.os.Bundle
        import androidx.fragment.app.Fragment
        import android.view.LayoutInflater
        import android.view.View
        import android.view.ViewGroup
        import android.widget.Toast
        import androidx.lifecycle.ViewModelProvider
        import com.google.firebase.auth.FirebaseAuth
        import com.google.firebase.database.*
        import com.querto.MainActivity
        import com.querto.R
        import com.querto.models.Address.Address
        import com.querto.viewmodel.MainActivityViewModel
        import kotlinx.android.synthetic.main.fragment_add_address.view.*

        class AddAddressFragment : Fragment() {
            private lateinit var database: DatabaseReference
            private lateinit var mAuth: FirebaseAuth
            private lateinit var mMainActivityViewModel: MainActivityViewModel
            private  var local_name: String?=""
            private var local_street:String?=""
            private var local_postcode:String?=""
            private var local_house_nr:String?=""
            private var local_city_name: String?=""
            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
               var view = inflater.inflate(R.layout.fragment_add_address, container, false)

                database = FirebaseDatabase.getInstance().reference
                mAuth = FirebaseAuth.getInstance()

                mMainActivityViewModel =
                    ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
                        .create(MainActivityViewModel::class.java)

                (activity as MainActivity).EDIT_ADDRESS_STATUS=1

                if(mAuth.currentUser!=null){
                    database.child("addresses").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                println("Address found")
                               local_name =snapshot.child("name").value.toString()
                                local_street =  snapshot.child("street").value.toString()
                                local_postcode = snapshot.child("postcode").value.toString()
                                local_house_nr =  snapshot.child("house_number").value.toString()
                                local_city_name=  snapshot.child("city_name").value.toString()

                                view.addAddressName.setText(local_name)
                                view.addAddressStreet.setText(local_street)
                                view.addAddressHouseNumber.setText(local_house_nr)
                                view.addAddressCityZipCode.setText(local_postcode)
                                view.addAddressCityName.setText(local_city_name)

                            }

                        }

                        override fun onCancelled(error: DatabaseError) {
                            println(error.message)
                        }
                    })

                }

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

                database = FirebaseDatabase.getInstance().reference
                mAuth = FirebaseAuth.getInstance()


                val address = Address(mAuth.currentUser?.uid, addressName, addressStreet,addressZipCode, addressNumber, addressCityName)
                database.child("addresses").child(mAuth.currentUser?.uid.toString()).setValue(address).addOnCompleteListener {
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


            override fun onDestroyView() {
                super.onDestroyView()
                (activity as MainActivity).EDIT_ADDRESS_STATUS=0
            }

        }