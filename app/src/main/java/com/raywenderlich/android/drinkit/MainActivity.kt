package com.raywenderlich.android.drinkit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

// TODO: import libraries

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context : Context?, intent : Intent?) {
      text_view_notification.text = intent?.extras?.getString("message")
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val bundle = intent.extras
    if (bundle != null) {
      text_view_notification.text = bundle.getString("text")
    }

    button_retrieve_token.setOnClickListener {
      if (checkGooglePlayServices()) {
        FirebaseInstanceId
          .getInstance()
          .instanceId
          .addOnCompleteListener { task ->
            if (!task.isSuccessful) {
              Log.w(TAG, "getInstanceId failed", task.exception)
              return@addOnCompleteListener
            }

            val token = task.result?.token

            val msg = getString(R.string.token_prefix, token)
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
          }
      } else {
        Log.w(TAG, "Device doesn't have google play services")
      }
    }

  }

  override fun onStart() {
    super.onStart()
    LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, IntentFilter("myData"))
  }

  override fun onStop() {
    super.onStop()
    LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
  }

  private fun checkGooglePlayServices(): Boolean {
    val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

    return if (status != ConnectionResult.SUCCESS) {
      Log.e(TAG, "Error")
      false
    } else {
      Log.i(TAG, "Google play services updated")
      true
    }
  }

  companion object {
    private const val TAG = "MainActivity"
  }
}