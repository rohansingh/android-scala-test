package com.rrsllc.com.android.scalaTest

import android.app.Activity
import android.os.Bundle
import android.util.Log

import com.rrsllc.android.scalaTest.R
import android.widget.TextView

object HelloAndroidActivity extends Activity {
  val TAG = "android-scala-test"
}

class HelloAndroidActivity extends Activity {
  var helloTextView: Option[TextView] = None

  /**
   * Called when the activity is first created.
   * @param savedInstanceState If the activity is being re-initialized after
   * previously being shut down then this Bundle contains the data it most
   * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
   */
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    
    Log.i(HelloAndroidActivity.TAG, "onCreate with Scala!!!!")
    setContentView(R.layout.main)

    helloTextView = Option(findViewById(R.id.helloTextView).asInstanceOf[TextView])
  }

  override def onResume(): Unit = {
    super.onResume()

    val items = Array(1, 2, 3, 4, 5)
    val output = items
      .map { i => i * i }
      .mkString(", ")

    helloTextView map { _.setText(output) }
  }
}
