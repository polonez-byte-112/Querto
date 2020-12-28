package com.querto.fragments.cart

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.cart.CartMainAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_calzone.view.*
import kotlinx.android.synthetic.main.fragment_cart_main.*
import kotlinx.android.synthetic.main.fragment_cart_main.view.*

        class CartMainFragment : Fragment() {
            private lateinit var mMainActivityViewModel: MainActivityViewModel
            lateinit var cartMainAdapter : CartMainAdapter
            var recyclerViewState: Parcelable? = null

            override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                      savedInstanceState: Bundle?): View? {
                // Inflate the layout for this fragment
                var view = inflater.inflate(R.layout.fragment_cart_main, container, false)

                mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                        MainActivityViewModel::class.java)
                view.cart_main_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                cartMainAdapter= CartMainAdapter((activity as MainActivity), (activity as MainActivity).items)
                view.cart_main_recycler_view.adapter = cartMainAdapter

                return view
            }



            override fun onPause() {
                super.onPause()
                recyclerViewState = cart_main_recycler_view.layoutManager?.onSaveInstanceState()
            }

            override fun onStart() {
                super.onStart()
                recyclerViewState?.let{
                    cart_main_recycler_view.adapter = cartMainAdapter
                    cart_main_recycler_view.layoutManager?.onRestoreInstanceState(it)
                }

            }
        }

