package com.lovedones.safe

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.telephony.SmsManager
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class EmBut : AppWidgetProvider() {

    companion object {
        const val ACTION_BUTTON_CLICK = "app.com.lovedones.safe.ACTION_BUTTON_CLICK"
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->
            val remoteViews = RemoteViews(context.packageName, R.layout.em_but)
            val intent = Intent(context, EmBut::class.java)
            intent.action = ACTION_BUTTON_CLICK
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_IMMUTABLE)
            remoteViews.setOnClickPendingIntent(R.id.btclk, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == ACTION_BUTTON_CLICK) {
            performTask(context)
        }
    }

    private fun performTask(context: Context) {
        val sharedPreference = context.getSharedPreferences("Number", AppCompatActivity.MODE_PRIVATE)
        val num1 = sharedPreference.getLong("no1",101).toString()
        val num2 = sharedPreference.getLong("no2",101).toString()
        val num3 = sharedPreference.getLong("no3",101).toString()
        val messageToSend = "Hey I am in Trouble, please Call me!"
        if(num1 != "101" && num2 != "101" && num3 != "101"){
            sendSMS(num1,messageToSend)
            sendSMS(num2,messageToSend)
            sendSMS(num3,messageToSend)
            sendNotif(context,"SMS Sent!","Successfully Sent the SMS.")
        } else if(num1 != "101" && num2 != "101" && num3 == "101"){
            sendSMS(num1,messageToSend)
            sendSMS(num2,messageToSend)
            sendNotif(context,"SMS Sent!","Successfully Sent the SMS.")
        } else if(num1 != "101" && num2 == "101" && num3 == "101"){
            sendSMS(num1,messageToSend)
            sendNotif(context,"SMS Sent!","Successfully Sent the SMS.")
        } else{
            sendNotif(context,"Please Add Numbers!","Click To Add.")
        }
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun sendNotif(context: Context, tittle: String, desc: String){
        var intent = Intent(context, MainActivity::class.java)
        if (tittle == "Please Add Numbers!"){
            intent = Intent(context, NumberAdd::class.java)
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = NotificationCompat.Builder(context, "default")
            .setSmallIcon(R.drawable.logor)
            .setContentTitle(tittle)
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Channel Description"
            channel.enableLights(true)
            channel.lightColor = Color.BLUE
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }
}
