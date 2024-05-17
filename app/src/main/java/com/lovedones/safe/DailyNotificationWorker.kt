package com.lovedones.safe

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class DailyNotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val safetyTips = arrayOf(
            "Stay aware of your surroundings.",
            "Trust your instincts always.",
            "Keep emergency contacts handy.",
            "Avoid isolated areas at night.",
            "Share your location with trusted friends.",
            "Carry a whistle for emergencies.",
            "Walk confidently and purposefully.",
            "Keep your phone fully charged.",
            "Avoid using headphones in unfamiliar areas.",
            "Take self-defense classes.",
            "Let someone know your whereabouts.",
            "Be cautious when using ATMs.",
            "Park in well-lit areas.",
            "Lock car doors immediately after entering.",
            "Check the backseat before entering the car.",
            "Use apps to track your location.",
            "Avoid sharing personal details with strangers.",
            "Change your routine frequently.",
            "Keep your home well-lit at night.",
            "Install security systems at home.",
            "Have a safe word with family.",
            "Avoid oversharing on social media.",
            "Use strong, unique passwords.",
            "Verify rideshare details before entering.",
            "Don’t accept drinks from strangers.",
            "Stay alert in parking garages.",
            "Keep personal items close.",
            "Trust your gut feeling.",
            "Have an escape plan ready.",
            "Avoid walking alone at night.",
            "Know the emergency exits.",
            "Stay in well-populated areas.",
            "Never take drinks from unknown person.",
            "Carry a personal safety alarm.",
            "Dress comfortably and practically.",
            "Stay vigilant at public events.",
            "Avoid shortcuts through unknown areas.",
            "Report suspicious activities immediately.",
            "Don’t reveal your travel plans publicly.",
            "Be aware of local emergency services.",
            "Use mirrors to see behind you.",
            "Limit alcohol intake in public.",
            "Avoid isolated rest stops.",
            "Stay near exits in public places.",
            "Carry a portable charger.",
            "Take care when using public transport.",
            "Keep a low profile in new places.",
            "Always lock your doors and windows.",
            "Learn basic first aid.",
            "Keep a list of emergency numbers.",
            "Keep your keys in hand when walking.",
            "Install a reliable safety app.",
            "Avoid secluded places when alone.",
            "Inform someone before taking a taxi.",
            "Stay in well-lit streets at night.",
            "Lock your doors immediately after entering.",
            "Use buddy system in unfamiliar areas.",
            "Avoid distractions while walking.",
            "Don’t share travel plans with strangers.",
            "Have emergency cash accessible.",
            "Check in regularly with loved ones.",
            "Learn and use assertive body language.",
            "Keep emergency whistle on your keychain.",
            "Use only trusted ride-sharing services.",
            "Be cautious when strangers ask for help.",
            "Stay close to exits in crowded places.",
            "Avoid revealing personal info online.",
            "Walk with confidence and purpose.",
            "Monitor your drink at social events.",
            "Change your locks when moving house.",
            "Plan your route before leaving.",
            "Avoid walking with your phone out.",
            "Do not let strangers into your home.",
            "Vary your daily routine patterns.",
            "Trust only verified online connections.",
            "Learn basic self-defense moves.",
            "Secure windows and doors at night.",
            "Stay sober and aware in new places.",
            "Keep your bag close and zipped.",
            "Avoid shortcuts through alleys.",
            "Have a backup power bank handy.",
            "Stay alert in unfamiliar surroundings.",
            "Use personal alarms for added safety.",
            "Avoid isolated public restrooms.",
            "Keep vehicle doors locked at all times.",
            "Wear practical, comfortable footwear.",
            "Stay informed about local crime trends.",
            "Update someone on your expected return.",
            "Keep your head up and look around.",
            "Secure your home with good lighting.",
            "Be cautious when using public Wi-Fi.",
            "Avoid oversharing your location online.",
            "Listen to your intuition about danger.",
            "Have a safety plan when traveling.",
            "Stay alert in parking lots and garages.",
            "Report suspicious behavior immediately.",
            "Carry identification and medical info.",
            "Be aware of your surroundings always.",
            "Avoid talking to strangers in secluded areas.",
            "Keep pepper spray within reach."
        )
        val tip = safetyTips.random()
        sendNotification("Safety Tip", tip)
        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the notification channel (for API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "DAILY_TIP_CHANNEL"
            val channelName = "Daily Tips"
            val channelDescription = "Channel for daily safety tips"
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)

        }
        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Ensure compatibility with Android 12+
        )
        // Create the notification
        val notification = NotificationCompat.Builder(applicationContext, "DAILY_TIP_CHANNEL")
            .setSmallIcon(R.drawable.logor)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Notify
        notificationManager.notify(1, notification)
    }
}
