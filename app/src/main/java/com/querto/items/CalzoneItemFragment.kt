package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_calzone_item.*
import kotlinx.android.synthetic.main.fragment_calzone_item.view.*
import kotlinx.android.synthetic.main.fragment_pizza_item.*
import kotlinx.android.synthetic.main.fragment_pizza_item.view.*

class CalzoneItemFragment(image : Int, normal_price: Int, big_price:Int) : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    val currentImg = image
    val currentNormalPrice = normal_price
    val currentBigPrice = big_price
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_calzone_item, container, false)

        view.calzone_medium_img.setImageResource(currentImg)
        view.calzone_big_img.setImageResource(currentImg)


        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)


        mMainActivityViewModel.updateSummary(view.calzone_item_summary,0,mMainActivityViewModel.calzone_medium_item, mMainActivityViewModel.calzone_big_item,0, currentNormalPrice, currentBigPrice)
        view.calzone_medium_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(calzone_medium_item_counter, "calzone", "medium")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_medium_item, mMainActivityViewModel.calzone_big_item,0, currentNormalPrice, currentBigPrice)
        }

        view.calzone_medium_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(calzone_medium_item_counter, "calzone", "medium")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_medium_item, mMainActivityViewModel.calzone_big_item,0, currentNormalPrice, currentBigPrice)
        }


        view.calzone_big_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(calzone_big_item_counter, "calzone", "big")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_medium_item, mMainActivityViewModel.calzone_big_item,0, currentNormalPrice, currentBigPrice)
        }


        view.calzone_big_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(calzone_big_item_counter, "calzone", "big")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_medium_item, mMainActivityViewModel.calzone_big_item,0, currentNormalPrice, currentBigPrice)
        }


        return view
    }

}