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
import kotlinx.android.synthetic.main.my_calzone_row.view.*
import kotlinx.android.synthetic.main.my_foaccia_row.view.*

class CalzoneAdapter(contextAdapter: Context, val calzone_images: Array<Int>, val calzone_names: Array<String>, val calzone_desc: Array<String>, val calzone_normal_price: IntArray, val calzone_big_price: IntArray): RecyclerView.Adapter<CalzoneAdapter.MyViewHolder>() {

    private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val currentImage = itemView.calzone_row_img
        val currentId = itemView.calzone_row_id
        val currentName = itemView.calzone_row_title
        val currentDesc = itemView.calzone_row_description
        val currentPriceNormal = itemView.calzone_row_price_normal
        val currentPriceBig = itemView.calzone_row_price_big
        val box = itemView.calzone_box
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return CalzoneAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_calzone_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(calzone_images[position])
        holder.currentId.text = (position+1).toString()
        holder.currentName.text = calzone_names.get(position)
        holder.currentDesc.text = calzone_desc.get(position)
        holder.currentPriceNormal.text = calzone_normal_price.get(position).toString()
        holder.currentPriceBig.text = calzone_big_price.get(position).toString()
    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.calzone_names.size
    }


}