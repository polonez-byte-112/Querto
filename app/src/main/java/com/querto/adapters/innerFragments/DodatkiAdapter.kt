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
import kotlinx.android.synthetic.main.my_dodatki_row.view.*

class DodatkiAdapter(activityMain : Activity, val dodatki_names: Array<String>, val dodatki_small_price: IntArray, val dodatki_medium_price: IntArray, val dodatki_big_price: IntArray) : RecyclerView.Adapter<DodatkiAdapter.MyViewHolder>() {

    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = activityMain.applicationContext
    var activity = activityMain as AppCompatActivity

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context as Application).create(
                MainActivityViewModel::class.java)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.dodatki_row_title
        var currentSmallPrice = itemView.dodatki_row_price_small
        var currentMediumPrice = itemView.dodatki_row_price_medium
        var currentBigPrice = itemView.dodatki_row_price_big
        val currentId = itemView.dodatki_row_id
        val box = itemView.dodatki_box
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_dodatki_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = dodatki_names.get(position)
        holder.currentSmallPrice.text = dodatki_small_price.get(position).toString()+" zł"
        holder.currentMediumPrice.text = dodatki_medium_price.get(position).toString()+" zł"
        holder.currentBigPrice.text = dodatki_big_price.get(position).toString()+" zł"
        holder.box.setOnClickListener {
            var dodatkiItem  = DodatkiItemFragment(dodatki_names.get(position),mMainActivityViewModel.dodatki_one_array,dodatki_small_price.get(position), dodatki_medium_price.get(position), dodatki_big_price.get(position) )

            when(position){
                0->  dodatkiItem = DodatkiItemFragment(dodatki_names.get(position),mMainActivityViewModel.dodatki_one_array,dodatki_small_price.get(position), dodatki_medium_price.get(position), dodatki_big_price.get(position) )
                1-> dodatkiItem = DodatkiItemFragment(dodatki_names.get(position),mMainActivityViewModel.dodatki_two_array,dodatki_small_price.get(position), dodatki_medium_price.get(position), dodatki_big_price.get(position) )
                2->  dodatkiItem = DodatkiItemFragment(dodatki_names.get(position),mMainActivityViewModel.dodatki_three_array,dodatki_small_price.get(position), dodatki_medium_price.get(position), dodatki_big_price.get(position) )
                3-> dodatkiItem = DodatkiItemFragment(dodatki_names.get(position),mMainActivityViewModel.dodatki_four_array,dodatki_small_price.get(position), dodatki_medium_price.get(position), dodatki_big_price.get(position) )
                4-> dodatkiItem = DodatkiItemFragment(dodatki_names.get(position),mMainActivityViewModel.dodatki_five_array,dodatki_small_price.get(position), dodatki_medium_price.get(position), dodatki_big_price.get(position) )

            }


            activity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, dodatkiItem).commit()
        }

    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.dodatki_names.size
    }

}