package com.querto.fragments.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.R
import com.querto.adapters.cart.CartMainAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_calzone.view.*
import kotlinx.android.synthetic.main.fragment_cart_main.view.*

        class CartMainFragment : Fragment() {
            private lateinit var mMainActivityViewModel: MainActivityViewModel
            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                      savedInstanceState: Bundle?): View? {
                // Inflate the layout for this fragment
                var view = inflater.inflate(R.layout.fragment_cart_main, container, false)

                mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                        MainActivityViewModel::class.java)
                view.cart_main_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                view.cart_main_recycler_view.adapter = CartMainAdapter(requireContext(), mMainActivityViewModel.items)

                return view
            }
        }

