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
import kotlinx.android.synthetic.main.fragment_napoje_item.*
import kotlinx.android.synthetic.main.fragment_napoje_item.view.*

class NapojeItemFragment(price: Int) : Fragment() {

    val currentPrice = price

    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view = inflater.inflate(R.layout.fragment_napoje_item, container, false)

        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)

        mMainActivityViewModel.updateSummary(view.napoje_item_summary,mMainActivityViewModel.napoje_item,0,0,currentPrice, 0, 0)
        view.napoje_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(napoje_counter, "napoje", "")
            mMainActivityViewModel.updateSummary(view.napoje_item_summary,mMainActivityViewModel.napoje_item,0,0,currentPrice, 0, 0)
        }

        view.napoje_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(napoje_counter, "napoje", "")
            mMainActivityViewModel.updateSummary(view.napoje_item_summary,mMainActivityViewModel.napoje_item,0,0,currentPrice, 0, 0)
        }

        return view
    }


}