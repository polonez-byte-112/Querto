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
import kotlinx.android.synthetic.main.my_foaccia_row.view.*


class FoacciaAdapter(contextAdapter: Context, val foaccia_images: Array<Int>, val foaccia_names: Array<String>, val foaccia_desc: Array<String>, val foaccia_price: IntArray) : RecyclerView.Adapter<FoacciaAdapter.MyViewHolder>() {

    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
                MainActivityViewModel::class.java)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoacciaAdapter.MyViewHolder {
        return FoacciaAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_foaccia_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(foaccia_images[position])
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = foaccia_names.get(position)
        holder.currentDesc.text = foaccia_desc.get(position)
        holder.currentPriceMedium.text = foaccia_price.get(position).toString()


    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.focaccia_names.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentImage = itemView.foaccia_row_img
        val currentId = itemView.foaccia_row_id
        val currentName = itemView.foaccia_row_title
        val currentDesc = itemView.foaccia_row_description
        val currentPriceMedium = itemView.foaccia_row_price_medium
        val box = itemView.foaccia_box
    }

}