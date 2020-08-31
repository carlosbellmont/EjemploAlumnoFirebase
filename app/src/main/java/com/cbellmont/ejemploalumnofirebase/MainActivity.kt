package com.cbellmont.ejemploalumnofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var userId = Random.nextInt()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result?.token

                val msg = "Nuestro token es $token"
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })


        b1.setOnClickListener { firebaseAnalytics.logEvent("MiEventoPersonalizado", Bundle()) }
        b2.setOnClickListener {
            val nullString : String? = null
            nullString!!.length
        }
        b3.setOnClickListener {
            val nullString : String? = null
            try {
                nullString!!.length
            } catch (exception : Exception) {
                FirebaseCrashlytics.getInstance().setCustomKey("TipoDeCrash", 1)
                FirebaseCrashlytics.getInstance().log("Crash capturado en un try catch.")
                FirebaseCrashlytics.getInstance().setUserId("$userId")
                FirebaseCrashlytics.getInstance().recordException(exception)
            }
        }


    }
}