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
import kotlinx.android.synthetic.main.my_dodatki_row.view.*

class DodatkiAdapter(contextAdapter: Context, val dodatki_names: Array<String>,val dodatki_small_price: IntArray,val dodatki_medium_price: IntArray, val dodatki_big_price: IntArray): RecyclerView.Adapter<DodatkiAdapter.MyViewHolder>() {

    private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.dodatki_row_title
        var currentSmallPrice =itemView.dodatki_row_price_small
        var currentMediumPrice =itemView.dodatki_row_price_medium
        var currentBigPrice =itemView.dodatki_row_price_big
        val currentId = itemView.dodatki_row_id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return DodatkiAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_dodatki_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentId.text = (position+1).toString()
        holder.currentName.text = dodatki_names.get(position)
        holder.currentSmallPrice.text= dodatki_small_price.get(position).toString()
        holder.currentMediumPrice.text= dodatki_medium_price.get(position).toString()
        holder.currentBigPrice.text= dodatki_big_price.get(position).toString()

    }

    override fun getItemCount(): Int {
      return mMainActivityViewModel.dodatki_names.size
    }

}