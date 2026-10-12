package com.restart.jetpack_compose_examples

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        val options = FirebaseOptions.Builder()
//            .setApplicationId("1:253360623182:android:f63e70fe01188d75707d1f")
//            .setProjectId("english-words-d9d2d")
//            .setApiKey("AIzaSyAoaoXNtC9yoUIfBaDGcr4TagVLjuE5Svo")
//            .setGcmSenderId("253360623182")
//            .build()
//
//        FirebaseApp.initializeApp(this, options)
//
//        FirebaseMessaging.getInstance().token
//            .addOnSuccessListener { token ->
//                Log.d("FCM", token)
//            }.addOnFailureListener {
//                Log.d("FCM", "Failed to get token message: ${it.message}")
//            }


        FirebaseApp.initializeApp(this)?.addBackgroundStateChangeListener {
            Log.d("FCM", "Background state changed $it")

            val token = FirebaseInstanceId.getInstance().getToken("253360623182", "FCM")
            Log.d("FCM", "Token: $token")

        }

    }

}