package com.querto.adapters

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.querto.R
import com.querto.items.*
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_napoje_row.view.*

class NapojeAdapter(activityMain: Activity, val napoje_names: Array<String>, val napoje_kind_one: Array<String>, val napoje_kind_two: Array<String>, val napoje_price: IntArray) : RecyclerView.Adapter<NapojeAdapter.MyViewHolder>() {


    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = activityMain.applicationContext
    var activity = activityMain as AppCompatActivity

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.napoje_row_title
        var currentPrice = itemView.napoje_row_price
        val currentId = itemView.napoje_row_id
        val currentFirstKind = itemView.napoje_row_rodzaj_one
        val currentSecondKind = itemView.napoje_row_rodzaj_two
        val box = itemView.napoje_box
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_napoje_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = napoje_names.get(position)
        holder.currentPrice.text = napoje_price.get(position).toString()
        holder.currentFirstKind.text = napoje_kind_one.get(position)
        holder.currentSecondKind.text = napoje_kind_two.get(position)
        holder.box.setOnClickListener {
            val napojeItem = NapojeItemFragment(napoje_price.get(position), napoje_kind_one.get(position), napoje_kind_two.get(position))
            activity.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, napojeItem)?.commit()

        }
    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.napoje_names.size
    }


}