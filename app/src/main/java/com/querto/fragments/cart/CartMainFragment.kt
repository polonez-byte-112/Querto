package com.querto.fragments.cart

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.querto.MainActivity
import com.querto.R
import com.querto.adapters.cart.CartMainAdapter
import com.querto.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_calzone.view.*
import kotlinx.android.synthetic.main.fragment_cart_main.*
import kotlinx.android.synthetic.main.fragment_cart_main.view.*
import kotlin.properties.Delegates

class CartMainFragment : Fragment() {
            private lateinit var mMainActivityViewModel: MainActivityViewModel
            lateinit var cartMainAdapter : CartMainAdapter
            var recyclerViewState: Parcelable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                      savedInstanceState: Bundle?): View? {
                // Inflate the layout for this fragment
               var  view = inflater.inflate(R.layout.fragment_cart_main, container, false)
                mMainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
                        MainActivityViewModel::class.java)
                view.cart_main_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                cartMainAdapter= CartMainAdapter((activity as MainActivity), (activity as MainActivity).items)
                view.cart_main_recycler_view.adapter = cartMainAdapter
                (activity as MainActivity).CART_MAIN_STATUS=1

        (activity as MainActivity).summarry.value=0
                (activity as MainActivity).items.forEach {
                    (activity as MainActivity).summarry.value = (activity as MainActivity).summarry.value?.plus(Integer.parseInt(it.i_amount) * Integer.parseInt(it.i_price))
                }


        (activity as MainActivity).summarry.observe(viewLifecycleOwner, Observer {
            view.summared_price.text=it.toString()
        })



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


                if((activity as MainActivity).items.size==0){
                    cart_no_items_text.visibility = View.VISIBLE
                    cart_main_recycler_view.visibility = View.GONE
                    summary_box.visibility=View.GONE
                }else{
                    cart_no_items_text.visibility = View.GONE
                    cart_main_recycler_view.visibility = View.VISIBLE
                    summary_box.visibility=View.VISIBLE
                }

            }


            override fun onDestroyView() {
                super.onDestroyView()
                (activity as MainActivity).CART_MAIN_STATUS=0
            }




        }

