package com.querto.fragments.home.innerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.home.innerFragments.PanuozzoAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_panuozzo.view.*

class PanuozzoFragment : Fragment() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_panuozzo, container, false)


        view.recyclerViewPanuozzo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        view.recyclerViewPanuozzo.adapter = PanuozzoAdapter(activity!!, mMainActivityViewModel.panuozzo_img, mMainActivityViewModel.panuozzo_names, mMainActivityViewModel.panuozzo_desc, mMainActivityViewModel.panuozzo_price_normal, mMainActivityViewModel.panuozzo_price_big)


        return view
    }


}