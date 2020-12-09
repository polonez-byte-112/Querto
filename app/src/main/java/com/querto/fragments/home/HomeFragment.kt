package com.querto.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.querto.R
import com.querto.adapters.ViewPagerAdapter
import com.querto.fragments.home.innerFragments.*

import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view =  inflater.inflate(R.layout.fragment_home, container, false)




        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpTabs()

        super.onViewCreated(view, savedInstanceState)

    }
    private fun setUpTabs() {
        val viewPagerAdapter = ViewPagerAdapter((activity as AppCompatActivity).supportFragmentManager)




        viewLifecycleOwnerLiveData.observe(viewLifecycleOwner, Observer {
            viewPagerAdapter.addFragment(PizzaFragment(), "Pizza")
            viewPagerAdapter.addFragment(FoacciaFragment(), "Foaccia")
            viewPagerAdapter.addFragment(CalzoneFragment(), "Calzone")
            viewPagerAdapter.addFragment(PanuozzoFragment(), "Panuozzo")
            viewPagerAdapter.addFragment(SosyFragment(), "Sosy")
            viewPagerAdapter.addFragment(NapojeFragment(), "Napoje")
            viewPagerAdapter.addFragment(DodatkiFragment(), "Dodatki")

            viewPager_home?.adapter=viewPagerAdapter
            tabLayout?.setupWithViewPager(viewPager_home)
            tabLayout?.getTabAt(0)?.select()
            viewPagerAdapter.notifyDataSetChanged()
        })




    }



}