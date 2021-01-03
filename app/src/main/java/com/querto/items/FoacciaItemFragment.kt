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
import kotlinx.android.synthetic.main.fragment_foaccia_item.*
import kotlinx.android.synthetic.main.fragment_foaccia_item.view.*
import kotlinx.android.synthetic.main.fragment_pizza_item.*


class FoacciaItemFragment(name: String, foacciaImg : Int, foacciaPrice : Int) : Fragment() {

    var img = foacciaImg
    var price = foacciaPrice
    val currentName = name
    private lateinit var mMainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_foaccia_item, container, false)
        (activity as MainActivity).CURRENT_ITEM_STATUS=1
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
        val cartMainAdapter = CartMainAdapter((activity as MainActivity), (activity as MainActivity).items)
        view.addFloaccia_Cart_btn.setOnClickListener {
            var normalAmount =mMainActivityViewModel.foaccia_item


            cartMainAdapter.addItem(currentName, "33cm", normalAmount.toString(), price.toString(),arrayListOf(),arrayListOf())
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, CartMainFragment())?.commit()

        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CURRENT_ITEM_STATUS=0
    }

}