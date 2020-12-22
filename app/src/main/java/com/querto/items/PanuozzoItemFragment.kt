package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_panuozzo_item.*
import kotlinx.android.synthetic.main.fragment_panuozzo_item.view.*


class PanuozzoItemFragment(image : Int, normal_price: Int, big_price:Int) : Fragment() {

    private lateinit var mMainActivityViewModel: MainActivityViewModel
    val currentImg = image
    val currentNormalPrice = normal_price
    val currentBigPrice = big_price

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_panuozzo_item, container, false)

        view.panuozzo_medium_img.setImageResource(currentImg)
        view.panuozzo_big_img.setImageResource(currentImg)

        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)


        mMainActivityViewModel.updateSummary(view.panuozzo_item_summary,0,mMainActivityViewModel.panuozzo_medium_item, mMainActivityViewModel.panuozzo_big_item,0, currentNormalPrice, currentBigPrice)

        view.panuozzo_medium_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(panuozzo_medium_item_counter, "panuozzo", "medium")
            mMainActivityViewModel.updateSummary(panuozzo_item_summary,0,mMainActivityViewModel.panuozzo_medium_item, mMainActivityViewModel.panuozzo_big_item,0, currentNormalPrice, currentBigPrice)
        }

        view.panuozzo_medium_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(panuozzo_medium_item_counter, "panuozzo", "medium")
            mMainActivityViewModel.updateSummary(panuozzo_item_summary,0,mMainActivityViewModel.panuozzo_medium_item, mMainActivityViewModel.panuozzo_big_item,0, currentNormalPrice, currentBigPrice)
        }


        view.panuozzo_big_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(panuozzo_big_item_counter, "panuozzo", "big")
            mMainActivityViewModel.updateSummary(panuozzo_item_summary,0,mMainActivityViewModel.panuozzo_medium_item, mMainActivityViewModel.panuozzo_big_item,0, currentNormalPrice, currentBigPrice)
        }


        view.panuozzo_big_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(panuozzo_big_item_counter, "panuozzo", "big")
            mMainActivityViewModel.updateSummary(panuozzo_item_summary,0,mMainActivityViewModel.panuozzo_medium_item, mMainActivityViewModel.panuozzo_big_item,0, currentNormalPrice, currentBigPrice)
        }

        return view
    }


}