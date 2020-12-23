package com.querto.adapters.cart

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.querto.R
import com.querto.adapters.innerFragments.CalzoneAdapter
import com.querto.models.Cart.CartItem
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.my_cart_main_row.view.*

class CartMainAdapter(val context: Context, val items: ArrayList<CartItem>): RecyclerView.Adapter<CartMainAdapter.MyViewHolder>() {
    private var mMainActivityViewModel: MainActivityViewModel
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    init {
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application).create(
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_cart_main_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var amount =  Integer.parseInt(items.get(position).i_amount)
       holder.currentName.text = items.get(position).i_name
        holder.currentKind.text=items.get(position).i_size
        holder.currentId.text  = (position + 1).toString()
        holder.currentAmount.text= amount.toString()

        holder.addBtn.setOnClickListener {
            amount++
            items.get(position).i_amount = amount.toString()
            notifyDataSetChanged()
        }

        holder.removeBtn.setOnClickListener {
            if(amount>0)
                amount--
            items.get(position).i_amount = amount.toString()

            if(items.get(position).i_amount.equals("0")){
                items.removeAt(position)
            }

            notifyDataSetChanged()
        }

        holder.removeItemBtn.setOnClickListener{
            //usuwa obecny obiekt z listy
            items.removeAt(position)
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
       return items.size
    }



    fun addItem(name: String, size: String, amount: String, price: String){
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        if(Integer.parseInt(amount)>0){
            val item = CartItem(database.push().key.toString(),name, size, amount, (Integer.parseInt(amount)* Integer.parseInt(price)).toString())
            mMainActivityViewModel.items.add(item)
            println("\nId: ${item.i_id}\nName: ${item.i_name}\nSize: ${item.i_size}\nAmount: ${item.i_amount}")
            notifyDataSetChanged()

        }
    }


}