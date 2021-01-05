package com.querto.extra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.extra.extra_dodatki.ExtraDodatkiAdapter
import com.querto.adapters.extra.extra_sosy.ExtraSosyAdapter
import com.querto.adapters.extra.extra_summary.ExtraSummaryAdapter
import com.querto.fragments.cart.CartMainFragment
import com.querto.models.Cart.CartItem
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_extra.view.*

class ExtraFragment(val activity: MainActivity,val  item: CartItem) : Fragment() {
    lateinit var mMainActivityViewModel: MainActivityViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_extra, container, false)
      activity.EXTRA_STATUS=1
        mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                MainActivityViewModel::class.java)

        var extraSosyAdapter=  ExtraSosyAdapter(activity,mMainActivityViewModel.sosy_names, mMainActivityViewModel.sosy_price,item)
        var extraDodatkiAdapter = ExtraDodatkiAdapter(activity, mMainActivityViewModel.dodatki_names, mMainActivityViewModel.dodatki_small_price, mMainActivityViewModel.dodatki_medium_price, mMainActivityViewModel.dodatki_big_price , item)
        var extraSummaryAdapter = ExtraSummaryAdapter(activity, item)
        view.extra_sosy_recycler_view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        view.extra_sosy_recycler_view.adapter = extraSosyAdapter


        view.extra_dodatki_recycler_view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        view.extra_dodatki_recycler_view.adapter = extraDodatkiAdapter


        view.extra_summary_recycler_view.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.extra_summary_recycler_view.adapter =extraSummaryAdapter

        view.extra_continue_btn.setOnClickListener {

          activity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, CartMainFragment()).commit()
        }
        return view
    }


  override fun onDestroyView() {
    super.onDestroyView()
    activity.EXTRA_STATUS=0
  }
}