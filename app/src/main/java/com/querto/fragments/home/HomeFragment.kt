package com.querto.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.querto.R
import com.querto.adapters.ViewPagerAdapter
import com.querto.fragments.home.innerFragments.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val openFloatingMenu: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim) }
    private val closeFloatingMenu: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim) }
    //By lazy pomaga zaoszczedzic pamiec a wszystko w nawiasie jest równe temu po lewej np openMenu : Animation = AnimationUtils etc

    private var floatingCartClicked = false
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpTabs()


        floatingCartBtn.setOnClickListener {
            onCartClicked()
        }

        floatingClearBtn.setOnClickListener {
            Toast.makeText(context, "Delete order", Toast.LENGTH_SHORT).show()
        }

        floatingContinueBtn.setOnClickListener {
            Toast.makeText(context, "Continue order", Toast.LENGTH_SHORT).show()
        }
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpTabs() {
        //Zamienilem context czy cokolwiek tam bylo na childfragmentmanager. kazdy fragment  to ma <3
        val viewPagerAdapter = ViewPagerAdapter(this.childFragmentManager)
        viewPagerAdapter.addFragment(PizzaFragment(), "Pizza")
        viewPagerAdapter.addFragment(FoacciaFragment(), "Foaccia")
        viewPagerAdapter.addFragment(CalzoneFragment(), "Calzone")
        viewPagerAdapter.addFragment(PanuozzoFragment(), "Panuozzo")
        viewPagerAdapter.addFragment(SosyFragment(), "Sosy")
        viewPagerAdapter.addFragment(NapojeFragment(), "Napoje")
        viewPagerAdapter.addFragment(DodatkiFragment(), "Dodatki")
        viewPager_home?.adapter = viewPagerAdapter
        tabLayout?.setupWithViewPager(viewPager_home)


    }


    private fun onCartClicked() {
        setVisibility(floatingCartClicked)
        setAnimation(floatingCartClicked)

        floatingCartClicked = !floatingCartClicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            floatingClearBtn.startAnimation(openFloatingMenu)
            floatingContinueBtn.startAnimation(openFloatingMenu)
        } else {
            floatingClearBtn.startAnimation(closeFloatingMenu)
            floatingContinueBtn.startAnimation(closeFloatingMenu)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            floatingContinueBtn.visibility = View.VISIBLE
            floatingClearBtn.visibility = View.VISIBLE
        } else {
            floatingContinueBtn.visibility = View.INVISIBLE
            floatingClearBtn.visibility = View.INVISIBLE
        }
    }


}