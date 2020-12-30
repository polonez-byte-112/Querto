package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.cart.CartMainAdapter
import com.querto.fragments.cart.CartMainFragment
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_calzone_item.*
import kotlinx.android.synthetic.main.fragment_calzone_item.view.*

class CalzoneItemFragment(calzoneName: String, image : Int, normal_price: Int, big_price:Int) : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    val currentImg = image
    val currentNormalPrice = normal_price
    val currentBigPrice = big_price
    var currentName = calzoneName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_calzone_item, container, false)
        (activity as MainActivity).CURRENT_ITEM_STATUS=1
        view.calzone_medium_img.setImageResource(currentImg)
        view.calzone_big_img.setImageResource(currentImg)


        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)


        mMainActivityViewModel.updateSummary(view.calzone_item_summary,0,mMainActivityViewModel.calzone_small_item, mMainActivityViewModel.calzone_medium_item,0, currentNormalPrice, currentBigPrice)
        view.calzone_medium_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(calzone_medium_item_counter, "calzone", "medium")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_small_item, mMainActivityViewModel.calzone_medium_item,0, currentNormalPrice, currentBigPrice)
        }

        view.calzone_medium_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(calzone_medium_item_counter, "calzone", "medium")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_small_item, mMainActivityViewModel.calzone_medium_item,0, currentNormalPrice, currentBigPrice)
        }


        view.calzone_big_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(calzone_big_item_counter, "calzone", "big")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_small_item, mMainActivityViewModel.calzone_medium_item,0, currentNormalPrice, currentBigPrice)
        }


        view.calzone_big_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(calzone_big_item_counter, "calzone", "big")
            mMainActivityViewModel.updateSummary(calzone_item_summary,0,mMainActivityViewModel.calzone_small_item, mMainActivityViewModel.calzone_medium_item,0, currentNormalPrice, currentBigPrice)
        }

        val cartMainAdapter = CartMainAdapter((activity as MainActivity), (activity as MainActivity).items, )

        view.addCalzone_Cart_btn.setOnClickListener {
            var smallAmount = mMainActivityViewModel.calzone_small_item
            var normalAmount =mMainActivityViewModel.calzone_medium_item


            cartMainAdapter.addItem(currentName, "26cm", smallAmount.toString(), currentNormalPrice.toString())
            cartMainAdapter.addItem(currentName, "33cm", normalAmount.toString(), currentBigPrice.toString())
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, CartMainFragment())?.commit()

        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CURRENT_ITEM_STATUS=0
    }

}