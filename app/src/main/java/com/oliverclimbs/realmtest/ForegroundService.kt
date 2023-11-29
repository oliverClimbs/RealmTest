package com.oliverclimbs.realmtest

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.util.Log

open class ForegroundService : Service()
{
  private var notificationManager: NotificationManager? = null

  // ---------------------------------------------------------------------------------------------
  override fun onCreate()
  {
    Log.i("TAG", "ForegroundService: onCreate")
    super.onCreate()

    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManager?.createNotificationChannel(
      NotificationChannel(CHANNEL_ID, CHANNEL_ID,
                          NotificationManager.IMPORTANCE_LOW).apply {
        setShowBadge(false)
      })

  }

  // ---------------------------------------------------------------------------------------------
  override fun onDestroy()
  {
    Log.i("TAG", "ForegroundService: onDestroy")
    super.onDestroy()

    releaseWakeLock()
    stopForeground(STOP_FOREGROUND_REMOVE)

  }

  // ---------------------------------------------------------------------------------------------
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
  {
    super.onStartCommand(intent, flags, startId)

    if (intent?.action == ACTION_FOREGROUND ||
        intent?.action == ACTION_FOREGROUND_WAKELOCK)
      startForeground(R.string.foreground_service_started,
                      Notification.Builder(this, CHANNEL_ID).build())


    if (intent?.action == ACTION_FOREGROUND_WAKELOCK)
      if (mWakeLock == null)
        acquireWakeLock()
      else
        releaseWakeLock()

    return START_STICKY

  }

  @SuppressLint("WakelockTimeout")
  private fun acquireWakeLock()
  {
    mWakeLock = getSystemService(PowerManager::class.java)?.newWakeLock(
      PowerManager.PARTIAL_WAKE_LOCK,
      WAKE_LOCK_TAG)

    mWakeLock?.acquire()

  }

  // ---------------------------------------------------------------------------------------------
  private fun releaseWakeLock()
  {
    mWakeLock?.release()
    mWakeLock = null

  }

  // ---------------------------------------------------------------------------------------------
  override fun onBind(intent: Intent): IBinder?
  {
    return null
  }

  // ---------------------------------------------------------------------------------------------
  companion object
  {
    var mWakeLock: WakeLock? = null
    const val ACTION_FOREGROUND = "com.oliverClimbs.realmtest.FOREGROUND"
    const val ACTION_FOREGROUND_WAKELOCK = "com.oliverClimbs.realmtest.FOREGROUND_WAKELOCK"
    const val CHANNEL_ID = "com.oliverClimbs.realmtest:foreground-channel"
    const val WAKE_LOCK_TAG = "com.oliverClimbs.realmtest:wake-service"

  }
}