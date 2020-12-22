package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.querto.R


class SosyItemFragment(title: String, price : Int) : Fragment() {

    val currentTitle = title
    val currentPrice = price

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_sosy_item, container, false)


        return view
    }


}