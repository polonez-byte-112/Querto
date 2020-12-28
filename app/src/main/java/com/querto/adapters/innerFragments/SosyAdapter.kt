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
import com.querto.items.SosyItemFragment
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_sosy_row.view.*


class SosyAdapter(activityMain: Activity, val sosy_names: Array<String>, val sosy_price: IntArray) : RecyclerView.Adapter<SosyAdapter.MyViewHolder>() {

    private  var mMainActivityViewModel: MainActivityViewModel
    private val context: Context = activityMain.applicationContext

    var activity = activityMain as AppCompatActivity

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
            context as Application
        ).create(
            MainActivityViewModel::class.java
        )
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.sosy_row_title
        var currentPrice = itemView.sosy_row_price
        val currentId = itemView.sosy_row_id
        val box = itemView.sosy_box
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.my_sosy_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentId.text = (position + 1).toString()
        holder.currentName.text = sosy_names.get(position)
        holder.currentPrice.text = sosy_price.get(position).toString()+" zł"
        holder.box.setOnClickListener {
            val sosyItem = SosyItemFragment(sosy_names.get(position), sosy_price.get(position))
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container,sosyItem)?.commit()
        }
    }



    override fun getItemCount(): Int {
        return mMainActivityViewModel.sosy_names.size
    }
}