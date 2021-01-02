package com.querto.adapters.extra.extra_dodatki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.extra.extra_sosy.ExtraSosyAdapter
import kotlinx.android.synthetic.main.my_extra_dodatki_row.view.*

class ExtraDodatkiAdapter(activityMain : MainActivity, val dodatki_names: Array<String>, val dodatki_small_price: IntArray, val dodatki_medium_price: IntArray, val dodatki_big_price: IntArray) : RecyclerView.Adapter<ExtraDodatkiAdapter.MyViewHolder>() {
    var activity = activityMain as AppCompatActivity
    private val context: Context = activityMain.applicationContext

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var currentName = itemView.extra_dodatki_row_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_extra_dodatki_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentName.text = dodatki_names.get(position)
    }

    override fun getItemCount(): Int {
        return dodatki_names.size
    }
}