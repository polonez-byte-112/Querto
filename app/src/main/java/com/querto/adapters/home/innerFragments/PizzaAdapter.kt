package com.querto.adapters.home.innerFragments

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
import kotlinx.android.synthetic.main.my_pizza_row.view.*


class PizzaAdapter(activityMain: Activity, val pizza_images: Array<Int>, val pizza_names: Array<String>, val pizza_desc: Array<String>, val pizza_small_price: IntArray, val pizza_medium_price: IntArray, val pizza_big_price: IntArray) : RecyclerView.Adapter<PizzaAdapter.MyViewHolder>() {
    private var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = activityMain.applicationContext
    var activity = activityMain as AppCompatActivity

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context as Application).create(MainActivityViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_pizza_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentImage.setImageResource(pizza_images[position])
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = pizza_names.get(position)
        holder.currentDesc.text = pizza_desc.get(position)
        holder.currentPriceSmall.text = pizza_small_price.get(position).toString()+" zł"
        holder.currentPriceMedium.text = pizza_medium_price.get(position).toString()+" zł"
        holder.currentPriceBig.text = pizza_big_price.get(position).toString()+" zł"
        holder.box.setOnClickListener {
              val pizzaItem = PizzaItemFragment(pizza_names.get(position), pizza_images[position], pizza_small_price.get(position), pizza_medium_price.get(position), pizza_big_price.get(position))
            activity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container,pizzaItem).commit()
        }


    }

    override fun getItemCount(): Int {
        return mMainActivityViewModel.pizza_names.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentImage = itemView.pizza_row_img
        val currentId = itemView.pizza_row_id
        val currentName = itemView.pizza_row_title
        val currentDesc = itemView.pizza_row_description
        val currentPriceSmall = itemView.pizza_row_price_small
        val currentPriceMedium = itemView.pizza_row_price_medium
        val currentPriceBig = itemView.pizza_row_price_big
        val box = itemView.pizza_box



    }
}


