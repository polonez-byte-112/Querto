package com.querto.fragments.home.innerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.innerFragments.DodatkiAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_dodatki.view.*


class DodatkiFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_dodatki, container, false)
        view.recyclerViewDodatki.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        view.recyclerViewDodatki.adapter = DodatkiAdapter(activity!!, mMainActivityViewModel.dodatki_names, mMainActivityViewModel.dodatki_small_price, mMainActivityViewModel.dodatki_medium_price, mMainActivityViewModel.dodatki_big_price)


        return view
    }


}