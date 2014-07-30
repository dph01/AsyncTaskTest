package com.ovaphone

import android.app.Activity
import android.util.Log;

//class MainActivity extends ScreenBrightnessControllerBaseActivity {
class MainActivity extends Activity {
  override def onResume() {
    try {
      super.onResume()
      new CheckNetworkStatusOnStartup(this).execute(new CheckNetworkStatusOnStartup.Payload(this))
    }
    catch {
      case e: Exception =>
        Log.e("", "exception thrown: ", e)
    }
  }

}

