package com.querto.adapters

import android.app.Application
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.querto.viewmodel.MainActivityViewModel


class FoacciaAdapter(contextAdapter: Context, val foaccia_images: Array<Int>, val foaccia_names: Array<String>, val foaccia_desc: Array<String>, val foaccia_price: IntArray): RecyclerView.Adapter<FoacciaAdapter.MyViewHolder>() {

    private var mMainActivityViewModel : MainActivityViewModel
    private val context: Context = contextAdapter

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(contextAdapter.applicationContext as Application).create(
            MainActivityViewModel::class.java)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}