package com.querto.fragments.home.innerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.PizzaAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_pizza.view.*

class PizzaFragment : Fragment() {
    private lateinit var mMainActivityViewModel : MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

       val view =  inflater.inflate(R.layout.fragment_pizza, container, false)

        view.recyclerViewPizza.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        //Dodac to jak bedą zdjecia
        //     view.recyclerView.adapter = HomeAdapter(context, mMainActivityViewModel.pizza_names, mMainActivityViewModel.pizza_desc, mMainActivityViewModel.pizza_img )
        view.recyclerViewPizza.adapter = PizzaAdapter(context!!,mMainActivityViewModel.pizza_img, mMainActivityViewModel.pizza_names, mMainActivityViewModel.pizza_desc, mMainActivityViewModel.pizza_small_price, mMainActivityViewModel.pizza_medium_price, mMainActivityViewModel.pizza_big_price )


        return view
    }

}