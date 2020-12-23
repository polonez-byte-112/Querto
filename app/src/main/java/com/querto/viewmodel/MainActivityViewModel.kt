package com.querto.viewmodel


import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.TextView
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
import com.querto.models.Cart.CartItem
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



    var pizza_small_item=0
    var pizza_medium_item=0
    var pizza_big_item=0

    var foaccia_item=0

    var calzone_medium_item=0
    var calzone_big_item=0

    var panuozzo_medium_item=0
    var panuozzo_big_item=0

    var sosy_item=0

    var napoje_one_item=0
    var napoje_two_item=0

    var dodatki_small_item=0
    var dodatki_medium_item=0
    var dodatki_big_item=0


    var dodatki_one_array : Array<String> = application.resources.getStringArray(R.array.dodatki_one_titles)
    var dodatki_two_array : Array<String> = application.resources.getStringArray(R.array.dodatki_two_titles)
    var dodatki_three_array : Array<String> = application.resources.getStringArray(R.array.dodatki_three_titles)
    var dodatki_four_array  : Array<String> = application.resources.getStringArray(R.array.dodatki_four_titles)
    var dodatki_five_array  : Array<String> = application.resources.getStringArray(R.array.dodatki_five_titles)


   val items: ArrayList<CartItem> = arrayListOf()


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

    fun addItem(textView: TextView, kind: String, size: String) { when(kind){

            "pizza"-> {
                when(size){
                    "small"->{
                        pizza_small_item +=1
                    textView.text = pizza_small_item.toString()
                    }
                    "medium"->{
                        pizza_medium_item +=1
                        textView.text= pizza_medium_item.toString()
                    }
                    "big"->{
                        pizza_big_item +=1
                        textView.text=pizza_big_item.toString()
                    }
                }
            }
            "foaccia"->{
                  foaccia_item +=1
                textView.text=foaccia_item.toString()
            }
            "calzone"-> {
                when(size){
                    "medium"->{
                        calzone_medium_item +=1
                        textView.text=calzone_medium_item.toString()
                    }
                    "big"->{
                        calzone_big_item +=1
                        textView.text=calzone_big_item.toString()
                    }
                }
            }
            "panuozzo"-> {
                when(size){
                    "medium"->{
                        panuozzo_medium_item +=1
                        textView.text=panuozzo_medium_item.toString()
                    }
                    "big"->{
                        panuozzo_big_item +=1
                        textView.text=panuozzo_big_item.toString()
                    }
                }
            }
            "sosy"-> {
                sosy_item +=1
                textView.text=sosy_item.toString()
            }
            "napoje"->  {
                when(size){
                    "one"->{
                        napoje_one_item +=1
                        textView.text=napoje_one_item.toString()
                    }

                    "two"->{
                        napoje_two_item +=1
                        textView.text=napoje_two_item.toString()
                    }
                }
            }
            "dodatki"-> {
                when(size){
                    "small"->{
                        dodatki_small_item +=1
                        textView.text=dodatki_small_item.toString()
                    }
                    "medium"->{
                        dodatki_medium_item +=1
                        textView.text=dodatki_medium_item.toString()
                    }
                    "big"->{
                        dodatki_big_item +=1
                        textView.text=dodatki_big_item.toString()
                    }
                }
            }
        } }
    fun removeItem(textView: TextView, kind: String, size: String) { when(kind){

            "pizza"-> {
                when(size){
                    "small"->{
                        if(pizza_small_item>0)
                        pizza_small_item -=1
                        textView.text = pizza_small_item.toString()
                    }
                    "medium"->{
                        if(pizza_medium_item>0)
                        pizza_medium_item -=1
                        textView.text= pizza_medium_item.toString()
                    }
                    "big"->{
                        if(pizza_big_item>0)
                        pizza_big_item -=1
                        textView.text=pizza_big_item.toString()
                    }
                }
            }
            "foaccia"->{
                if(foaccia_item>0)
                foaccia_item -=1
                textView.text=foaccia_item.toString()
            }
            "calzone"-> {
                when(size){
                    "medium"->{
                        if(calzone_medium_item>0)
                        calzone_medium_item -=1
                        textView.text=calzone_medium_item.toString()
                    }
                    "big"->{
                        if(calzone_big_item>0)
                        calzone_big_item -=1
                        textView.text=calzone_big_item.toString()
                    }
                }
            }
            "panuozzo"-> {
                when(size){
                    "medium"->{
                        if(panuozzo_medium_item>0)
                        panuozzo_medium_item -=1
                        textView.text=panuozzo_medium_item.toString()
                    }
                    "big"->{
                        if(panuozzo_big_item>0)
                        panuozzo_big_item -=1
                        textView.text=panuozzo_big_item.toString()
                    }
                }
            }
            "sosy"-> {
                if(sosy_item>0)
                sosy_item -=1
                textView.text=sosy_item.toString()
            }
            "napoje"->  {
                when(size){
                    "one"->{
                        if(napoje_one_item>0)
                        napoje_one_item -=1
                        textView.text=napoje_one_item.toString()
                    }

                    "two"->{
                        if(napoje_two_item>0)
                        napoje_two_item -=1
                        textView.text=napoje_two_item.toString()
                    }
                }
            }
            "dodatki"-> {
                when(size){
                    "small"->{
                        if(dodatki_small_item>0)
                        dodatki_small_item -=1
                        textView.text=dodatki_small_item.toString()
                    }
                    "medium"->{
                        if(dodatki_medium_item>0)
                        dodatki_medium_item -=1
                        textView.text=dodatki_medium_item.toString()
                    }
                    "big"->{
                        if(dodatki_big_item>0)
                        dodatki_big_item -=1
                        textView.text=dodatki_big_item.toString()
                    }
                }
            }
        } }

    fun updateSummary(textView: TextView, itemOne: Int, itemTwo: Int, itemThree: Int, priceOne:Int, priceTwo: Int, priceThree: Int ) {

        var summary =  (itemOne* priceOne)+ (itemTwo*priceTwo)+ (itemThree*priceThree)
        textView.text= summary.toString()

    }
}