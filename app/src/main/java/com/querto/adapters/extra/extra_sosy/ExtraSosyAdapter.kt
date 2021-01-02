package com.querto.adapters.extra.extra_sosy

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.extra.extra_dodatki.ExtraDodatkiAdapter
import com.querto.adapters.home.innerFragments.CalzoneAdapter
import kotlinx.android.synthetic.main.my_extra_sosy_row.view.*

class ExtraSosyAdapter(activityMain: MainActivity, val sosy_names: Array<String>, val sosy_price: IntArray): RecyclerView.Adapter<ExtraSosyAdapter.MyViewHolder>() {
    var activity = activityMain as AppCompatActivity
    private val context: Context = activityMain.applicationContext


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.extra_sosy_row_title

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_extra_sosy_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentName.text= sosy_names.get(position)
    }

    override fun getItemCount(): Int {
        return sosy_names.size
    }
}