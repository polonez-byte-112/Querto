package com.querto

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), LifecycleOwner, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    lateinit var user_name: TextView
    lateinit var user_surname: TextView
    val mutable_name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = mutable_name

    val mutable_surname = MutableLiveData<String>()
    val surname: LiveData<String>
        get() = mutable_surname
    var loginOpen: Int? = null



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
        headerView = navigationView.getHeaderView(0)

        val img = headerView.findViewById<ImageView>(R.id.header_img)
        user_name = headerView.findViewById(R.id.nav_name)
        user_surname = headerView.findViewById(R.id.nav_surname)






        img.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.accountFragment).commit()
        }


        val toogle: ActionBarDrawerToggle
        toogle = ActionBarDrawerToggle(this, drawer, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)

        toogle.syncState()

        if (savedInstanceState == null) {

            if(mAuth.currentUser!=null){
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
                navigationView.setCheckedItem(R.id.home)
            }else{
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.loginFragment).commit()
                navigationView.setCheckedItem(R.id.login)
            }


        }




        updateUI()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
        val navigationView: NavigationView
        navigationView = findViewById(R.id.nav_view)
        when (item.itemId) {
            R.id.home -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.homeFragment).commit()
            R.id.login -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.loginFragment).commit()
            R.id.account-> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.accountFragment).commit()
            R.id.address -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.addressFragment).commit()
            R.id.logout -> {
                if (mAuth.currentUser != null) {
                    mAuth.signOut()
                    updateUI()
                }
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.loginFragment).commit()
            }
            R.id.email -> mainActivityViewModel.sendMail(this)
            R.id.detail -> {
                navigationView.setCheckedItem(R.id.detail)
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, mainActivityViewModel.detailsFragment).commit()
            }
            R.id.facebook -> mainActivityViewModel.openFacebook(this)
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
    fun updateUI() {

        println("Updating UI")
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()


        if (mAuth.currentUser != null) {


            database.child("users").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        println("User found")
                        mutable_name.postValue(snapshot.child("name").value.toString())
                        mutable_surname.postValue(snapshot.child("surname").value.toString())
                    } else {
                        println("User not found")
                        mutable_name.postValue("Guest")
                        mutable_surname.postValue("")
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    println(error.message)
                }
            })

            val navigationView: NavigationView
            navigationView = findViewById(R.id.nav_view)
            val nav_menu  = navigationView.menu
            nav_menu.findItem(R.id.account).setVisible(true)
            nav_menu.findItem(R.id.address).setVisible(true)
            nav_menu.findItem(R.id.logout).setVisible(true)
            nav_menu.findItem(R.id.login).setVisible(false)
        } else {
            println("User not signed in")
            mutable_name.postValue("Guest")
            mutable_surname.postValue("")


            val navigationView: NavigationView
            navigationView = findViewById(R.id.nav_view)
            val nav_menu  = navigationView.menu
            nav_menu.findItem(R.id.account).setVisible(false)
            nav_menu.findItem(R.id.address).setVisible(false)
            nav_menu.findItem(R.id.logout).setVisible(false)
            nav_menu.findItem(R.id.login).setVisible(true)
        }

        name.observe(this, {
            user_name.text = it.toString()
        })

        surname.observe(this, {
            user_surname.text = it.toString()
        })





    }


}