package com.querto.fragments.home.innerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.CalzoneAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_calzone.view.*
import kotlinx.android.synthetic.main.fragment_pizza.view.*

class CalzoneFragment : Fragment() {
    private lateinit var mMainActivityViewModel : MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     var view = inflater.inflate(R.layout.fragment_calzone, container, false)


        view.recyclerViewCalzone.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        view.recyclerViewCalzone.adapter = CalzoneAdapter(requireContext(),mMainActivityViewModel.calzone_img, mMainActivityViewModel.calzone_names, mMainActivityViewModel.calzone_desc, mMainActivityViewModel.calzone_price_normal,  mMainActivityViewModel.calzone_price_big )


        return view
    }


}