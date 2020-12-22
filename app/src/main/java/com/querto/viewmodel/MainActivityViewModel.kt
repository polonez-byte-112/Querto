package com.querto.viewmodel


import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.querto.R
import com.querto.fragments.account.AccountFragment
import com.querto.fragments.account.EditAccountFragment
import com.querto.fragments.address.AddAddressFragment
import com.querto.fragments.address.AddressFragment
import com.querto.fragments.details.DetailsFragment
import com.querto.fragments.home.HomeFragment
import com.querto.fragments.login.LoginFragment
import com.querto.fragments.register.RegisterFragment
import com.querto.items.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    val homeFragment = HomeFragment()
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()
    val detailsFragment = DetailsFragment()
    val addressFragment = AddressFragment()
    val addAddressFragment = AddAddressFragment()
    val accountFragment = AccountFragment()
    val editAccountFragment = EditAccountFragment()

    //Items

    val pizzaItem = PizzaItemFragment()
    val calzoneItem = CalzoneItemFragment()
    val dodatkiItem = DodatkiItemFragment()
    val foacciaItem = FoacciaItemFragment()
    val napojeItem = NapojeItemFragment()
    val panuozzoItem = PanuozzoItemFragment()
    val sosyItem = SosyItemFragment()


    var pizza_names: Array<String> = application.resources.getStringArray(R.array.pizza_titles)
    var pizza_desc: Array<String> = application.resources.getStringArray(R.array.pizza_desc)
    val pizza_small_price: IntArray = application.resources.getIntArray(R.array.pizza_small_price)
    val pizza_medium_price: IntArray = application.resources.getIntArray(R.array.pizza_medium_price)
    val pizza_big_price: IntArray = application.resources.getIntArray(R.array.pizza_big_price)
    var pizza_img: Array<Int> = arrayOf(R.drawable.napoletana, R.drawable.margherita, R.drawable.estate, R.drawable.pepperone, R.drawable.pancetta, R.drawable.ortolana, R.drawable.marinara, R.drawable.diavola, R.drawable.messicana, R.drawable.quattro_formaggi, R.drawable.sugoza, R.drawable.semola, R.drawable.capriciossa, R.drawable.vulcano, R.drawable.romana, R.drawable.capodanno, R.drawable.primavera, R.drawable.regina, R.drawable.quattro_stagioni, R.drawable.cilento, R.drawable.tirolese, R.drawable.michele, R.drawable.pollo, R.drawable.havana, R.drawable.siciliana, R.drawable.sandra, R.drawable.bari, R.drawable.gringo, R.drawable.angelo, R.drawable.spinaci)


    var focaccia_names: Array<String> = application.resources.getStringArray(R.array.foaccia_titles)
    var focaccia_desc: Array<String> = application.resources.getStringArray(R.array.foaccia_desc)
    val focaccia_price: IntArray = application.resources.getIntArray(R.array.foaccia_price)
    var focaccia_img: Array<Int> = arrayOf(R.drawable.base, R.drawable.nutella)


    var calzone_names: Array<String> = application.resources.getStringArray(R.array.calzone_titles)
    var calzone_desc: Array<String> = application.resources.getStringArray(R.array.calzone_desc)
    val calzone_price_normal: IntArray = application.resources.getIntArray(R.array.calzone_normal_price)
    val calzone_price_big: IntArray = application.resources.getIntArray(R.array.calzone_big_price)
    var calzone_img: Array<Int> = arrayOf(R.drawable.calzone)


    var panuozzo_names: Array<String> = application.resources.getStringArray(R.array.panuozzo_titles)
    var panuozzo_desc: Array<String> = application.resources.getStringArray(R.array.panuozzo_desc)
    val panuozzo_price_normal: IntArray = application.resources.getIntArray(R.array.panuozzo_normal_price)
    val panuozzo_price_big: IntArray = application.resources.getIntArray(R.array.panuozzo_big_price)
    var panuozzo_img: Array<Int> = arrayOf(R.drawable.panuozzo)

    val sosy_names: Array<String> = application.resources.getStringArray(R.array.sosy_titles)
    val sosy_price: IntArray = application.resources.getIntArray(R.array.sosy_price)

    val napoje_names: Array<String> = application.resources.getStringArray(R.array.napoje_titles)
    val napoje_price: IntArray = application.resources.getIntArray(R.array.napoje_price)
    val napoje_first_kind: Array<String> = application.resources.getStringArray(R.array.napoje_kinds_one)
    val napoje_second_kind: Array<String> = application.resources.getStringArray(R.array.napoje_kinds_two)

    val dodatki_names: Array<String> = application.resources.getStringArray(R.array.dodatki_titles)
    val dodatki_small_price: IntArray = application.resources.getIntArray(R.array.dodatki_small_price)
    val dodatki_medium_price: IntArray = application.resources.getIntArray(R.array.dodatki_medium_price)
    val dodatki_big_price: IntArray = application.resources.getIntArray(R.array.dodatki_big_price)

    var contactName: String? = ""
    var contactSurname : String?=""
    var contactFullAddress: String? =""



    private val mutableLoginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = mutableLoginStatus


    fun checkLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            database = FirebaseDatabase.getInstance().reference
            mAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        mutableLoginStatus.postValue(true)
                    }else{
                        mutableLoginStatus.postValue(false)
                    }
                }
        }
    }
    fun openFacebook(context: Context) {

        val openURL = Intent(Intent.ACTION_VIEW)

        openURL.data = Uri.parse("https://www.facebook.com/1488596184507308/")
        context.startActivity(openURL)


    }
    fun sendMail(context: Context) {
        val sendEmail = Intent(Intent.ACTION_SEND)
        val email: Array<String> = arrayOf("kontakt@cilento.pl")
        sendEmail.data = Uri.parse("mailto: kontakt@cilento.pl ")
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Problem z Usługą")



        database = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {


            database.child("users").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {


                    if (snapshot.exists()) {
                        println("User found")
                       contactName = snapshot.child("name").value.toString()
                       contactSurname = snapshot.child("surname").value.toString()

                        database.child("addresses").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    println("Address found")
                                    contactFullAddress=snapshot.child("street").value.toString() +"  "+snapshot.child("house_number").value.toString()+",  "+ snapshot.child("postcode").value.toString()+",  "+ snapshot.child("city_name").value.toString()
                                } else {
                                    println("Address not found ")
                                    contactFullAddress = "Not found"
                                }

                                sendEmail.putExtra(Intent.EXTRA_TEXT, "Pizza którą zamówiłem nie przyszła na czas.\n\n\nMoje Dane Kontaktowe: \nImię: $contactName \nNazwisko: $contactSurname \nAdress:\n$contactFullAddress  ")
                                sendEmail.type = "message/rfc822"
                                sendEmail.putExtra(Intent.EXTRA_EMAIL, email)
                                val chooser = Intent.createChooser(sendEmail, "Send mail using")
                                context.startActivity(chooser)
                            }

                            override fun onCancelled(error: DatabaseError) {
                                println(error.message)
                            }
                        })
                    } else {
                        println("User not found in")
                        contactName = "Guest"
                        contactSurname = " "
                        contactFullAddress = "Not found"
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    println(error.message)
                }
            })
        } else {
            println("User not signed in")
            contactName = "Guest"
            contactSurname = ""
            contactFullAddress = "Not found"

            sendEmail.putExtra(Intent.EXTRA_TEXT, "Pizza którą zamówiłem nie przyszła na czas.\n\n\nMoje Dane Kontaktowe: \nImię: $contactName \nNazwisko: $contactSurname \nAdress: $contactFullAddress  ")
            sendEmail.type = "message/rfc822"
            sendEmail.putExtra(Intent.EXTRA_EMAIL, email)
            val chooser = Intent.createChooser(sendEmail, "Send mail using")
            context.startActivity(chooser)
        }
    }


}