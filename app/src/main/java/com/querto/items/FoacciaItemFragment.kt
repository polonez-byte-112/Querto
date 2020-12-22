package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_foaccia_item.*
import kotlinx.android.synthetic.main.fragment_foaccia_item.view.*
import kotlinx.android.synthetic.main.fragment_pizza_item.*


class FoacciaItemFragment(foacciaImg : Int, foacciaPrice : Int) : Fragment() {

    var img = foacciaImg
    var price = foacciaPrice
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_foaccia_item, container, false)

        view.foaccia_small_img.setImageResource(img)

        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)

        mMainActivityViewModel.updateSummary(view.foaccia_item_summary,mMainActivityViewModel.foaccia_item,0, 0,price,0,0 )
        view.foaccia_item_add_btn.setOnClickListener {
            mMainActivityViewModel.addItem(foaccia_item_counter, "foaccia", "")
            mMainActivityViewModel.updateSummary(foaccia_item_summary,mMainActivityViewModel.foaccia_item,0, 0,price,0,0 )

        }

        view.foaccia_item_remove_btn.setOnClickListener {
            mMainActivityViewModel.removeItem(foaccia_item_counter, "foaccia", "")
            mMainActivityViewModel.updateSummary(foaccia_item_summary,mMainActivityViewModel.foaccia_item,0, 0,price,0,0 )

        }

        return view
    }

}