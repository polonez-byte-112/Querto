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


        holder.currentCounter.text = item.i_sosy[position].s_amount
        var  amount = Integer.parseInt(item.i_sosy[position].s_amount)
        holder.addItemToCounter.setOnClickListener {
            amount +=1
            if(amount==1){
                item.i_sosy[position].s_name = "+ "+sosy_names[position]+", "
                item.i_sosy[position].s_price = sosy_price[position].toString()
                item.i_sosy[position].s_amount=amount.toString()

            }else{
                item.i_sosy[position].s_amount=amount.toString()
            }

            var itemPrice =  Integer.parseInt(item.i_price)
            item.i_price = (itemPrice+sosy_price[position]).toString()

            holder.currentCounter.text  = amount.toString()


        }

        holder.removeItemFromCounter.setOnClickListener {

            if(amount >0){
                amount -=1

                var itemPrice =  Integer.parseInt(item.i_price)
                item.i_price = (itemPrice-sosy_price[position]).toString()

                holder.currentCounter.text = amount.toString()
                item.i_sosy[position].s_amount=amount.toString()
                }


            if(amount==0){
                item.i_sosy[position].s_name = ""
                item.i_sosy[position].s_price = ""
                item.i_sosy[position].s_amount= "0"
            }

        }
    }

    override fun getItemCount(): Int {
        return sosy_names.size
    }
}