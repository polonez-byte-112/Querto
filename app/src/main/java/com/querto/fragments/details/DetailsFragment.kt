package com.querto.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.querto.MainActivity
import com.querto.R
import com.querto.viewmodel.MainActivityViewModel


class DetailsFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_details, container, false)
        (activity as MainActivity).DETALE_STATUS=1
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).DETALE_STATUS=0
    }




}