package com.querto.fragments.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.querto.MainActivity
import com.querto.R

class CartSummaryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view = inflater.inflate(R.layout.fragment_cart_summary, container, false)
        (activity as MainActivity).CART_SUMMARY_STATUS=1
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).CART_SUMMARY_STATUS=0
    }


}