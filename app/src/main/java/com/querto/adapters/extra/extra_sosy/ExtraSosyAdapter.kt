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
import com.querto.models.Cart.CartItem
import com.querto.models.Dodatek.Dodatek
import com.querto.models.Sos.Sos
import kotlinx.android.synthetic.main.my_extra_sosy_row.view.*

class ExtraSosyAdapter(activityMain: MainActivity, val sosy_names: Array<String>, val sosy_price: IntArray ,val item: CartItem): RecyclerView.Adapter<ExtraSosyAdapter.MyViewHolder>() {
    var activity = activityMain as AppCompatActivity
    private val context: Context = activityMain.applicationContext


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val currentName = itemView.extra_sosy_row_title
        val addItemToCounter = itemView.extra_sosy_row_add_btn
        var currentCounter = itemView.extra_sosy_row_counter
        var removeItemFromCounter= itemView.extra_sosy_row_remove_btn

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        for(i in 0 until 7 step 1){
            item.i_sosy.add(Sos("","","0"))
        }

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_extra_sosy_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentName.text= sosy_names.get(position)
        var amount = Integer.parseInt(holder.currentCounter.text.toString())


        holder.addItemToCounter.setOnClickListener {
            holder.currentCounter.text  = (amount+1).toString()
        }

        holder.removeItemFromCounter.setOnClickListener {
            if(amount >0){
                holder.currentCounter.text = (amount-1).toString()
                // Jeszcze po tym trzeba aktualizowac podsumowanie
                }

        }
    }

    override fun getItemCount(): Int {
        return sosy_names.size
    }
}