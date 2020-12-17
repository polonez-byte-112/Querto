package com.querto

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), LifecycleOwner, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    lateinit var user_name : TextView
    lateinit var user_surname: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()


        drawer = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val headerView: View
        headerView= navigationView.getHeaderView(0)

        viewModel.updateUI()

        val img = headerView.findViewById<ImageView>(R.id.header_img)
        user_name = headerView.findViewById(R.id.nav_name)
        user_surname = headerView.findViewById(R.id.nav_surname)






        img.setOnClickListener {
            println("Otwiera profil uzytkownika")
        }

    viewModel.name


        val toogle: ActionBarDrawerToggle
        toogle = ActionBarDrawerToggle(this, drawer, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)
        drawer.setOnClickListener {
            println("\n\n\n\nDziala")
        }

        toogle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.loginFragment).commit()
            navigationView.setCheckedItem(R.id.login)


        }



        /*

         */
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
        val navigationView: NavigationView
        navigationView = findViewById(R.id.nav_view)
        when (item.itemId) {
            R.id.home -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.homeFragment).commit()
            R.id.login -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.loginFragment).commit()
            R.id.register -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.registerFragment).commit()
            R.id.address -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.addressFragment).commit()
            R.id.logout->{
                if(mAuth.currentUser!=null){
                       mAuth.signOut()
                }
                    supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim)?.replace(R.id.fragment_container, mainActivityViewModel.loginFragment)?.commit()
              }

            R.id.email -> mainActivityViewModel.sendMail(this)
            R.id.rate -> {
                navigationView.setCheckedItem(R.id.rate)
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.detailsFragment).commit()
            }
            R.id.share -> mainActivityViewModel.shareApp(this)
        }


        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()

        //Poprawic to jutro.
        //Tak myslalem by poprawiac to czy uzytkownik jest zalogowany

        var viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
        viewModel.name.observe(this,
                Observer {
                    user_name.text = it
                })

        viewModel.surname.observe(this, Observer {
            user_surname.text = it
        })
    }


}