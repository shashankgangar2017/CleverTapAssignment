package com.clevertap

import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.clevertap.android.sdk.CleverTapAPI

class MainActivity : AppCompatActivity() {

    var cleverTapDefaultInstance: CleverTapAPI? = null


    lateinit var raiseEvent: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)

        Log.i(
            "CleverTap",
            "onCreate: cleverTapDefaultInstance value ${cleverTapDefaultInstance.toString()}"
        )
        raiseEvent = findViewById(R.id.raiseEvent)

        CleverTapAPI.createNotificationChannel(
            getApplicationContext(), "080995", "TAM TEST", "Testing for TAM Position",
            NotificationManager.IMPORTANCE_MAX, true
        )

        cleverTapDefaultInstance?.promptForPushPermission(true) // Takes boolean as a parameter. If true and the permission is denied then we fallback to app’s notification settings, if it’s false then we just throw a verbose log saying permission is denied.
        
        raiseEvent.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                cleverTapEvent()
            }
        })

    }

    private fun cleverTapEvent() {
        val prodViewedAction = HashMap<String, Any>()
        prodViewedAction.put("Product ID", 1)
        prodViewedAction.put(
            "Product Image",
            "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg"
        )
        prodViewedAction.put("Product Name", "CleverTap")
        prodViewedAction.put("Email", "shashankgangar2017@gmail.com")

        cleverTapDefaultInstance?.pushEvent("Product viewed", prodViewedAction)
        Log.i(
            "CleverTap",
            "onCreate: cleverTapDefaultInstance cleverTapID ${cleverTapDefaultInstance?.cleverTapID.toString()}"
        )

    }

}