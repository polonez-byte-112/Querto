package com.querto.adapters.innerFragments

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.querto.R
import com.querto.items.*
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_calzone_row.view.*

class CalzoneAdapter(activityMain : Activity, val calzone_images: Array<Int>, val calzone_names: Array<String>, val calzone_desc: Array<String>, val calzone_small_price: IntArray, val calzone_normal_price: IntArray) : RecyclerView.Adapter<CalzoneAdapter.MyViewHolder>() {

    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = activityMain.applicationContext
    var activity = activityMain as AppCompatActivity

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentImage = itemView.calzone_row_img
        val currentId = itemView.calzone_row_id
        val currentName = itemView.calzone_row_title
        val currentDesc = itemView.calzone_row_description
        val currentPriceSmall = itemView.calzone_row_price_small
        val currentPriceNormal = itemView.calzone_row_price_medium
        val box = itemView.calzone_box
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_calzone_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(calzone_images[position])
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = calzone_names.get(position)
        holder.currentDesc.text = calzone_desc.get(position)
        holder.currentPriceSmall.text = calzone_small_price.get(position).toString()+" zł"
        holder.currentPriceNormal.text = calzone_normal_price.get(position).toString()+" zł"
        holder.box.setOnClickListener {
            val calzoneItem = CalzoneItemFragment(calzone_names.get(position),calzone_images[position],calzone_small_price.get(position),calzone_normal_price.get(position) )
            activity.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container,calzoneItem)?.commit()
        }
    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.calzone_names.size
    }


}