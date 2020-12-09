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
import kotlinx.android.synthetic.main.my_panuozzo_row.view.*

class PanuozzoAdapter(contextAdapter: Context, val panuozzo_images: Array<Int>, val panuozzo_names: Array<String>, val panuozzo_desc: Array<String>, val panuozzo_normal_price: IntArray, val panuozzo_big_price: IntArray): RecyclerView.Adapter<PanuozzoAdapter.MyViewHolder>() {


    private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
                MainActivityViewModel::class.java)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return PanuozzoAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_panuozzo_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(panuozzo_images[position])
        holder.currentId.text = (position+1).toString()
        holder.currentName.text = panuozzo_names.get(position)
        holder.currentDesc.text = panuozzo_desc.get(position)
        holder.currentPriceNormal.text = panuozzo_normal_price.get(position).toString()
        holder.currentPriceBig.text = panuozzo_big_price.get(position).toString()
    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.panuozzo_names.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentImage = itemView.panuozzo_row_img
        val currentId = itemView.panuozzo_row_id
        val currentName = itemView.panuozzo_row_title
        val currentDesc = itemView.panuozzo_row_description
        val currentPriceNormal = itemView.panuozzo_row_price_normal
        val currentPriceBig = itemView.panuozzo_row_price_big
        val box = itemView.panuozzo_box
    }

}