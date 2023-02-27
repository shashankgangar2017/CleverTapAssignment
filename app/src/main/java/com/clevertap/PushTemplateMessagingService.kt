package com.clevertap

import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class PushTemplateMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        CTFcmMessageHandler()
            .createNotification(applicationContext, remoteMessage)
    }

    override fun onNewToken(s: String) {
        //no-op
    }
}