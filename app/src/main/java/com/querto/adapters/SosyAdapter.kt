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
import kotlinx.android.synthetic.main.my_sosy_row.view.*

class SosyAdapter(contextAdapter: Context,  val sosy_names: Array<String>, val sosy_price: IntArray): RecyclerView.Adapter<SosyAdapter.MyViewHolder>() {

    private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.sosy_row_title
        var currentPrice =itemView.sosy_row_price
        val currentId = itemView.sosy_row_id
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return SosyAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_sosy_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentId.text = (position+1).toString()
        holder.currentName.text = sosy_names.get(position)
        holder.currentPrice.text= sosy_price.get(position).toString()
    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.sosy_names.size
    }
}