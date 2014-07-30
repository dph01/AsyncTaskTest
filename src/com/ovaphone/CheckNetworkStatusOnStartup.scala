package com.ovaphone

import android.app.{AlertDialog, ProgressDialog}
import android.content.{Context, DialogInterface}
import android.os.AsyncTask
import android.util.Log

object CheckNetworkStatusOnStartup {
  class Payload(var mainActivity: MainActivity) {
    var isConnected: java.lang.Boolean = false
  }
}

class CheckNetworkStatusOnStartup(var context: Context) extends AsyncTask[CheckNetworkStatusOnStartup.Payload, Void, CheckNetworkStatusOnStartup.Payload] {

  private var exception: Exception = null

  override protected def doInBackground(params: CheckNetworkStatusOnStartup.Payload*): CheckNetworkStatusOnStartup.Payload = {
    val payload = params(0)
    Log.d(getClass.getName, "doInBackground starting  ")
    try {
      payload.isConnected = true
    } catch {
      case e: Exception => {
        Log.d(getClass.getName, "doInBackground exception caught:  " + e)
        this.exception = e
        payload.isConnected = false
      }
    }
    payload
  }

  override protected def onPostExecute(payload: CheckNetworkStatusOnStartup.Payload) {
    val mainActivity = payload.mainActivity
    if (this.exception != null) {
      Log.d(getClass.getName, "onPostExecute doInBackground threw an exception: ", this.exception)
    } else if (payload.isConnected) {
      Log.d(getClass.getName, "onPostExecute connected to network")
    }
  }
}
