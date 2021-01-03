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
import com.querto.models.Cart.CartItem
import com.querto.models.Dodatek.Dodatek
import kotlinx.android.synthetic.main.my_extra_dodatki_row.view.*

class ExtraDodatkiAdapter(activityMain : MainActivity, val dodatki_names: Array<String>, val dodatki_small_price: IntArray, val dodatki_medium_price: IntArray,val dodatki_big_price: IntArray,val item: CartItem) : RecyclerView.Adapter<ExtraDodatkiAdapter.MyViewHolder>() {
    var activity = activityMain as AppCompatActivity
    private val context: Context = activityMain.applicationContext

    var currentPrice = 0

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var currentName = itemView.extra_dodatki_row_title
        var currentCounter = itemView.extra_dodatki_row_counter
        var addItemToCounter = itemView.extra_dodatki_row_add_btn
        var removeItemFromCounter = itemView.extra_dodatki_row_remove_btn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        for(i in 0 until 38 step 1){
            item.i_dodatki.add(Dodatek("","","0"))
        }

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_extra_dodatki_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.currentName.text = dodatki_names[position]
        currentPrice = 0
        var amount = 0

        getCurrentPrice(dodatki_small_price[position], dodatki_medium_price[position], dodatki_big_price[position])





     holder.currentCounter.text = item.i_dodatki[position].d_amount
        amount = Integer.parseInt(item.i_dodatki[position].d_amount)



        holder.addItemToCounter.setOnClickListener {

            amount +=1

            if(amount ==1){
                var dodatek = Dodatek("+ "+ dodatki_names[position] +", ", currentPrice.toString(), amount.toString())
               item.i_dodatki.add(position, dodatek)
            }else{
                item.i_dodatki[position].d_amount = amount.toString()
            }

          var item_price =   Integer.parseInt(item.i_price)
            item_price +=currentPrice
            item.i_price = item_price.toString()



            holder.currentCounter.text  = amount.toString()


            println("Nazwa : ${dodatki_names.get(position)},Cena: $currentPrice, Ilosc:  $amount")
        }

        holder.removeItemFromCounter.setOnClickListener {
            if(amount >0) {
                amount -=1

                var item_price =   Integer.parseInt(item.i_price)
                item_price -=currentPrice
                item.i_price = item_price.toString()

                holder.currentCounter.text = amount.toString()
                item.i_dodatki[position].d_amount = amount.toString()
            }

            if(amount==0){
                holder.currentCounter.text = amount.toString()
                if(item.i_dodatki.size>0){
                    currentPrice=0
                    println("Nazwa : ${item.i_dodatki[position].d_name},Cena: ${item.i_dodatki[position].d_price}, Ilosc:  ${item.i_dodatki[position].d_amount}")

                    item.i_dodatki.get(position).d_name=""
                    item.i_dodatki.get(position).d_price=""
                    item.i_dodatki.get(position).d_amount="0"
                }

            }


        }

    }

    override fun getItemCount(): Int {
        return dodatki_names.size
    }


    fun getCurrentPrice(smallPrice: Int, mediumPrice: Int, bigPrice:Int) {
        if(item.i_size == "26cm"){
           currentPrice=  smallPrice
        }

        if(item.i_size == "33cm"){
            currentPrice=  mediumPrice
        }

        if(item.i_size == "41cm"){
            currentPrice=  bigPrice
        }
    }


}