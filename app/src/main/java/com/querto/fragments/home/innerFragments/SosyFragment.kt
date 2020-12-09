package com.querto.fragments.home.innerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.PanuozzoAdapter
import com.querto.adapters.SosyAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_panuozzo.view.*
import kotlinx.android.synthetic.main.fragment_sosy.view.*


class SosyFragment : Fragment() {
    private lateinit var mMainActivityViewModel : MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view= inflater.inflate(R.layout.fragment_sosy, container, false)


        view.recyclerViewSosy.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!).create(MainActivityViewModel::class.java)

        view.recyclerViewSosy.adapter = SosyAdapter(requireContext(), mMainActivityViewModel.sosy_names,  mMainActivityViewModel.sosy_price)

        return view
    }


}