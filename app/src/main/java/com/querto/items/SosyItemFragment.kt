package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_panuozzo_item.view.*
import kotlinx.android.synthetic.main.fragment_sosy_item.*
import kotlinx.android.synthetic.main.fragment_sosy_item.view.*


class SosyItemFragment(title: String, price : Int) : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    val currentTitle = title
    val currentPrice = price

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_sosy_item, container, false)


        view.sosy_item_name.text= currentTitle

        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)


        mMainActivityViewModel.updateSummary(view.sosy_item_summary,mMainActivityViewModel.sosy_item,0, 0,currentPrice, 0, 0)


        view.sosy_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(sosy_item_counter, "sosy", "")
            mMainActivityViewModel.updateSummary(sosy_item_summary,mMainActivityViewModel.sosy_item,0, 0,currentPrice, 0, 0)


        }


        view.sosy_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(sosy_item_counter, "sosy", "")
            mMainActivityViewModel.updateSummary(sosy_item_summary,mMainActivityViewModel.sosy_item,0, 0,currentPrice, 0, 0)


        }



        return view
    }


}