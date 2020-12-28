package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.querto.MainActivity
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_dodatki_item.*
import kotlinx.android.synthetic.main.fragment_dodatki_item.view.*
import kotlinx.android.synthetic.main.fragment_napoje_item.*


class DodatkiItemFragment(name: String, items: Array<String>, smallPrice: Int, mediumPrice: Int, bigPrice: Int) : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    var currentItems = items
    var currentSmallPrice = smallPrice
    var currentMediumPrice = mediumPrice
    var currentBigPrice = bigPrice

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view = inflater.inflate(R.layout.fragment_dodatki_item, container, false)
        (activity as MainActivity).CURRENT_ITEM_STATUS=1
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)

        var adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,currentItems )

        view.dodatki_item_spinner.adapter = adapter
        view.dodatki_item_two_spinner.adapter=adapter
        view.dodatki_item_three_spinner.adapter=adapter

        /**
        * dodac przejecie wcisnietego obiektu
        * dodac tez np opcje (jak nic sie nie wybierze z dodatkow ale wezmie ilosc)
        * naprawic to poprzez nadanie pierwszej opcji etc albo zmiane koloru tekstu
        *
        */
        mMainActivityViewModel.updateSummary(view.dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        view.dodatki_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(dodatki_item_counter, "dodatki", "small")
            mMainActivityViewModel.updateSummary(dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }


        view.dodatki_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(dodatki_item_counter, "dodatki", "small")
            mMainActivityViewModel.updateSummary(dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }



        view.dodatki_item_two_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(dodatki_item_two_counter, "dodatki", "medium")
            mMainActivityViewModel.updateSummary(dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }


        view.dodatki_item_two_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(dodatki_item_two_counter, "dodatki", "medium")
            mMainActivityViewModel.updateSummary(dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }





        view.dodatki_item_three_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(dodatki_item_three_counter, "dodatki", "big")
            mMainActivityViewModel.updateSummary(dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }


        view.dodatki_item_three_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(dodatki_item_three_counter, "dodatki", "big")
            mMainActivityViewModel.updateSummary(dodatki_item_summary,mMainActivityViewModel.dodatki_small_item, mMainActivityViewModel.dodatki_medium_item, mMainActivityViewModel.dodatki_big_item, currentSmallPrice, currentMediumPrice, currentBigPrice)
        }



        return view;
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CURRENT_ITEM_STATUS=0
    }



}