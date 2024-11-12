package ro.pub.cs.systems.eim.colocviu1_1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class Colocviu1_1Service : Service() {
    private val scope = CoroutineScope(Dispatchers.IO)

    @Override
    override fun onCreate() {
        super.onCreate()
        val notification_manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notification_manager.createNotificationChannel(NotificationChannel("Colocviu1_1", "Colocviu1_1", NotificationManager.IMPORTANCE_DEFAULT))

        val notification_intent = Intent(this, Colocviu1_1MainActivity::class.java)
        val pending_intent = PendingIntent.getActivity(this, 0, notification_intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "Colocviu1_1")
            .setContentTitle("Colocviu1_1")
            .setContentText("Service is running")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pending_intent)
            .setAutoCancel(true)
            .build()

        startForeground(1, notification)
    }

    @Override
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @Override
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            delay(5000)
            val current_date = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val formatted_date_time = formatter.format(current_date)
            val broadcast_message = formatted_date_time + " " + intent?.getStringExtra(Constants.EDIT_TEXT)
            val broadcast_intent = Intent(Constants.ACTION_STRING)
            broadcast_intent.action = Constants.ACTION_STRING
            broadcast_intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, broadcast_message)
            this@Colocviu1_1Service.sendBroadcast(broadcast_intent)
        }

        return START_REDELIVER_INTENT
    }
}