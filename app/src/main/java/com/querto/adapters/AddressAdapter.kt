package com.querto.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.querto.R
import com.querto.model.Address
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_address_row.view.*

class AddressAdapter(contextAdapter: Context, addresses: ArrayList<Address>):


    RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = contextAdapter
    private val local_addreses : ArrayList<Address> = addresses
    private  var database: DatabaseReference
    private  var mAuth: FirebaseAuth

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application).create(MainActivityViewModel::class.java)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentTitle = itemView.address_title
        val currentId = itemView.address_id
        val currentStreet = itemView.address_street
        val currentPostcode = itemView.address_postcode
        val currentHouseNumber = itemView.address_number
        val currentAddressCityName = itemView.address_city
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_address_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentTitle.text =  local_addreses[position].name
        holder.currentId.text = (position + 1).toString()
        holder.currentStreet.text =local_addreses[position].street
        holder.currentPostcode.text =local_addreses[position].postcode
        holder.currentHouseNumber.text = local_addreses[position].house_number
        holder.currentAddressCityName.text = local_addreses[position].city_name
    }

    override fun getItemCount(): Int {
        return local_addreses.size
    }


}