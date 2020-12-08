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
import kotlinx.android.synthetic.main.my_row.view.*

class PizzaAdapter(contextAdapter: Context, val pizza_images: Array<Int>, val pizza_names: Array<String>, val pizza_desc: Array<String>, val pizza_small_price: IntArray, val pizza_medium_price: IntArray, val pizza_big_price: IntArray): RecyclerView.Adapter<PizzaAdapter.MyViewHolder>() {
   private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
       mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(MainActivityViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_row, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(pizza_images[position])
        holder.currentId.text = (position+1).toString()
        holder.currentName.text = pizza_names.get(position)
        holder.currentDesc.text = pizza_desc.get(position)
        holder.currentPriceSmall.text = pizza_small_price.get(position).toString()
        holder.currentPriceMedium.text = pizza_medium_price.get(position).toString()
        holder.currentPriceBig.text = pizza_big_price.get(position).toString()



    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.pizza_names.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentImage = itemView.row_img
        val currentId = itemView.row_id
        val currentName = itemView.row_title
        val currentDesc = itemView.row_description
        val currentPriceSmall = itemView.row_price_small
        val currentPriceMedium = itemView.row_price_medium
        val currentPriceBig = itemView.row_price_big
        val box = itemView.box


    }
}