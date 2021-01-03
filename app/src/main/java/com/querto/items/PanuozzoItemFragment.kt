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
import kotlinx.android.synthetic.main.fragment_panuozzo_item.*
import kotlinx.android.synthetic.main.fragment_panuozzo_item.view.*


class PanuozzoItemFragment(name : String,image : Int, normal_price: Int, big_price:Int) : Fragment() {

    private lateinit var mMainActivityViewModel: MainActivityViewModel
    val currentImg = image
    val currentName =name
    val currentNormalPrice = normal_price
    val currentBigPrice = big_price

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_panuozzo_item, container, false)
        (activity as MainActivity).CURRENT_ITEM_STATUS=1
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

        val cartMainAdapter = CartMainAdapter((activity as MainActivity), (activity as MainActivity).items)

        view.addPanuozzo_Cart_btn.setOnClickListener {
            var normalAmount = mMainActivityViewModel.panuozzo_medium_item
            var bigAmount =mMainActivityViewModel.panuozzo_big_item


            cartMainAdapter.addItem(currentName, "26cm", normalAmount.toString(), currentNormalPrice.toString(),arrayListOf(),arrayListOf())
            cartMainAdapter.addItem(currentName, "33cm", bigAmount.toString(), currentBigPrice.toString(),arrayListOf(),arrayListOf())

            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, CartMainFragment())?.commit()

        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CURRENT_ITEM_STATUS=0
    }

}