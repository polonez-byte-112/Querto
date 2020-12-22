package com.querto.fragments.home.innerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.FoacciaAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_foaccia.view.*


class FoacciaFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_foaccia, container, false)


        view.recyclerViewFoaccia.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        view.recyclerViewFoaccia.adapter = FoacciaAdapter(activity!!, mMainActivityViewModel.focaccia_img, mMainActivityViewModel.focaccia_names, mMainActivityViewModel.focaccia_desc, mMainActivityViewModel.focaccia_price)


        return view
    }


}