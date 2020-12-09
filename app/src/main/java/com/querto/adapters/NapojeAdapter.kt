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
import kotlinx.android.synthetic.main.my_napoje_row.view.*
import kotlinx.android.synthetic.main.my_sosy_row.view.*

class NapojeAdapter(contextAdapter: Context, val napoje_names: Array<String>, val napoje_kind_one: Array<String>, val napoje_kind_two: Array<String>, val napoje_price: IntArray): RecyclerView.Adapter<NapojeAdapter.MyViewHolder>() {


    private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.napoje_row_title
        var currentPrice =itemView.napoje_row_price
        val currentId = itemView.napoje_row_id
        val currentFirstKind= itemView.napoje_row_rodzaj_one
        val currentSecondKind = itemView.napoje_row_rodzaj_two
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return NapojeAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_napoje_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentId.text = (position+1).toString()
        holder.currentName.text = napoje_names.get(position)
        holder.currentPrice.text= napoje_price.get(position).toString()
        holder.currentFirstKind.text= napoje_kind_one.get(position)
        holder.currentSecondKind.text= napoje_kind_two.get(position)
    }

    override fun getItemCount(): Int {
       return mMainActivityViewModel.napoje_names.size
    }


}