package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_napoje_item.*
import kotlinx.android.synthetic.main.fragment_napoje_item.view.*

class NapojeItemFragment(price: Int, kindOne : String, kindTwo: String) : Fragment() {

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


        return view
    }


}