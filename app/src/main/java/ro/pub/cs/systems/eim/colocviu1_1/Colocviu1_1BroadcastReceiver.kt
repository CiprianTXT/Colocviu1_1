package ro.pub.cs.systems.eim.colocviu1_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Colocviu1_1BroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("BroadcastReceiver", "onReceive() callback method has been invoked")
        val action = intent.action
        if (action == Constants.ACTION_STRING) {
            Log.d("BroadcastReceiver", intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA).toString())
        }
    }
}