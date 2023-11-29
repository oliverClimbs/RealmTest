package com.oliverclimbs.realmtest.ui.home

import android.app.Application
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oliverclimbs.realmtest.Outing
import com.oliverclimbs.realmtest.Pressure
import com.oliverclimbs.realmtest.RealmDatabase
import io.realm.kotlin.MutableRealm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration.between
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

class HomeViewModel(app: Application) :
  AndroidViewModel(app), SensorEventListener
{
  private val context = app

  private var sensorManager: SensorManager =
    context.getSystemService(SENSOR_SERVICE) as SensorManager

  private var pressureSensor: Sensor? =
    sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

  private val realm = RealmDatabase().getDatabase()
  private var outing = Outing()
  private var isEmpty = true
  private var lastTime = Instant.now()

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }
  val text: LiveData<String> = _text

  init
  {
    if (pressureSensor != null)
      sensorManager.registerListener(this,
                                     pressureSensor,
                                     SensorManager.SENSOR_DELAY_FASTEST)
    else
      Log.e("TAG", "No Sensor")

  }

  override fun onSensorChanged(event: SensorEvent?)
  {
    if (event != null)
    {
      if (event.sensor.type == Sensor.TYPE_PRESSURE)
      {
        val p = event.values[0]
        val now = Instant.now()

        viewModelScope.launch(Dispatchers.IO) {
          kotlin.runCatching {
            outing =
              realm.write {
                if (isEmpty) outing = newOuting(this)

                findLatest(outing)?.apply {
                  stopTime = now

                  pressure.add(Pressure().apply {
                    time = now
                    hPa = p
                  })
                } ?: outing
              }
          }.onFailure { Log.e("TAG", it.stackTraceToString(), it) }
        }

        val frequency = (1000.0 / (between(lastTime, now).toMillis())).roundToInt()
        lastTime = now

        _text.postValue("${now.truncatedTo(ChronoUnit.SECONDS)}\n$p\n$frequency Hz")
        Log.d("Sensor Changed", p.toString())

      }
    }
  }

  private fun newOuting(realm: MutableRealm): Outing
  {
    var outing = Outing()

    kotlin.runCatching {
      val now = Instant.now()

      outing = realm.copyToRealm(Outing().apply {
        startTime = now
        stopTime = now
      }, UpdatePolicy.ALL)

      isEmpty = false

    }.onFailure { Log.e("TAG", it.stackTraceToString(), it) }

    return outing

  }

  override fun onCleared()
  {
    Log.d("TAG", "HomeViewModel: onCleared")
    super.onCleared()
    sensorManager.unregisterListener(this, pressureSensor)

  }

  override fun onAccuracyChanged(p0: Sensor?, p1: Int)
  {
  }
}