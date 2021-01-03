package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.cart.CartMainAdapter
import com.querto.fragments.cart.CartMainFragment
import com.querto.models.Cart.CartItem
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_cart_main.*
import kotlinx.android.synthetic.main.fragment_pizza_item.*
import kotlinx.android.synthetic.main.fragment_pizza_item.view.*


class PizzaItemFragment( pizza_name: String, pizza_images: Int, pizza_small_price: Int,  pizza_medium_price:  Int,  pizza_big_price: Int) : Fragment(){

    val currentImg = pizza_images
    val currentPizzaName = pizza_name
    val currentSmallPrice = pizza_small_price
    val currentMediumPrice = pizza_medium_price
    val currentBigPrice = pizza_big_price

    private lateinit var mMainActivityViewModel: MainActivityViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
     var view = inflater.inflate(R.layout.fragment_pizza_item, container, false)
        (activity as MainActivity).CURRENT_ITEM_STATUS=1

        view.pizza_small_img.setImageResource(currentImg)
        view.pizza_medium_img.setImageResource(currentImg)
        view.pizza_big_img.setImageResource(currentImg)


        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)



        mMainActivityViewModel.updateSummary(view.pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)

        view.pizza_small_item_add_btn.setOnClickListener {
        mMainActivityViewModel.addItem(pizza_small_item_counter, "pizza", "small")
        mMainActivityViewModel.updateSummary(pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }

        view.pizza_small_item_remove_btn.setOnClickListener {
           mMainActivityViewModel.removeItem(pizza_small_item_counter, "pizza", "small")
            mMainActivityViewModel.updateSummary(pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }



        view.pizza_medium_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(pizza_medium_item_counter, "pizza", "medium")
            mMainActivityViewModel.updateSummary(pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }

        view.pizza_medium_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(pizza_medium_item_counter, "pizza", "medium")
            mMainActivityViewModel.updateSummary(pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }


        view.pizza_big_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(pizza_big_item_counter, "pizza", "big")
            mMainActivityViewModel.updateSummary(pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }

        view.pizza_big_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(pizza_big_item_counter, "pizza", "big")
            mMainActivityViewModel.updateSummary(pizza_item_summary, mMainActivityViewModel.pizza_small_item, mMainActivityViewModel.pizza_medium_item, mMainActivityViewModel.pizza_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }

        val cartMainAdapter = CartMainAdapter((activity as MainActivity), (activity as MainActivity).items)

        view.addPizza_Cart_btn.setOnClickListener {
            val smallPizzas  = mMainActivityViewModel.pizza_small_item
            val mediumPizzas  = mMainActivityViewModel.pizza_medium_item
            val bigPizzas  = mMainActivityViewModel.pizza_big_item

            cartMainAdapter.addItem(currentPizzaName, "26cm", smallPizzas.toString(), currentSmallPrice.toString(),arrayListOf(),arrayListOf())
            cartMainAdapter.addItem(currentPizzaName, "33cm", mediumPizzas.toString(), currentMediumPrice.toString(), arrayListOf(),arrayListOf())
            cartMainAdapter.addItem(currentPizzaName, "41cm", bigPizzas.toString(), currentBigPrice.toString(), arrayListOf(),arrayListOf())

            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, CartMainFragment())?.commit()

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CURRENT_ITEM_STATUS=0
    }

}