package com.querto.viewmodel


import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.querto.R
import com.querto.data.UserDatabase
import com.querto.fragments.details.DetailsFragment
import com.querto.fragments.home.HomeFragment
import com.querto.fragments.login.LoginFragment
import com.querto.fragments.register.RegisterFragment
import com.querto.model.User
import com.querto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val homeFragment = HomeFragment()
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()
    val detailsFragment = DetailsFragment()


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


    private val mutableLoginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = mutableLoginStatus


    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDataBase(application).userDao()
        repository = UserRepository(userDao)


    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    /**
     * Function is to validate provided user on login page
     */
    fun checkLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loginUser(username, password)?.let {
                mutableLoginStatus.postValue(true)

            } ?: mutableLoginStatus.postValue(false)
        }
    }

    fun shareApp(context: Context) {

        val openURL = Intent(android.content.Intent.ACTION_VIEW)

        openURL.data = Uri.parse("https://www.facebook.com/1488596184507308/")
        context.startActivity(openURL)


    }


    fun openStore(context: Context) {


    }


    fun sendMail(context: Context) {
        val sendEmail = Intent(Intent.ACTION_SEND)
        val email: Array<String> = arrayOf("kontakt@cilento.pl")
        sendEmail.setData(Uri.parse("mailto: kontakt@cilento.pl "))
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Problem z Usługą")
        sendEmail.putExtra(Intent.EXTRA_TEXT, "Pizza którą zamówiłem nie przyszła na czas.\n\n\nMoje Dane Kontaktowe: \n\nImie: \nNazwisko: \nAdres: ")
        sendEmail.setType("message/rfc822")
        sendEmail.putExtra(Intent.EXTRA_EMAIL, email)
        val chooser = Intent.createChooser(sendEmail, "Send mail using")
        context.startActivity(chooser)
    }

    /*
       fun createNotification(context: Context){
           if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
               val channel: NotificationChannel = NotificationChannel(1.toString(), "Title", NotificationManager.IMPORTANCE_DEFAULT).apply {
                   description = "Notifaction here : D "
               }

               val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
           }
       }


       fun sendNotification(context: Context){
           var builder = NotificationCompat.Builder(context, 1.toString())
               .setSmallIcon(R.drawable.ic_person_clicked)
               .setContentTitle("Cilento").setContentText("Example desc")
               .setPriority(NotificationCompat.PRIORITY_DEFAULT)


           with(NotificationManagerCompat.from(context)){
               notify(notifcat)
           }
       }
     */
}