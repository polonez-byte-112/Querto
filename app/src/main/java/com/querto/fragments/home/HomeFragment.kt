package com.querto.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.PizzaAdapter
import com.querto.adapters.ViewPagerAdapter
import com.querto.fragments.home.innerFragments.*
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var mMainActivityViewModel : MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_home, container, false)

        setUpTabs()

        return view


        /* Stara wersja
               view.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        //Dodac to jak bedą zdjecia
   //     view.recyclerView.adapter = HomeAdapter(context, mMainActivityViewModel.pizza_names, mMainActivityViewModel.pizza_desc, mMainActivityViewModel.pizza_img )
     view.recyclerView.adapter = PizzaAdapter(context!!,mMainActivityViewModel.pizza_img, mMainActivityViewModel.pizza_names, mMainActivityViewModel.pizza_desc, mMainActivityViewModel.pizza_small_price, mMainActivityViewModel.pizza_medium_price, mMainActivityViewModel.pizza_big_price )

         */
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter((activity as AppCompatActivity).supportFragmentManager)

        adapter.addFragment(PizzaFragment(), "Pizza")
        adapter.addFragment(FoacciaFragment(), "Foaccia")
        adapter.addFragment(CalzoneFragment(), "Calzone")
        adapter.addFragment(PanuozzoFragment(), "Panuozzo")
        adapter.addFragment(SosyFragment(), "Sosy")
        adapter.addFragment(NapojeFragment(), "Napoje")

        viewPager_home.adapter=adapter
        tabLayout.setupWithViewPager(viewPager_home)
    }


}