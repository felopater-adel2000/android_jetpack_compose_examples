package com.restart.jetpack_compose_examples

//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage

//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
//
//class MyMessagingService : FirebaseMessagingService() {
//    override fun onMessageReceived(message: RemoteMessage) {
//        // build your notification here
//
//        super.onMessageReceived(message)
//
//        // Log all details of the message
//        message.data.forEach { (key, value) ->
//            Log.d("FCM", "$key: $value")
//        }
//
//        message.messageId?.let { Log.d("FCM", "Message ID: $it") }
//
//        message.notification?.let { Log.d("FCM", "Notification: ${it.title}") }
//        message.notification?.body?.let { Log.d("FCM", "Body: $it") }
//        message.data["title"]?.let { Log.d("FCM", "Title: $it") }
//        message.data["body"]?.let { Log.d("FCM", "Body: $it") }
//        message.data["url"]?.let { Log.d("FCM", "URL: $it") }
//        message.data["imageUrl"]?.let { Log.d("FCM", "Image URL: $it") }
//        message.data["type"]?.let { Log.d("FCM", "Type: $it") }
//        message.data["id"]?.let { Log.d("FCM", "ID: $it") }
//        message.data["senderId"]?.let { Log.d("FCM", "Sender ID: $it") }
//        message.data["senderName"]?.let { Log.d("FCM", "Sender Name: $it") }
//        message.data["senderImage"]?.let { Log.d("FCM", "Sender Image: $it") }
//        message.data["senderEmail"]?.let { Log.d("FCM", "Sender Email: $it") }
//
//
//    }
//}