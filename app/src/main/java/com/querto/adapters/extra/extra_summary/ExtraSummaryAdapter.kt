package com.querto.adapters.extra.extra_summary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.querto.MainActivity
import com.querto.R
import com.querto.models.Cart.CartItem
import kotlinx.android.synthetic.main.my_extra_summary_row.view.*

class ExtraSummaryAdapter( activityMain: MainActivity, val item: CartItem): RecyclerView.Adapter<ExtraSummaryAdapter.MyViewHolder>() {
    var activity = activityMain as AppCompatActivity
    private val context: Context = activityMain.applicationContext
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var summarryName = itemView.extra_summary_row_title
        var summaryAmountAndPrice = itemView.extra_summary_row_price_and_amount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        updateSummary()
       return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_extra_summary_row, parent, false))


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.summarryName.text = (activity as MainActivity).mySummaryItemListName[position]
        holder.summaryAmountAndPrice.text = (activity as MainActivity).mySummaryItemListAmountAndPrice[position]
    }

    override fun getItemCount(): Int {
        var size = 0

        //Wyswietlamy tylko dodatki które są na obecne etc

        item.i_dodatki.forEach {
            if(!(it.d_name=="" && it.d_price=="" && it.d_amount=="0")){
                size +=1

            }
        }
        item.i_sosy.forEach {
            if(!(it.s_name=="" && it.s_price=="" && it.s_amount=="0")){
                size +=1
            }
        }
        return size
    }

    fun updateSummary(){
        println("Aktualizujemy summary!")
        (activity as MainActivity).mySummaryItemListName.clear()
        (activity as MainActivity).mySummaryItemListAmountAndPrice.clear()

        item.i_sosy.forEach {
            if(!(it.s_name=="" && it.s_price=="" && it.s_amount=="0")){
                (activity as MainActivity).mySummaryItemListName.add(it.s_name)
                (activity as MainActivity).mySummaryItemListAmountAndPrice.add(it.s_amount+" x "+it.s_price+" zł")
            }
        }

        item.i_dodatki.forEach {
            if(!(it.d_name=="" && it.d_price=="" && it.d_amount=="0")){

                (activity as MainActivity).mySummaryItemListName.add(it.d_name)
                (activity as MainActivity).mySummaryItemListAmountAndPrice.add(it.d_amount+" x "+it.d_price+" zł")

            }
        }


    }
}