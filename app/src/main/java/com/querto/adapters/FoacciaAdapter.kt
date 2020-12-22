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
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_foaccia_row.view.*


class FoacciaAdapter(activityMain : Activity, val foaccia_images: Array<Int>, val foaccia_names: Array<String>, val foaccia_desc: Array<String>, val foaccia_price: IntArray) : RecyclerView.Adapter<FoacciaAdapter.MyViewHolder>() {

    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = activityMain.applicationContext
    var activity = activityMain as AppCompatActivity

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context as Application).create(
                MainActivityViewModel::class.java)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_foaccia_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(foaccia_images[position])
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = foaccia_names.get(position)
        holder.currentDesc.text = foaccia_desc.get(position)
        holder.currentPriceMedium.text = foaccia_price.get(position).toString()
        holder.box.setOnClickListener {
            Toast.makeText(context, "Wcisnieto  Foaccia ${(position + 1)} ", Toast.LENGTH_SHORT).show()
            activity.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mMainActivityViewModel.foacciaItem)?.commit()

        }


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