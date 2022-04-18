package com.raywenderlich.android.drinkit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// TODO: import libraries

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO: create OnClickListener for the button_retrieve_token

    // TODO: check in bundle extras for notification data
  }


  override fun onStart() {
    super.onStart()
    //TODO: Register the receiver for notifications
  }

  override fun onStop() {
    super.onStop()
    // TODO: Unregister the receiver for notifications
  }

  // TODO: Add a method for receiving notifications

  // TODO: Add a function to check for Google Play Services

  // TODO: Create a message receiver constant

  companion object {
    private const val TAG = "MainActivity"
  }
}