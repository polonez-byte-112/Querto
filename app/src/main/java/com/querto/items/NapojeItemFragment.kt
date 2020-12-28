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
import kotlinx.android.synthetic.main.fragment_napoje_item.*
import kotlinx.android.synthetic.main.fragment_napoje_item.view.*

class NapojeItemFragment(name: String, price: Int, kindOne : String, kindTwo: String) : Fragment() {

    val currentName=name
    val currentPrice = price
    val currentFirstKind = kindOne
    val currentSecondKind =kindTwo


    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view = inflater.inflate(R.layout.fragment_napoje_item, container, false)
        (activity as MainActivity).CURRENT_ITEM_STATUS=1
        println(currentName)


            view.napoje_item_kind_one.text= currentFirstKind
            view.napoje_item_kind_two.text=currentSecondKind



        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)

        mMainActivityViewModel.updateSummary(view.napoje_item_summary,mMainActivityViewModel.napoje_one_item,mMainActivityViewModel.napoje_two_item,0,currentPrice, currentPrice, 0)

        view.napoje_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(napoje_counter, "napoje", "one")
            mMainActivityViewModel.updateSummary(napoje_item_summary,mMainActivityViewModel.napoje_one_item,mMainActivityViewModel.napoje_two_item,0,currentPrice, currentPrice, 0)   }

        view.napoje_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(napoje_counter, "napoje", "one")
            mMainActivityViewModel.updateSummary(napoje_item_summary,mMainActivityViewModel.napoje_one_item,mMainActivityViewModel.napoje_two_item,0,currentPrice, currentPrice, 0)
        }

        view.napoje_item_two_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(napoje_counter_two, "napoje", "two")
            mMainActivityViewModel.updateSummary(napoje_item_summary,mMainActivityViewModel.napoje_one_item,mMainActivityViewModel.napoje_two_item,0,currentPrice, currentPrice, 0)
        }


        view.napoje_item_two_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(napoje_counter_two, "napoje", "two")
            mMainActivityViewModel.updateSummary(napoje_item_summary,mMainActivityViewModel.napoje_one_item,mMainActivityViewModel.napoje_two_item,0,currentPrice, currentPrice, 0)

        }

        val cartMainAdapter = CartMainAdapter((activity as MainActivity), (activity as MainActivity).items)
        view.addNapoje_Cart_btn.setOnClickListener {
            var oneAmount =mMainActivityViewModel.napoje_one_item
            var twoAmount =mMainActivityViewModel.napoje_two_item


            cartMainAdapter.addItem(currentName+" "+currentFirstKind, "", oneAmount.toString(), currentPrice.toString())
            cartMainAdapter.addItem(currentName+" "+currentSecondKind, "", twoAmount.toString(), currentPrice.toString())
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, CartMainFragment())?.commit()

        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CURRENT_ITEM_STATUS=0
    }

}