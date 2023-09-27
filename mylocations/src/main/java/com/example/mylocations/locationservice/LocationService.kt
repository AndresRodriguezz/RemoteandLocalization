package com.example.mylocations.locationservice

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.mylocations.util.Constants.ACTION_START
import com.example.mylocations.util.Constants.ACTION_STOP
import com.example.mylocations.util.Constants.CHANEL_ID_NOTIFICATION
import com.example.mylocations.util.Constants.DEFAULT_DESCRIPTION_NOTIFICATION
import com.example.mylocations.util.Constants.DEFAULT_TITLE_NOTIFICATION
import com.example.mylocations.util.Constants.ID_NOTIFICATION
import com.example.mylocations.util.Constants.TABLE_NAME_FIREBASE
import com.example.mylocations.util.Constants.TIME_TO_SHOW_INFO
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LocationService @Inject constructor() : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient
    private lateinit var database: FirebaseDatabase
    private lateinit var locationDataRef: DatabaseReference

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
        database = FirebaseDatabase.getInstance()
        locationDataRef = database.reference.child(TABLE_NAME_FIREBASE)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, CHANEL_ID_NOTIFICATION)
            .setContentTitle(DEFAULT_TITLE_NOTIFICATION)
            .setContentText(DEFAULT_DESCRIPTION_NOTIFICATION)
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
            .setOngoing(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(TIME_TO_SHOW_INFO)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString()
                val long = location.longitude.toString()
                val timestamp = System.currentTimeMillis()
                val updatedNotification = notification.setContentText(
                    "$DEFAULT_DESCRIPTION_NOTIFICATION $lat,$long"
                )

                locationDataRef.push().setValue(Location(lat, long, timestamp))
                notificationManager.notify(ID_NOTIFICATION, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(ID_NOTIFICATION, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}
