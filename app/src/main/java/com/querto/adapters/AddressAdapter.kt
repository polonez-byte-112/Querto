package com.querto.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_address_row.view.*

class AddressAdapter(contextAdapter: Context, val addressTitles: ArrayList<String>, val addressStreets: ArrayList<String>, val addressPostcodes: ArrayList<String>, val addressNumber: ArrayList<String>, val addressCityNames: ArrayList<String>):
    RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application).create(
            MainActivityViewModel::class.java)
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentTitle = itemView.address_title
        val currentId = itemView.address_id
        val currentStreet = itemView.address_street
        val currentPostcode = itemView.address_postcode
        val currentHouseNumber = itemView.address_number
        val currentAddressCityName = itemView.address_city
        val box = itemView.address_box
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return AddressAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_address_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentTitle.text = addressTitles[position]
        holder.currentId.text = (position + 1).toString()
        holder.currentStreet.text = addressStreets[position]
        holder.currentPostcode.text = addressPostcodes[position]
        holder.currentHouseNumber.text = addressNumber[position]
        holder.currentAddressCityName.text = addressCityNames[position]
    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.address_title.size
    }
}