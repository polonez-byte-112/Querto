package com.querto

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.fragments.cart.CartMainFragment
import com.querto.models.Cart.CartItem
import com.querto.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), LifecycleOwner, NavigationView.OnNavigationItemSelectedListener {

    var isCart_Address : Boolean = false as Boolean
    private lateinit var drawer: DrawerLayout
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    lateinit var viewModel: MainActivityViewModel
    lateinit var user_name: TextView
    lateinit var user_surname: TextView

    val mutable_surname = MutableLiveData<String>()
    val surname: LiveData<String>
        get() = mutable_surname


    var DETALE_STATUS=0
    var HOME_STATUS=0
    var PROFIL_STATUS=0
    var EDIT_PROFIL_STATUS=0
    var ADDRESS_STATUS=0
    var EDIT_ADDRESS_STATUS=0
    var CURRENT_ITEM_STATUS=0
    var LOGIN_STATUS=0
    var REGISTER_STATUS=0
    var CART_MAIN_STATUS=0
    var CART_ADDRESS_STATUS=0
    var CART_SUMMARY_STATUS=0
    var EXTRA_STATUS=0
    var items: ArrayList<CartItem> = arrayListOf()
    var summarry = MutableLiveData<Int>()
    //List of addings for each pizza
   var mySummaryItemListName: MutableList<String> = mutableListOf()
    var mySummaryItemListAmountAndPrice: MutableList<String> = mutableListOf()
    init {
        summarry.postValue(0)
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()


        drawer = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView
        navigationView = findViewById(R.id.nav_view)
        navigationView.itemIconTintList=null
        navigationView.setNavigationItemSelectedListener(this)

        val headerView: View
        headerView = navigationView.getHeaderView(0)

        user_name = headerView.findViewById(R.id.nav_name)
        user_surname = headerView.findViewById(R.id.nav_surname)





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
       val navigationView: NavigationView
        navigationView = findViewById(R.id.nav_view)
        when (item.itemId) {
            R.id.home -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
            R.id.login -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.loginFragment).commit()
            R.id.account-> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.accountFragment).commit()
            R.id.address -> supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.addressFragment).commit()
            R.id.logout -> {
                if (mAuth.currentUser != null) {
                    mAuth.signOut()
                    updateUI()
                }
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.loginFragment).commit()
            }
            R.id.email -> viewModel.sendMail(this)
            R.id.detail -> {
                navigationView.setCheckedItem(R.id.detail)
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.detailsFragment).commit()
            }
            R.id.facebook -> viewModel.openFacebook(this)
        }


        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {

            if(HOME_STATUS==1 || LOGIN_STATUS==1){
            HOME_STATUS=0
            LOGIN_STATUS=0
            super.onBackPressed()
        }
                if(DETALE_STATUS==1){
                    DETALE_STATUS=0
                    supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
                    HOME_STATUS=1
                }


            if(PROFIL_STATUS==1){
                PROFIL_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
                HOME_STATUS=1
            }

            if(ADDRESS_STATUS==1){
                ADDRESS_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
                HOME_STATUS=1
            }


            if(CURRENT_ITEM_STATUS==1){
                CURRENT_ITEM_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
                HOME_STATUS=1
            }

            if(CART_MAIN_STATUS==1){
                CART_MAIN_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.homeFragment).commit()
                HOME_STATUS=1
            }

            if(EXTRA_STATUS==1){
                EXTRA_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, CartMainFragment()).commit()
                CART_MAIN_STATUS=1
            }

            if(CART_ADDRESS_STATUS==1){
                CART_ADDRESS_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, CartMainFragment()).commit()
                CART_MAIN_STATUS=1
            }


            if(CART_SUMMARY_STATUS==1){
                CART_SUMMARY_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, CartMainFragment()).commit()
                CART_MAIN_STATUS=1
            }

            if(REGISTER_STATUS==1){
                REGISTER_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.loginFragment).commit()
                LOGIN_STATUS=1
            }


            if(EDIT_PROFIL_STATUS==1){
                EDIT_PROFIL_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.accountFragment).commit()
                PROFIL_STATUS=1
            }


            if(EDIT_ADDRESS_STATUS==1){
                EDIT_ADDRESS_STATUS=0
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fragment_slide_in_anim, R.anim.fragment_fade_out_anim, R.anim.fragment_slide_out_anim, R.anim.fragment_fade_in_anim).replace(R.id.fragment_container, viewModel.addressFragment).commit()
                ADDRESS_STATUS=1
            }



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
                        mutable_surname.postValue(snapshot.child("name").value.toString()+" "+snapshot.child("surname").value.toString())
                    } else {
                        println("User not found")
                        mutable_surname.postValue("Gość")
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    println(error.message)
                }
            })

            val navigationView: NavigationView
            navigationView = findViewById(R.id.nav_view)
            val nav_menu  = navigationView.menu
            nav_menu.findItem(R.id.account).isVisible = true
            nav_menu.findItem(R.id.address).isVisible = true
            nav_menu.findItem(R.id.logout).isVisible = true
            nav_menu.findItem(R.id.login).isVisible = false
        } else {

            println("User not signed in")
            mutable_surname.postValue("Gość")


            val navigationView: NavigationView
            navigationView = findViewById(R.id.nav_view)
            val nav_menu  = navigationView.menu
            nav_menu.findItem(R.id.account).isVisible = false
            nav_menu.findItem(R.id.address).isVisible = false
            nav_menu.findItem(R.id.logout).isVisible = false
            nav_menu.findItem(R.id.login).isVisible = true
        }



        surname.observe(this, {
            user_surname.text = it.toString()
        })





    }

    fun updateSummary(summaryText: TextView, item: CartItem){
        println("We are updating summary!")
        mySummaryItemListName.clear()
        mySummaryItemListAmountAndPrice.clear()

        item.i_sosy.forEach {

            if(!(it.s_name=="" && it.s_price=="" && it.s_amount=="0")){
                println("\n---------------------------------------\nName: ${it.s_name}\nPrice: ${it.s_price}\nAmount: ${it.s_amount}\n---------------------------------------\n")
               mySummaryItemListName.add(it.s_name)
               mySummaryItemListAmountAndPrice.add(it.s_amount+" x "+it.s_price+" zł")

            }else{
               mySummaryItemListName.remove(it.s_name)
               mySummaryItemListAmountAndPrice.remove(it.s_amount+" x "+it.s_price+" zł")
            }
        }

        item.i_dodatki.forEach {

            if(!(it.d_name=="" && it.d_price=="" && it.d_amount=="0")){
                println("\n---------------------------------------\nName: ${it.d_name}\nPrice: ${it.d_price}\nAmount: ${it.d_amount}\n---------------------------------------\n")
                mySummaryItemListName.add(it.d_name)
                mySummaryItemListAmountAndPrice.add(it.d_amount+" x "+it.d_price+" zł")


            }else{
                mySummaryItemListName.remove(it.d_name)
                mySummaryItemListAmountAndPrice.remove(it.d_amount+" x "+it.d_price+" zł")
            }
        }
        var local_text=""
       mySummaryItemListName.forEachIndexed { index, s ->
            local_text =local_text +"\n"+ mySummaryItemListName[index]+" "+mySummaryItemListAmountAndPrice[index]+"\n"
        }

        summaryText.text = local_text

    }
}