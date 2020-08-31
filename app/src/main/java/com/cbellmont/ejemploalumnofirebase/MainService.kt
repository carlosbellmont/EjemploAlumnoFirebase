package com.cbellmont.ejemploalumnofirebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MainService : FirebaseMessagingService() {

    private val TAG = "MainService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Contenido del mensaje: ${remoteMessage.data}")
        }

        remoteMessage.notification?.body?.let {
            Log.d(TAG, "Cuerpo del mensaje: $it")
            //sendNotification(it)
        }
    }
}