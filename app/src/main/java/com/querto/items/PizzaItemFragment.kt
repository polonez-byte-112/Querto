package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_pizza_item.*
import kotlinx.android.synthetic.main.fragment_pizza_item.view.*


class PizzaItemFragment(pizza_images: Int, pizza_small_price: Int,  pizza_medium_price:  Int,  pizza_big_price: Int) : Fragment(){

    val currentImg = pizza_images

    val currentSmallPrice = pizza_small_price
    val currentMediumPrice = pizza_medium_price
    val currentBigPrice = pizza_big_price

    private lateinit var mMainActivityViewModel: MainActivityViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
     var view = inflater.inflate(R.layout.fragment_pizza_item, container, false)


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

        return view
    }


}