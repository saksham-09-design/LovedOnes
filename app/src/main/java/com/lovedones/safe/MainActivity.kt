package com.lovedones.safe


import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telephony.SmsManager
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.lovedones.safe.databinding.ActivityMainBinding
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*


class MainActivity : AppCompatActivity() {
    companion object {
        private const val PERMISSION_REQUEST_SEND_SMS = 123
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        emergency()
        addLovedOne()
        about()
        emergencyNumber()
        help()
        safetytips()
    }
    private fun safetytips(){
        val safe = binding.safetytips
        safe.setOnClickListener {
            val intent = Intent(this, safetytips::class.java)
            startActivity(intent)
        }
    }
    private fun help(){
        val hel = binding.help
        hel.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://drive.google.com/file/d/1fF7Qp5r_WczPgUfoNmKB0YGGNHjufVwq/view?usp=sharing"))
            startActivity(intent)
        }
    }
    private fun emergencyNumber(){
        val emNo = binding.emergencyno
        emNo.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),101)
            } else {
                val Intent = Intent(this, EmergencyNumbers::class.java)
                startActivity(Intent)
            }
        }
    }
    private fun addLovedOne(){
        val add = binding.addlovedones
        add.setOnClickListener {
            val intent = Intent(this, NumberAdd::class.java)
            startActivity(intent)
        }
    }
    private fun about(){
        val abt = binding.about
        abt.setOnClickListener {
            val Intent = Intent(this, About::class.java)
            startActivity(Intent)
        }
    }
    private fun emergency(){
        val but = binding.button
        var Notif: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        but.setOnClickListener {
            var messageToSend = "Hey! I am in trouble, Please Call me Immediately."
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || !Notif.isNotificationPolicyAccessGranted) {
                toastShow("Please Give Permissions.")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.SEND_SMS,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_SEND_SMS
                )
                startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
            } else {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){ task ->
                    val location:Location? = task.result
                    val sharedPreference = getSharedPreferences("Number", MODE_PRIVATE)
                    val num1 = sharedPreference.getLong("no1",101).toString()
                    val num2 = sharedPreference.getLong("no2",101).toString()
                    val num3 = sharedPreference.getLong("no3",101).toString()
                    if(location == null){
                        toastShow("Location Turned Off Please Turn ON!")
                        if(binding.messagebox.text.toString() != ""){
                            if(num1 != "101" && num2 != "101" && num3 != "101"){
                                messageToSend = binding.messagebox.text.toString()
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                sendSMS(num3,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 != "101" && num3 == "101"){
                                messageToSend = binding.messagebox.text.toString()
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 == "101" && num3 == "101"){
                                messageToSend = binding.messagebox.text.toString()
                                sendSMS(num1,messageToSend)
                                silent()
                            } else{
                                toastShow("Please Enter Numbers")
                            }
                            binding.messagebox.text = Editable.Factory.getInstance().newEditable("")
                        } else {
                            if(num1 != "101" && num2 != "101" && num3 != "101"){
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                sendSMS(num3,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 != "101" && num3 == "101"){
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 == "101" && num3 == "101"){
                                sendSMS(num1,messageToSend)
                                silent()
                            } else{
                                toastShow("Please Enter Numbers")
                            }
                        }
                    } else{
                        val locLong = location.longitude.toString()
                        val locLat = location.latitude.toString()
                        if(binding.messagebox.text.toString() != ""){
                            if(num1 != "101" && num2 != "101" && num3 != "101"){
                                messageToSend = binding.messagebox.text.toString() + " I am sending my location please help me.\n" +
                                        "https://www.google.com/maps/place/$locLat,$locLong"
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                sendSMS(num3,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 != "101" && num3 == "101"){
                                messageToSend = binding.messagebox.text.toString() + " I am sending my location please help me.\n" +
                                        "https://www.google.com/maps/place/$locLat,$locLong"
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 == "101" && num3 == "101"){
                                messageToSend = binding.messagebox.text.toString() + " I am sending my location please help me.\n" +
                                        "https://www.google.com/maps/place/$locLat,$locLong"
                                sendSMS(num1,messageToSend)
                                silent()
                            } else{
                                toastShow("Please Enter Numbers")
                            }
                            binding.messagebox.text = Editable.Factory.getInstance().newEditable("")
                        } else {
                            if(num1 != "101" && num2 != "101" && num3 != "101"){
                                messageToSend = "Hey! I am in trouble, I am sending my location please help me.\n" +
                                        "https://www.google.com/maps/place/$locLat,$locLong"
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                sendSMS(num3,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 != "101" && num3 == "101"){
                                messageToSend = "Hey! I am in trouble, I am sending my location please help me.\n" +
                                        "https://www.google.com/maps/place/$locLat,$locLong"
                                sendSMS(num1,messageToSend)
                                sendSMS(num2,messageToSend)
                                silent()
                            } else if(num1 != "101" && num2 == "101" && num3 == "101"){
                                messageToSend = "Hey! I am in trouble, I am sending my location please help me.\n" +
                                        "https://www.google.com/maps/place/$locLat,$locLong"
                                sendSMS(num1,messageToSend)
                                silent()
                            } else{
                                toastShow("Please Enter Numbers")
                            }
                        }
                    }
                }
            }
        }
    }
    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            toastShow("SMS sent Success")
        } catch (e: Exception) {
            toastShow("Failed to Send SMS")
            e.printStackTrace()
        }
    }
    private fun toastShow(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
    private fun silent(){
            val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
        toastShow("Phone Set to Silent Mode")
    }
}