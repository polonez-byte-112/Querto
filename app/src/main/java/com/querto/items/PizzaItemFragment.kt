package com.querto.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.querto.R
import kotlinx.android.synthetic.main.fragment_pizza_item.view.*


class PizzaItemFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
     var view = inflater.inflate(R.layout.fragment_pizza_item, container, false)



        return view
    }


}