package com.querto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.querto.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() ,LifecycleOwner, NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawer : DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      var  viewModel =  ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)



        drawer = findViewById(R.id.drawer_layout)

        val navigationView :NavigationView
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)


        val toogle : ActionBarDrawerToggle
        toogle = ActionBarDrawerToggle(this, drawer, findViewById(R.id.toolbar),R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)

        toogle.syncState()

        //Kod powyzej od 21 do 34 jest po to by było menu (hamburger) w rogu

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, viewModel.homeFragment ).commit()
            navigationView.setCheckedItem(R.id.home)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var  view =  ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)

        when(item.itemId){
            R.id.home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, view.homeFragment).commit()
            R.id.login-> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, view.loginFragment).commit()
            R.id.register-> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, view.registerFragment).commit()

            R.id.email-> view.sendMail(this)
            R.id.rate -> view.openStore(this)
            R.id.share -> view.shareApp(this)
        }


        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}