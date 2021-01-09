package com.querto.adapters.cart

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.querto.MainActivity
import com.querto.R
import com.querto.fragments.extra.ExtraFragment
import com.querto.models.Cart.CartItem
import com.querto.models.Dodatek.Dodatek
import com.querto.models.Sos.Sos
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_cart_main.*
import kotlinx.android.synthetic.main.my_cart_main_row.view.*

class CartMainAdapter(val activity: MainActivity, val items: ArrayList<CartItem>): RecyclerView.Adapter<CartMainAdapter.MyViewHolder>() {
    private var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private val context = activity.applicationContext

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context as Application).create(
                MainActivityViewModel::class.java)

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val currentName = itemView.cart_main_row_title
            val currentKind = itemView.cart_main_row_kind
            val currentId= itemView.cart_main_row_id
            val currentAmount = itemView.cart_main_row_counter
            val addBtn = itemView.cart_main_row_add_btn
            val removeBtn = itemView.cart_main_row_remove_btn
            val removeItemBtn = itemView.cart_main_row_remove_item_btn
            val itemSummary = itemView.cart_main_row_item_summary
            val currentSosy = itemView.cart_main_row_sosy
            val currentDodatki = itemView.cart_main_row_dodatki
            val box = itemView.cart_main_box
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_cart_main_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var amount =  Integer.parseInt(items.get(position).i_amount)
        var price = Integer.parseInt(items.get(position).i_price)
       holder.currentName.text = items.get(position).i_name
        holder.currentKind.text=items.get(position).i_size
        holder.currentId.text  = (position + 1).toString()
        holder.currentAmount.text= amount.toString()
        holder.itemSummary.text = (amount* price).toString()+" zł"

        var wszystkieDodatki = ""
        var wszystkieSosy =""


        items[position].i_dodatki.forEach {
           wszystkieDodatki +=it.d_name
        }

        items[position].i_sosy.forEach {
            wszystkieSosy += it.s_name
        }


        holder.currentDodatki.text = wszystkieDodatki
        holder.currentSosy.text = wszystkieSosy


        holder.addBtn.setOnClickListener {


            amount++
            items.get(position).i_amount = amount.toString()
            changeItem(activity, Integer.parseInt(items[position].i_price),"plus")

            //Naprawic tutaj problem z cena ( cena tez zwraca z iloscia  i zeby to bylo widac w summarry!)
            notifyDataSetChanged()
        }


        holder.removeBtn.setOnClickListener {
            if(amount>0){
                amount--
                items.get(position).i_amount = amount.toString()
                changeItem(activity, Integer.parseInt(items[position].i_price), "minus")
             }


            if(items.get(position).i_amount.equals("0")){
                items.removeAt(position)
                if(items.size==0){
                    activity.cart_no_items_text.visibility = View.VISIBLE
                    activity.cart_main_recycler_view.visibility = View.GONE
                    activity.summary_box.visibility=View.GONE
                }
            }

            notifyDataSetChanged()
        }

        holder.removeItemBtn.setOnClickListener{


            deleteItem(activity, Integer.parseInt(items[position].i_price), Integer.parseInt(items[position].i_amount))
            items.removeAt(position)
            if(items.size==0){
                activity.cart_no_items_text.visibility = View.VISIBLE
                activity.cart_main_recycler_view.visibility = View.GONE
                activity.summary_box.visibility=View.GONE
            }
            notifyDataSetChanged()
        }

        holder.box.setOnClickListener {

            if(!( items.get(position).i_name == "Pepsi  light" || items.get(position).i_name == "Pepsi  zwykła"|| items.get(position).i_name == "Woda  niegazowana" || items.get(position).i_name == "Woda  gazowana"))
                activity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, ExtraFragment(activity,items.get(position) )).commit()
        }

        if( items.get(position).i_name == "Pepsi  light" || items.get(position).i_name == "Pepsi  zwykła"|| items.get(position).i_name == "Woda  niegazowana" || items.get(position).i_name == "Woda  gazowana"){
            holder.currentSosy.visibility=View.GONE
            holder.currentDodatki.visibility=View.GONE
        }else{
            holder.currentSosy.visibility=View.VISIBLE
            holder.currentDodatki.visibility=View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
       return items.size
    }



    fun addItem(name: String, size: String, amount: String, price: String, sosy:ArrayList<Sos> ,dodatki: ArrayList<Dodatek>){
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        if(Integer.parseInt(amount)>0){
            val item = CartItem(database.push().key.toString(),name, size, amount,price, sosy, dodatki)
           activity.items.add(item)

            notifyDataSetChanged()

        }
    }

    fun deleteItem(activity: MainActivity, price: Int, amount: Int) {

        activity.summarry.value =  activity.summarry.value?.minus(price* amount)

    }



    fun changeItem( activity: MainActivity,price: Int, state:String){

        when(state){
            "plus" -> {
                activity.summarry.value = activity.summarry.value?.plus(price)
            }

             "minus"->{
                 activity.summarry.value =  activity.summarry.value?.minus(price)
             }
        }
    }


}